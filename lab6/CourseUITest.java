package com.baarsch_bytes.end2end;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.*;

public class CourseUITest extends BaseUiTest {

    @Test
    public void testCreateCourse() {
        String courseName = createCourse("course-create", "12345", "3", "MSC 101");

        WebElement row = waitForRowContaining("course-list-table", courseName);
        String rowText = row.getText();

        assertTrue(rowText.contains("12345"));
        assertTrue(rowText.contains("3"));
        assertTrue(rowText.contains("MSC 101"));
    }

    @Test
    public void testEditCourse() {
        String originalName = createCourse("course-edit", "11111", "2", "Wingo 200");

        clickRowButton("course-list-table", originalName, "edit-course-button");

        String updatedName = unique("course-updated");
        typeInto("edit-course-name", updatedName);
        typeInto("edit-course-instructor", "22222");
        typeInto("edit-course-max-size", "4");
        typeInto("edit-course-room", "Wingo 220");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-course-save-button"))).click();

        WebElement updatedRow = waitForRowContaining("course-list-table", updatedName);
        String rowText = updatedRow.getText();

        assertTrue(rowText.contains("22222"));
        assertTrue(rowText.contains("4"));
        assertTrue(rowText.contains("Wingo 220"));
        assertFalse(rowExists("course-list-table", originalName));
    }

    @Test
    public void testDeleteCourse() {
        String courseName = createCourse("course-delete", "33333", "2", "Snow 110");

        clickRowButton("course-list-table", courseName, "delete-course-button");
        waitForRowToDisappear("course-list-table", courseName);

        assertFalse(rowExists("course-list-table", courseName));
    }

    @Test
    public void testAddStudentToCourse() {
        String studentName = createStudent("student-for-course", "CS", "3.20");
        String courseName = createCourse("course-add-student", "44444", "2", "Burdick 101");

        Select addSelect = selectInRow("course-list-table", courseName, "select-student");
        selectOptionContaining(addSelect, studentName);
        clickInRow("course-list-table", courseName, "add-student-button");

        wait.until(d -> {
            WebElement row = waitForRowContaining("course-list-table", courseName);
            return row.getText().contains(studentName);
        });

        WebElement row = waitForRowContaining("course-list-table", courseName);
        String rowText = row.getText();

        assertTrue(rowText.contains(studentName));
    }

    @Test
    public void testCourseCapacityRule() {
        String studentOne = createStudent("student-cap-1", "CS", "3.10");
        String studentTwo = createStudent("student-cap-2", "CS", "3.20");
        String courseName = createCourse("course-capacity", "55555", "1", "Oak 100");

        Select addFirst = selectInRow("course-list-table", courseName, "select-student");
        selectOptionContaining(addFirst, studentOne);
        clickInRow("course-list-table", courseName, "add-student-button");

        wait.until(d -> {
            WebElement row = waitForRowContaining("course-list-table", courseName);
            return row.getText().contains(studentOne);
        });

        Select addSecond = selectInRow("course-list-table", courseName, "select-student");
        selectOptionContaining(addSecond, studentTwo);
        clickInRow("course-list-table", courseName, "add-student-button");

        shortPause();

        WebElement row = waitForRowContaining("course-list-table", courseName);
        String rowText = row.getText();

        assertTrue(rowText.contains(studentOne));
        assertFalse(rowText.contains(studentTwo));
    }

    @Test
    public void testRejectCourseNameLongerThan255Characters() {
        openCoursePage();
        long before = countRows("course-list-table");

        String longName = "C".repeat(256);
        fillNewCourseForm(longName, "66666", "2", "Room 9");
        submitNewCourse();
        shortPause();

        assertEquals(before, countRows("course-list-table"));
        assertFalse(rowExists("course-list-table", longName));
    }
}