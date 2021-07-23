package com.bayrktlihn.courserecord.application;

import com.bayrktlihn.courserecord.model.Course;
import com.bayrktlihn.courserecord.model.Lecturer;
import com.bayrktlihn.courserecord.model.LecturerCourseRecord;
import com.bayrktlihn.courserecord.model.Semester;
import com.bayrktlihn.courserecord.model.Student;
import com.bayrktlihn.courserecord.model.StudentCourseRecord;
import com.bayrktlihn.courserecord.model.StudentRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {
    private final CourseService courseService;
    private final LecturerService lecturerService;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(CourseService courseService, LecturerService lecturerService, StudentRepository studentRepository) {
        this.courseService = courseService;
        this.lecturerService = lecturerService;
        this.studentRepository = studentRepository;
    }


    @Override
    public void addCourse(String studentId, Course course) {
        addCourse(studentId, course, new Semester());
    }

    @Override
    public void addCourse(String studentId, Course course, Semester semester) {
        final Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Can't find a student with id<%s>", studentId)));

        final Course courseFromRepo = courseService.findCourse(course)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Can't find a course with info<%s>", course)));

        final Lecturer lecturer = lecturerService.findLecturer(courseFromRepo, semester)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Can't find a lecturer with info<%s>", course)));

        final LecturerCourseRecord lecturerCourseRecord = lecturer.lecturerCourseRecord(courseFromRepo, semester);

        student.addCourse(lecturerCourseRecord);
        studentRepository.save(student);


    }

    @Override
    public void dropCourse(String studentId, Course course) {

    }

    @Override
    public void addGrade(String studentId, Course course, StudentCourseRecord.Grade grade) {

    }

    @Override
    public boolean isTakeCourse(String studentId, Course course) {
        return false;
    }

    @Override
    public BigDecimal gpa(String studentId) {
        return null;
    }

    @Override
    public Optional<Student> findStudent(String studentId) {
        return studentRepository.findById(studentId);
    }
}
