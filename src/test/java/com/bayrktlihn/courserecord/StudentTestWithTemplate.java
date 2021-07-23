package com.bayrktlihn.courserecord;

import com.bayrktlihn.courserecord.model.LecturerCourseRecord;
import com.bayrktlihn.courserecord.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentTestWithTemplate implements TestLifecycleReporter {
    private Student student;

    @BeforeAll
    void setUp() {
        student = new Student("id1", "Ahmet", "Yilmaz");
    }

    @ExtendWith(RepeatedStudentTestTemplateInvocationContextProvider.class)
    @TestTemplate
    void addCourseToStudent(LecturerCourseRecord lecturerCourseRecord, int numberOfInvocation) {
        student.addCourse(lecturerCourseRecord);
        assertEquals(numberOfInvocation, student.getStudentCourseRecords().size());

    }
}
