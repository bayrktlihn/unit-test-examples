package com.bayrktlihn.courserecord.model;

import java.util.Optional;

public interface StudentRepository {
    Optional<Student> findById(String id);


    Student save(Student student);


}



