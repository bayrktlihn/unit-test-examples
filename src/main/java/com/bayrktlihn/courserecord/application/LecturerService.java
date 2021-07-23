package com.bayrktlihn.courserecord.application;

import com.bayrktlihn.courserecord.model.Course;
import com.bayrktlihn.courserecord.model.Lecturer;
import com.bayrktlihn.courserecord.model.Semester;

import java.util.Optional;

public interface LecturerService {
    Optional<Lecturer> findLecturer(Course courseFromRepo, Semester semester);
}
