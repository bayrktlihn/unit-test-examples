package com.bayrktlihn.courserecord.application;

import com.bayrktlihn.courserecord.model.Course;
import com.bayrktlihn.courserecord.model.Lecturer;
import com.bayrktlihn.courserecord.model.LecturerCourseRecord;
import com.bayrktlihn.courserecord.model.Semester;
import com.bayrktlihn.courserecord.model.Student;
import com.bayrktlihn.courserecord.model.StudentRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentServiceImplTest {
    @Test
    void addCourse() {


        final Student student = new Student("id1", "Ahmet", "Yilmaz");
        final Course course = new Course("101");
        final Semester semester = new Semester();
        final Lecturer lecturer = mock(Lecturer.class);

        final CourseService courseService = mock(CourseService.class);
        final LecturerService lecturerService = mock(LecturerService.class);
        final StudentRepository studentRepository = mock(StudentRepository.class);


        when(studentRepository.findById(anyString()))
                .thenReturn(Optional.of(student))
                .thenThrow(new IllegalArgumentException("Can't find a student"));

        when(courseService.findCourse(any())).thenReturn(Optional.of(course));
        when(lecturer.lecturerCourseRecord(course, semester)).thenReturn(new LecturerCourseRecord(course, semester));
        when(lecturerService.findLecturer(course, semester)).thenReturn(Optional.of(lecturer));
//
        final StudentServiceImpl studentService = new StudentServiceImpl(courseService, lecturerService, studentRepository);

        studentService.addCourse("id1", course, semester);

        assertThatThrownBy(() -> studentService.findStudent("id1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Can't find a student");
//
//        final Optional<Student> studentOptional = studentService.findStudent("id1");
//
//        assertThat(studentOptional)
//                .isPresent()
//                .get()
//                .matches(tempStudent -> tempStudent.isTakeCourse(course));
    }
}