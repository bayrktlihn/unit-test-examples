package com.bayrktlihn.courserecord;

import com.bayrktlihn.courserecord.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentTestWithTestLifecycle {
    private Student mehmet = new Student("id1", "Mehmet", "Yilmaz");

    @BeforeAll
    void setUp() {

    }

    @Test
    void stateCannotChangeWhenLifecycleIsPerMethod() {
        assertEquals("Mehmet", mehmet.getName());
        mehmet = new Student("id2", "Ahmet", "Yilmaz");
    }

    @Test
    void stateCanChangeWhenLifecycleIsPerMethod() {
        assertEquals("Mehmet", mehmet.getName());
        mehmet = new Student("id2", "Ahmet", "Yilmaz");
    }
}
