package com.bayrktlihn.courserecord;

import com.bayrktlihn.courserecord.model.Course;
import com.bayrktlihn.courserecord.model.Department;
import com.bayrktlihn.courserecord.model.LecturerCourseRecord;
import com.bayrktlihn.courserecord.model.NoCourseFoundForStudentException;
import com.bayrktlihn.courserecord.model.Semester;
import com.bayrktlihn.courserecord.model.Student;
import com.bayrktlihn.courserecord.model.UniversityMember;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.in;
import static org.assertj.core.api.Assertions.tuple;

public class StudentTestWithAssertJAssertions {

    @Test
    void createStudent() {
        final Student student = new Student("id1", "Alihan", "Bayraktar");

        assertThat(student.getName()).as("Student's name %s ", student.getName())
                .isNotBlank()
                .isNotEmpty()
//                .doesNotContainOnlyWhitespaces();
                .matches("^A\\w{4}n$")
                .hasSize(6)
                .contains("iha")
                .contains(List.of("Ali", "han"))
                .endsWith("han")
                .doesNotEndWith("Ali")
                .doesNotStartWith("han")
                .startsWith("Ali")
                .isNotIn("Ahmet", "İlknur")
                .isIn("Alihan", "Merve", "Deniz")
                .isEqualToIgnoringCase("alihan")
                .isEqualTo("Alihan");
    }

    @Test
    void addCourseToStudent() {
        final Student ahmet = new Student("id1", "Ahmet", "Yilmaz", LocalDate.of(1990, 1, 1));
        final Student mehmet = new Student("id2", "Mehmet", "Kural", LocalDate.of(1992, 1, 1));
        final Student canan = new Student("id3", "Canan", "Sahin", LocalDate.of(1995, 1, 1));
        final Student elif = new Student("id4", "Elif", "Oz", LocalDate.of(1991, 1, 1));
        final Student hasan = new Student("id5", "Hasan", "Kartal", LocalDate.of(1990, 1, 1));
        final Student alihan = new Student("id6", "Alihan", "Bayraktar", LocalDate.of(1995, 1, 1));

        final List<Student> students = List.of(ahmet, mehmet, hasan, elif, canan);

        assertThat(students).as("Student's List")
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .contains(ahmet, mehmet)
                .containsOnly(mehmet, canan, ahmet, elif, hasan)
                .containsExactly(ahmet, mehmet, hasan, elif, canan)
                .containsExactlyInAnyOrder(mehmet, canan, ahmet, elif, hasan)
        ;

        assertThat(students)
                .filteredOn(student -> student.getBirthDate().until(LocalDate.now(), ChronoUnit.YEARS) >= 30)
                .hasSize(3)
                .containsOnly(ahmet, elif, hasan)
        ;


        assertThat(students)
                .filteredOn(new Condition<>() {
                    @Override
                    public boolean matches(Student student) {
                        return student.getBirthDate().until(LocalDate.now(), ChronoUnit.YEARS) >= 30;
                    }
                })
                .hasSize(3)
                .containsOnly(ahmet, elif, hasan)
        ;

        assertThat(students)
                .filteredOn("birthDate", in(LocalDate.of(1990, 1, 1)))
                .hasSize(2)
                .containsOnly(hasan, ahmet);

        assertThat(students)
                .extracting(Student::getName)
                .filteredOn(name -> name.contains("e"))
                .hasSize(2)
                .containsOnly("Ahmet", "Mehmet");

        assertThat(students)
                .filteredOn(student -> student.getName().contains("e"))
                .extracting(Student::getName, Student::getSurname)
                .containsOnly(tuple("Ahmet", "Yilmaz"), tuple("Mehmet", "Kural"));

        final LecturerCourseRecord lecturerCourseRecord101 = new LecturerCourseRecord(new Course("101"), new Semester());
        final LecturerCourseRecord lecturerCourseRecord103 = new LecturerCourseRecord(new Course("103"), new Semester());
        final LecturerCourseRecord lecturerCourseRecord105 = new LecturerCourseRecord(new Course("105"), new Semester());

        ahmet.addCourse(lecturerCourseRecord101);
        ahmet.addCourse(lecturerCourseRecord103);

        canan.addCourse(lecturerCourseRecord101);
        canan.addCourse(lecturerCourseRecord103);
        canan.addCourse(lecturerCourseRecord105);

        assertThat(students)
                .filteredOn(student -> student.getName().equals("Ahmet") || student.getName().equals("Canan"))
                .flatExtracting(Student::getStudentCourseRecords)
                .hasSize(5)
                .filteredOn(studentCourseRecord -> studentCourseRecord.getLecturerCourseRecord().getCourse().getCode().equals("101"))
                .hasSize(2);


    }

    @Test
    void anotherCreateStudentTest() {

        final Department department = new Department();

        final Student ahmet = new Student("id1", "Ahmet", "Yilmaz", LocalDate.of(1990, 1, 1));
        ahmet.setDepartment(department);
        final Student mehmet = new Student("id2", "Mehmet", "Yilmaz", LocalDate.of(1995, 1, 1));
        mehmet.setDepartment(department);

        assertThat(ahmet).as("Check student ahmet info")
                .isNotNull()
                .hasSameClassAs(mehmet)
                .isExactlyInstanceOf(Student.class)
                .isInstanceOf(UniversityMember.class)
                .isNotEqualTo(mehmet)
                .isEqualToComparingOnlyGivenFields(mehmet, "surname")
                .isEqualToIgnoringGivenFields(mehmet, "id", "name", "birthDate")
                .matches(student -> student.getBirthDate().getYear() == 1990)
                .hasFieldOrProperty("name")
                .hasNoNullFieldsOrProperties()
                .extracting(Student::getName, Student::getSurname)
                .containsOnly("Ahmet", "Yilmaz")
        ;

        StudentAssert.assertThat(mehmet).as("Student mehmet info check")
                .hasName("Mehmet");

    }

    @Test
    void addCourseToStudentWithExceptionalScenarios() {
        final Student student = new Student("id1", "Ahmet", "Yilmaz");

        assertThatThrownBy(() -> student.addCourse(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Can't add course")
                .hasStackTraceContaining("Student");

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> student.addCourse(null))
                .withMessageContaining("Can't add course")
                .withNoCause();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> student.addCourse(null))
                .withMessageContaining("Can't add course")
                .withNoCause();

        assertThatCode(() -> student.addCourse(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Can't add course");

        assertThatCode(() -> student.addCourse(new LecturerCourseRecord(new Course("101"), new Semester())))
                .doesNotThrowAnyException();

        assertThatThrownBy(() -> student.addGrade(null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasCauseExactlyInstanceOf(NoCourseFoundForStudentException.class)
        ;

        final Throwable throwable = catchThrowable(() -> student.addCourse(null));
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Can't add course");

    }

}
