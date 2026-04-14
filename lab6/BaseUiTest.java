package com.baarsch_bytes.end2end;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class BaseUiTest {

    protected static final String BASE_URL = "http://localhost:5173";
    protected static final int MAX_WAIT_SECONDS = 10;

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUpBase() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless");

        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_WAIT_SECONDS));
    }

    @AfterEach
    public void tearDownBase() {
        takeScreenshot(getClass().getSimpleName() + "-" + System.currentTimeMillis() + ".png");
        if (driver != null) {
            driver.quit();
        }
    }

    protected void openCoursePage() {
        driver.get(BASE_URL + "/");
        waitForVisible("new-course-fields");
    }

    protected void openStudentPage() {
        driver.get(BASE_URL + "/");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-student-list-link"))).click();
        waitForVisible("add-student-fields");
    }

    protected WebElement waitForVisible(String id) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }

    protected void typeInto(String id, String value) {
        WebElement element = waitForVisible(id);
        element.clear();
        element.sendKeys(value);
    }

    protected String unique(String prefix) {
        return prefix + "-" + System.currentTimeMillis();
    }

    protected List<WebElement> dataRows(String tableId) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(tableId)));
        return driver.findElements(By.xpath("//table[@id='" + tableId + "']//tr[td]"));
    }

    protected long countRows(String tableId) {
        return dataRows(tableId).size();
    }

    protected boolean rowExists(String tableId, String text) {
        for (WebElement row : dataRows(tableId)) {
            try {
                if (row.getText().contains(text)) {
                    return true;
                }
            } catch (StaleElementReferenceException ignored) {
            }
        }
        return false;
    }

    protected WebElement waitForRowContaining(String tableId, String text) {
        return wait.until(d -> {
            for (WebElement row : dataRows(tableId)) {
                try {
                    if (row.getText().contains(text)) {
                        return row;
                    }
                } catch (StaleElementReferenceException ignored) {
                }
            }
            return null;
        });
    }

    protected void waitForRowToDisappear(String tableId, String text) {
        wait.until(d -> !rowExists(tableId, text));
    }

    protected void clickRowButton(String tableId, String rowText, String buttonId) {
        WebElement row = waitForRowContaining(tableId, rowText);
        WebElement button = row.findElement(By.xpath(".//*[@id='" + buttonId + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    protected Select selectInRow(String tableId, String rowText, String selectId) {
        WebElement row = waitForRowContaining(tableId, rowText);
        WebElement selectEl = row.findElement(By.xpath(".//*[@id='" + selectId + "']"));
        return new Select(selectEl);
    }

    protected void clickInRow(String tableId, String rowText, String elementId) {
        WebElement row = waitForRowContaining(tableId, rowText);
        WebElement element = row.findElement(By.xpath(".//*[@id='" + elementId + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void selectOptionContaining(Select select, String text) {
        for (WebElement option : select.getOptions()) {
            if (option.getText().contains(text)) {
                select.selectByVisibleText(option.getText());
                return;
            }
        }
        fail("Could not find option containing: " + text);
    }

    protected void fillNewStudentForm(String name, String major, String gpa) {
        typeInto("new-student-name", name);
        typeInto("new-student-major", major);
        typeInto("new-student-gpa", gpa);
    }

    protected void submitNewStudent() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-student-button"))).click();
    }

    protected void fillNewCourseForm(String name, String instructor, String maxSize, String room) {
        typeInto("new-course-name", name);
        typeInto("new-course-instructor", instructor);
        typeInto("new-course-max-size", maxSize);
        typeInto("new-course-room", room);
    }

    protected void submitNewCourse() {
        WebElement container = waitForVisible("new-course-fields");
        List<WebElement> buttons = container.findElements(By.tagName("button"));

        for (WebElement button : buttons) {
            if (button.isDisplayed() && button.isEnabled()) {
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(button)).click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
                }
                return;
            }
        }

        waitForVisible("new-course-room").sendKeys(Keys.ENTER);
    }

    protected String createStudent(String baseName, String major, String gpa) {
        openStudentPage();
        String name = unique(baseName);
        fillNewStudentForm(name, major, gpa);
        submitNewStudent();
        waitForRowContaining("student-list-table", name);
        return name;
    }

    protected String createCourse(String baseName, String instructor, String maxSize, String room) {
        openCoursePage();
        String name = unique(baseName);
        fillNewCourseForm(name, instructor, maxSize, room);
        submitNewCourse();
        waitForRowContaining("course-list-table", name);
        return name;
    }

    protected void shortPause() {
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected void takeScreenshot(String filename) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path destination = Paths.get("screenshots", filename);
            Files.createDirectories(destination.getParent());
            Files.copy(screenshot.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ignored) {
        }
    }
}