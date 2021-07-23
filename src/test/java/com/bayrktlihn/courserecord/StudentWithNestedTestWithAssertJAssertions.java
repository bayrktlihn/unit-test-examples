package com.bayrktlihn.courserecord;

import com.bayrktlihn.courserecord.model.LecturerCourseRecord;
import com.bayrktlihn.courserecord.model.Student;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@Disabled("No more valid tests")
//@ExtendWith(TestLoggerExtension.class)
//@ExtendWith(DropCourseConditionExtension.class)
@Tag("student")
@DisplayName("Student Test With Nested Tests")
public class StudentWithNestedTestWithAssertJAssertions {


//    @RegisterExtension
//    static TestLoggerExtension testLoggerExtension = new TestLoggerExtension();

    @Nested
    @DisplayName("Create Student")
    @Tag("createStudent")
    class CreateStudent {
        @Test
        @DisplayName("Test every student must have an id, name and surname")
        void shouldCreateStudentWithIdNameAndSurname() {

            final Student ahmet = new Student("2", "Ahmet", "Yilmaz");
            Student alihan = new Student("1", "Alihan", "Bayraktar");
            Student student = alihan;


            assertThat(alihan).as("Alihan")
                    .isSameAs(student)
                    .isNotSameAs(ahmet)
                    .extracting(Student::getName)
                    .asString()
                    .isEqualTo("Alihan")
                    .isNotEqualTo("Alihann")
                    .startsWith("A");


            assertThat(new Student("id1", "Mehmet", "Can").getName()).as("Mehmet")
                    .doesNotEndWith("M");


            assertThat(List.of(alihan, ahmet))
                    .extracting(Student::getName)
                    .containsOnly("Alihan", "Ahmet");


        }

        @Test
        @DisplayName("Test every student must have an id, name and surname with grouped assertions")
        void shouldCreateStudentWithNameAndSurnameWithGroupedAssertions() {
            Student alihan = new Student("1", "Alihan", "Bayraktar");

            final SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(alihan.getName()).as("Alihan's name").isEqualTo("Alihan");
            softAssertions.assertThat(alihan.getName()).as("Alihann's name").isNotEqualTo("Alihann");
            softAssertions.assertAll();


            assertAll("Student's name character check",
                    () -> assertTrue(alihan.getName().startsWith("A")),
                    () -> assertTrue(alihan.getName().startsWith("A"), () -> "Student's name starts with Al"),
                    () -> assertFalse(() -> {
                        Student mehmet = new Student("id1", "Mehmet", "Can");
                        return mehmet.getName().endsWith("M");
                    }, () -> "Student's name ends with M")
            );

            assertAll(() -> {
                final Student ahmet = new Student("2", "Ahmet", "Yilmaz");
                assertArrayEquals(new String[]{"Alihan", "Ahmet"}, Stream.of(alihan, ahmet).map(Student::getName).toArray());
            }, () -> {
                Student student = alihan;
                final Student ahmet = new Student("2", "Ahmet", "Yilmaz");

                assertSame(alihan, student);
                assertNotSame(student, ahmet);
            });
        }


    }

    @Nested
    @DisplayName("Add Course to Student")
    @Tag("addCourse")
    class AddCourseToStudent {

        @Test
        @DisplayName("Add course to a student less than 10ms")
//        @Tags({@Tag("exceptional"), @Tag("addCourse")})
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


        @Nested
        @DisplayName("Add Course to Student(Exceptional)")
        @Tag("exceptional")
        class AddCourseToStudentExceptionScenario {
            @Test
            @DisplayName("Got an exception when add a null lecturer course record to student")
            void throwsExceptionWhenAddToNullCourseToStudent() {
                final Student ahmet = new Student("1", "Ahmet", "Can");

                assertThatIllegalArgumentException().as("Throws IllegalArgumentException").isThrownBy(() -> ahmet.addCourse(null));

                final Throwable throwable = catchThrowable(() -> ahmet.addCourse(null));
                assertThat(throwable)
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("Can't add course with null lecturer course record");


            }
        }

    }


}
