package com.bayrktlihn.courserecord.application;

import com.bayrktlihn.courserecord.model.Course;

import java.util.Optional;

public interface CourseService {
    Optional<Course> findCourse(Course course);
}
