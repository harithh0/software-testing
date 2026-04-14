package com.baarsch_bytes.end2end;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentUITest extends BaseUiTest {

    @Test
    public void testCreateStudent() {
        String name = createStudent("student-create", "Computer Science", "3.50");

        WebElement row = waitForRowContaining("student-list-table", name);
        String rowText = row.getText();

        assertTrue(rowText.contains("Computer Science"));
        assertTrue(rowText.contains("3.5") || rowText.contains("3.50"));
    }

    @Test
    public void testEditStudent() {
        String originalName = createStudent("student-edit", "Math", "3.10");

        clickRowButton("student-list-table", originalName, "edit-student-button");

        String updatedName = unique("student-updated");
        typeInto("edit-student-name", updatedName);
        typeInto("edit-student-major", "Cybersecurity");
        typeInto("edit-student-gpa", "3.90");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-student-save-button"))).click();

        WebElement updatedRow = waitForRowContaining("student-list-table", updatedName);
        String rowText = updatedRow.getText();

        assertTrue(rowText.contains("Cybersecurity"));
        assertTrue(rowText.contains("3.9") || rowText.contains("3.90"));
        assertFalse(rowExists("student-list-table", originalName));
    }

    @Test
    public void testDeleteStudent() {
        String name = createStudent("student-delete", "Physics", "3.20");

        clickRowButton("student-list-table", name, "delete-student-button");
        waitForRowToDisappear("student-list-table", name);

        assertFalse(rowExists("student-list-table", name));
    }

    @Test
    public void testRejectStudentGpaAboveFour() {
        openStudentPage();
        long before = countRows("student-list-table");

        String invalidName = unique("bad-high-gpa");
        fillNewStudentForm(invalidName, "CS", "4.50");
        submitNewStudent();
        shortPause();

        assertEquals(before, countRows("student-list-table"));
        assertFalse(rowExists("student-list-table", invalidName));
    }

    @Test
    public void testRejectStudentGpaBelowZero() {
        openStudentPage();
        long before = countRows("student-list-table");

        String invalidName = unique("bad-low-gpa");
        fillNewStudentForm(invalidName, "CS", "-0.10");
        submitNewStudent();
        shortPause();

        assertEquals(before, countRows("student-list-table"));
        assertFalse(rowExists("student-list-table", invalidName));
    }

    @Test
    public void testRejectStudentNameLongerThan255Characters() {
        openStudentPage();
        long before = countRows("student-list-table");

        String longName = "A".repeat(256);
        fillNewStudentForm(longName, "CS", "3.00");
        submitNewStudent();
        shortPause();

        assertEquals(before, countRows("student-list-table"));
        assertFalse(rowExists("student-list-table", longName));
    }
}