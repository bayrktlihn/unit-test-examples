package com.bayrktlihn.courserecord.model;

public class LecturerCourseRecord {

    private Lecturer lecturer;

    private final Course course;
    private final Semester semester;
    private int credit;

    public LecturerCourseRecord(Course course, Semester semester) {
        this.course = course;
        this.semester = semester;

    }

    public LecturerCourseRecord() {
        this(null, null);
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Lecturer getLecturer() {
        return this.lecturer;
    }

    public Course getCourse() {
        return course;
    }

    public Semester getSemester() {
        return semester;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
