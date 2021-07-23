package com.bayrktlihn.courserecord;

import com.bayrktlihn.courserecord.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Student Test with TestInfo and TestReporter Parameters")
public class JUnitParameterizedStudentTest {

    private Student student;

    public JUnitParameterizedStudentTest(TestInfo testInfo) {
        assertEquals("Student Test with TestInfo and TestReporter Parameters", testInfo.getDisplayName());
    }

    @BeforeEach
    void setStudent(TestInfo testInfo) {
        if (testInfo.getTags().contains("create")) {
            this.student = new Student("id1", "Ahmet", "Yilmaz");
        } else {
            student = new Student("id1", "Mehmet", "Yilmaz");
        }
    }

    @Test
    @DisplayName("Create Student")
    @Tag("create")
    void createStudent(TestInfo testInfo) {
        assertTrue(testInfo.getTags().contains("create"));
        assertEquals("Ahmet", student.getName());
    }

    @Test
    @DisplayName("Add course to Student")
    @Tag("addCourse")
    void addCourseToStudent(TestReporter reporter) {
        reporter.publishEntry("startTime", LocalDateTime.now().toString());
        assertEquals("Mehmet", student.getName());
        reporter.publishEntry("endTime", LocalDateTime.now().toString());
    }
}
