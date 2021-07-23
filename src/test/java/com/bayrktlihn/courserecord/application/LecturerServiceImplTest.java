package com.bayrktlihn.courserecord.application;

import com.bayrktlihn.courserecord.model.Course;
import com.bayrktlihn.courserecord.model.Lecturer;
import com.bayrktlihn.courserecord.model.LecturerRepository;
import com.bayrktlihn.courserecord.model.Semester;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class LecturerServiceImplTest {
    @Test
    void findLecturer() {

        final Course course = new Course("101");
        final Semester semester = new Semester();

        final LecturerRepository lecturerRepository = Mockito.mock(LecturerRepository.class);
        final Lecturer lecturer = new Lecturer();
        Mockito.when(lecturerRepository.findByCourseAndSemester(course, semester)).thenReturn(Optional.of(lecturer));

        final LecturerService lecturerService = new LecturerServiceImpl(lecturerRepository);

        final Optional<Lecturer> lecturerOpt = lecturerService.findLecturer(course, semester);

        assertThat(lecturerOpt)
                .isPresent()
                .get()
                .isSameAs(lecturer)
//                .isNotPresent()
        ;

        Mockito.verify(lecturerRepository).findByCourseAndSemester(course, semester);
    }
}