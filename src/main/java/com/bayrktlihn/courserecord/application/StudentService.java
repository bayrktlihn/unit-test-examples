package com.bayrktlihn.courserecord.application;

import com.bayrktlihn.courserecord.model.Course;
import com.bayrktlihn.courserecord.model.Semester;
import com.bayrktlihn.courserecord.model.Student;
import com.bayrktlihn.courserecord.model.StudentCourseRecord;

import java.math.BigDecimal;
import java.util.Optional;

public interface StudentService {

    void addCourse(String studentId, Course course);

    void addCourse(String studentId, Course course, Semester semester);

    void dropCourse(String studentId, Course course);

    void addGrade(String studentId, Course course, StudentCourseRecord.Grade grade);

    boolean isTakeCourse(String studentId, Course course);

    BigDecimal gpa(String studentId);


    Optional<Student> findStudent(String studentId);
}
