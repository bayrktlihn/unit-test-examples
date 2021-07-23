package com.bayrktlihn.courserecord;

import com.bayrktlihn.courserecord.model.Course;
import com.bayrktlihn.courserecord.model.LecturerCourseRecord;
import com.bayrktlihn.courserecord.model.Semester;
import com.bayrktlihn.courserecord.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.function.ThrowingConsumer;

import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;

public class StudentTestWithDynamicTest {

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("id1", "Ahmet", "Yilmaz");
    }

    @TestFactory
    Stream<DynamicNode> addCourseToStudentWithCourseCodeAndCourseType(TestReporter testReporter) {
        return Stream.of("101", "103", "105")
                .map(courseCode -> dynamicContainer("Add course<" + courseCode + "> to student",
                        Stream.of(Course.CourseType.MANDATORY, Course.CourseType.ELECTIVE)
                                .map(courseType -> DynamicTest.dynamicTest("Add course<" + courseType + "> to student", () -> {
                                    final Course course = new Course();
                                    course.setCode(courseCode);
                                    course.setCourseType(courseType);
                                    final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
                                    student.addCourse(lecturerCourseRecord);
                                    assertTrue(student.isTakeCourse(course));
                                    testReporter.publishEntry("Student Course Size", String.valueOf(student.getStudentCourseRecords().size()));
                                }))

                ));
    }

    @TestFactory
    Stream<DynamicTest> addCourseToStudentWithCourseCode() {
        final Iterator<String> courseCodeGenerator = Stream.of("101", "103", "105").iterator();
        Function<String, String> displayNameGenerator = courseCode -> "Add course<" + courseCode + "> to student";
        ThrowingConsumer<String> testExecutor = courseCode -> {
            final Course course = new Course();
            course.setCode(courseCode);
            course.setCourseType(Course.CourseType.MANDATORY);
            final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertTrue(student.isTakeCourse(course));
        };

        return DynamicTest.stream(courseCodeGenerator, displayNameGenerator, testExecutor);
    }
}
