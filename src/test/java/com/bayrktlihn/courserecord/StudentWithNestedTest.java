package com.bayrktlihn.courserecord;

import com.bayrktlihn.courserecord.model.Course;
import com.bayrktlihn.courserecord.model.LecturerCourseRecord;
import com.bayrktlihn.courserecord.model.NotActiveSemesterException;
import com.bayrktlihn.courserecord.model.Semester;
import com.bayrktlihn.courserecord.model.Student;
import com.bayrktlihn.courserecord.model.StudentCourseRecord;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

//@Disabled("No more valid tests")
//@ExtendWith(TestLoggerExtension.class)
//@ExtendWith(DropCourseConditionExtension.class)
@Tag("student")
@DisplayName("Student Test With Nested Tests")
public class StudentWithNestedTest {


    @RegisterExtension
    static TestLoggerExtension testLoggerExtension = new TestLoggerExtension();

    @Nested
    @DisplayName("Create Student")
    @Tag("createStudent")
    class CreateStudent {
        @Test
        @DisplayName("Test every student must have an id, name and surname")
        @Tag("createStudent")
        void shouldCreateStudentWithIdNameAndSurname() {
            Student alihan = new Student("1", "Alihan", "Bayraktar");

            assertEquals("Alihan", alihan.getName());
            assertEquals("Alihan", alihan.getName(), "Student's name");
            assertNotEquals("Alihann", alihan.getName(), "Student's name");

            assertTrue(alihan.getName().startsWith("A"));
            assertTrue(alihan.getName().startsWith("A"), () -> "Student's name starts with Al");
            assertFalse(() -> {
                Student mehmet = new Student("id1", "Mehmet", "Can");
                return mehmet.getName().endsWith("M");
            }, () -> "Student's name ends with M");

            final Student ahmet = new Student("2", "Ahmet", "Yilmaz");

            assertArrayEquals(new String[]{"Alihan", "Ahmet"}, Stream.of(alihan, ahmet).map(Student::getName).toArray());

            Student student = alihan;


            assertSame(alihan, student);
            assertNotSame(ahmet, student);
        }

