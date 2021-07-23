package com.bayrktlihn.courserecord.model;

public class Course {
    private String code;
    private String name;
    private String description;
    private CourseType courseType;
    private int credit;
    private Department department;


    public Course() {
    }

    public Course(String code) {
        this.code = code;
    }

    public static Course createNewCourse(String courseCode) {
        return new Course(courseCode);
    }


    public enum CourseType {
        MANDATORY,
        ELECTIVE
    }

    public static CourseBuilder newCourse() {
        return new CourseBuilder(new Course());
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {

        if (!Course.class.isInstance(o)) {
            return false;
        }

        Course course = (Course) o;
        return course.code.equals(code);
    }

    private static class CourseBuilder {
        private Course course;

        CourseBuilder(Course course) {
            this.course = course;
        }


    }


}
