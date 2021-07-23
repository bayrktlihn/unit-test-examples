package com.bayrktlihn.courserecord;

import com.bayrktlihn.courserecord.model.Course;
import com.bayrktlihn.courserecord.model.LecturerCourseRecord;
import com.bayrktlihn.courserecord.model.NotActiveSemesterException;
import com.bayrktlihn.courserecord.model.Semester;
import com.bayrktlihn.courserecord.model.Student;
import com.bayrktlihn.courserecord.model.StudentCourseRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class StudentTestWithParameterResolver {

    @ExtendWith(DropCourseParameterResolver.class)
    @Nested
    @DisplayName("Drop Course from Student")
    @Tag("dropCourse")
    class DropCourseFromStudent {

        final Student studentAhmet;
        final Semester addDropPeriodOpenSemesters;
        final Semester addDropPeriodClosedSemester;
        final Semester notActiveSemester;

        public DropCourseFromStudent(Student studentAhmet, Semester addDropPeriodOpenSemesters, Semester addDropPeriodClosedSemester, Semester notActiveSemester) {
            this.studentAhmet = studentAhmet;
            this.addDropPeriodOpenSemesters = addDropPeriodOpenSemesters;
            this.addDropPeriodClosedSemester = addDropPeriodClosedSemester;
            this.notActiveSemester = notActiveSemester;
        }

        @TestFactory
        Stream<DynamicTest> dropCourseFromStudentFactory() {

            return Stream.of(
                    DynamicTest.dynamicTest("throws illegal argument exception for null lecturer course record", () -> {
                        assertThrows(IllegalArgumentException.class, () -> studentAhmet.dropCourse(null));
                    }),
                    DynamicTest.dynamicTest("throws illegal argument exception if the student didn't register course before", () -> {
                        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), new Semester());
                        assertThrows(IllegalArgumentException.class, () -> studentAhmet.dropCourse(lecturerCourseRecord));
                    }),
                    DynamicTest.dynamicTest("throws not active semester exception if the semester is not active", () -> {
                        final Semester notActiveSemester = this.notActiveSemester;
                        assumeTrue(!notActiveSemester.isActive());
                        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), notActiveSemester);
                        Student studentMehmet = new Student("id1", "Mehmet", "Yilmaz", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
                        assertThrows(NotActiveSemesterException.class, () -> studentMehmet.dropCourse(lecturerCourseRecord));
                    }),
                    DynamicTest.dynamicTest("throws not active semester exception if the add drop period is closed for the semester", () -> {
                        final Semester addDropPeriodClosedSemester = this.addDropPeriodClosedSemester;
                        assumeTrue(!addDropPeriodClosedSemester.isAddDropAllowed());
                        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), addDropPeriodClosedSemester);
                        Student studentMehmet = new Student("id1", "Mehmet", "Yilmaz", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
                        assertThrows(NotActiveSemesterException.class, () -> studentMehmet.dropCourse(lecturerCourseRecord));
                    }),
                    DynamicTest.dynamicTest("drop course from student",
                            () -> {
                                final Semester addDropPeriodOpenSemesters = this.addDropPeriodOpenSemesters;
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


    }


}