        @Test
        @DisplayName("Test every student must have an id, name and surname with grouped assertions")
        @Tag("createStudent")
        void shouldCreateStudentWithNameAndSurnameWithGroupedAssertions() {
            Student alihan = new Student("1", "Alihan", "Bayraktar");

            assertAll("Student's name check",
                    () -> assertEquals("Alihan", alihan.getName()),
                    () -> assertEquals("Alihan", alihan.getName(), "Student's name"),
                    () -> assertNotEquals("Alihann", alihan.getName(), "Student's name")
            );

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

        @Test
        @DisplayName("Test student creation at only development machine")
        @Tag("createStudent")
        void shouldCreateStudentWithNameAndSurnameAtDevelopmentMachine() {
            assumeTrue(System.getProperty("ENV") != null, "Aborting Test: System property ENV doesn't exists!");
            assumeTrue(System.getProperty("ENV").equals("dev"), "Aborting Test: Not on a developer machine!");

            final Student ahmet = new Student("1", "Ahmet", "Can");
            assertAll("Student informaiton",
                    () -> assertEquals("Ahmet", ahmet.getName()),
                    () -> assertEquals("Can", ahmet.getSurname()),
                    () -> assertEquals("1", ahmet.getId())
            );
        }

        @Test
        @DisplayName("Test student creation at different environments")
        @Tag("createStudent")
        void shouldCreateStudentWithNameAndSurnameWithSpecificEnvironment() {
            final Student ahmet = new Student("1", "Ahmet", "Can");

            final String env = System.getProperty("ENV");
            assumingThat(env != null && env.equals("dev"), () -> {
                LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord();
                ahmet.addCourse(lecturerCourseRecord);
                assertEquals(1, ahmet.getStudentCourseRecords().size());
            });

            assertAll("Student Information",
                    () -> assertEquals("Ahmet", ahmet.getName()),
                    () -> assertEquals("Can", ahmet.getSurname()),
                    () -> assertEquals("1", ahmet.getId())
            );
        }

        @Disabled("No more valid scenario")
        @Test
        @DisplayName("Test that student must have only number id")
        @Tag("createStudent")
        void shouldCreateStudentWithNumberId() {
            assertThrows(IllegalArgumentException.class, () -> new Student("id", "Ahmet", "Can"));
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
                assertThrows(IllegalArgumentException.class, () -> ahmet.addCourse(null));
                assertThrows(IllegalArgumentException.class, () -> ahmet.addCourse(null), "Throws IllegalArgumentException");
                final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> ahmet.addCourse(null));
                assertEquals("Can't add course with null lecturer course record", illegalArgumentException.getMessage());
            }
        }

    }

    @Nested
    @DisplayName("Drop Course from Student")
    @Tag("dropCourse")
    class DropCourseFromStudent {

        @TestFactory
        Stream<DynamicTest> dropCourseFromStudentFactory() {
            final Student studentAhmet = new Student("id1", "Ahmet", "Yilmaz");

            return Stream.of(
                    DynamicTest.dynamicTest("throws illegal argument exception for null lecturer course record", () -> {
                        assertThrows(IllegalArgumentException.class, () -> studentAhmet.dropCourse(null));
                    }),
                    DynamicTest.dynamicTest("throws illegal argument exception if the student didn't register course before", () -> {
                        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), new Semester());
                        assertThrows(IllegalArgumentException.class, () -> studentAhmet.dropCourse(lecturerCourseRecord));
                    }),
                    DynamicTest.dynamicTest("throws not active semester exception if the semester is not active", () -> {
                        final Semester notActiveSemester = notActiveSemester();
                        assumeTrue(!notActiveSemester.isActive());
                        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), notActiveSemester);
                        Student studentMehmet = new Student("id1", "Mehmet", "Yilmaz", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
                        assertThrows(NotActiveSemesterException.class, () -> studentMehmet.dropCourse(lecturerCourseRecord));
                    }),
                    DynamicTest.dynamicTest("throws not active semester exception if the add drop period is closed for the semester", () -> {
                        final Semester addDropPeriodClosedSemester = addDropPeriodClosedSemester();
                        assumeTrue(!addDropPeriodClosedSemester.isAddDropAllowed());
                        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), addDropPeriodClosedSemester);
                        Student studentMehmet = new Student("id1", "Mehmet", "Yilmaz", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
                        assertThrows(NotActiveSemesterException.class, () -> studentMehmet.dropCourse(lecturerCourseRecord));
                    }),
                    DynamicTest.dynamicTest("drop course from student",
                            () -> {
                                final Semester addDropPeriodOpenSemesters = addDropPeriodOpenSemesters();
                                assumeTrue(addDropPeriodOpenSemesters.isAddDropAllowed());
                                final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), addDropPeriodOpenSemesters);
                                Student studentMehmet = new Student("id1", "Mehmet", "Yilmaz", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
                                assertEquals(1, studentMehmet.getStudentCourseRecords().size());
                                studentMehmet.dropCourse(lecturerCourseRecord);
                                assertTrue(studentMehmet.getStudentCourseRecords().isEmpty());

                            }
                    )
            );
        }

        private Semester addDropPeriodOpenSemesters() {
            final Semester activeSemester = new Semester();
            final LocalDate semesterDate = LocalDate.of(activeSemester.getYear(), activeSemester.getTerm().getStartMonth(), 1);
            final LocalDate now = LocalDate.now();
            activeSemester.setAddDropPeriodInWeek(Long.valueOf(semesterDate.until(now, ChronoUnit.WEEKS)).intValue());
            return activeSemester;
        }

        private Semester addDropPeriodClosedSemester() {
            final Semester activeSemester = new Semester();
            activeSemester.setAddDropPeriodInWeek(0);
            if (LocalDate.now().getDayOfMonth() == 1) {
                activeSemester.setAddDropPeriodInWeek(-1);
            }
            return activeSemester;
        }

        private Semester notActiveSemester() {
            final Semester activeSemester = new Semester();
            return new Semester(LocalDate.of(activeSemester.getYear() - 1, 1, 1));
        }

    }


    @ExtendWith(ParameterResolverForGpaCalculation.class)
    @Nested
    @DisplayName("Calculate Gpa for a Student")
    @Tag("calculateGpa")
    class CalculateGpa {

        @Test
        @DisplayName("Calculate Gpa for a Student")
        void calculateGpa(Student student, Map<LecturerCourseRecord, StudentCourseRecord.Grade> lecturerCourseRecordGradeMap, BigDecimal expected) {


            lecturerCourseRecordGradeMap.forEach((lecturerCourseRecord, grade) -> {
                student.addCourse(lecturerCourseRecord);
                student.addGrade(lecturerCourseRecord, grade);
            });


            assertEquals(expected, student.gpa());

        }

    }


}
