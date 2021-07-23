package com.bayrktlihn.courserecord.application;

import com.bayrktlihn.courserecord.model.Course;
import com.bayrktlihn.courserecord.model.Lecturer;
import com.bayrktlihn.courserecord.model.LecturerRepository;
import com.bayrktlihn.courserecord.model.Semester;

import java.util.Optional;

public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;

    public LecturerServiceImpl(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public Optional<Lecturer> findLecturer(Course course, Semester semester) {
        if(course == null || semester == null){
            throw new IllegalArgumentException("Can't find lecturer without course and semester");
        }
        return lecturerRepository.findByCourseAndSemester(course, semester);
    }
}
