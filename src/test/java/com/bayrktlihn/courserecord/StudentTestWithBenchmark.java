package com.bayrktlihn.courserecord;

import com.bayrktlihn.courserecord.model.LecturerCourseRecord;
import com.bayrktlihn.courserecord.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class StudentTestWithBenchmark {

    @ExtendWith(BenchmarkExtension.class)
    @Nested
    @DisplayName("Add Course to Student")
    @Tag("addCourse")
    class AddCourseToStudent {
        @Test
        @DisplayName("Got an exception when add a null lecturer course record to student")
        @Tag("addCourse")
        void throwsExceptionWhenAddToNullCourseToStudent() {
            final Student ahmet = new Student("1", "Ahmet", "Can");
            assertThrows(IllegalArgumentException.class, () -> ahmet.addCourse(null));
            assertThrows(IllegalArgumentException.class, () -> ahmet.addCourse(null), "Throws IllegalArgumentException");
            final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> ahmet.addCourse(null));
            assertEquals("Can't add course with null lecturer course record", illegalArgumentException.getMessage());
        }

        @Nested
        @DisplayName("Add Course to Student(Exceptional)")
        class AddCourseToStudentExceptionScenario {
            @Test
            @DisplayName("Add course to a student less than 10ms")
            @Tags({@Tag("exceptional"), @Tag("addCourse")})
            void addCourseToStudentWithATimeConstraint() {


                assertTimeout(Duration.ofMillis(10), () -> {

                });

                final String result = assertTimeout(Duration.ofMillis(10), () -> {
                    return "some string result";
                });

                assertEquals("some string result", result);

                final Student student = new Student("1", "Ahmet", "Can");
                LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord();
                assertTimeout(Duration.ofMillis(6), () -> student.addCourse(lecturerCourseRecord));

                assertTimeoutPreemptively(Duration.ofMillis(6), () -> student.addCourse(lecturerCourseRecord));
            }
        }

    }


}
