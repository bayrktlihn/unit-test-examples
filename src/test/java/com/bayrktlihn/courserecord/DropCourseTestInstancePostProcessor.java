package com.bayrktlihn.courserecord;

import com.bayrktlihn.courserecord.model.Semester;
import com.bayrktlihn.courserecord.model.Student;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

public class DropCourseTestInstancePostProcessor implements TestInstancePostProcessor {

    private static Logger logger = Logger.getLogger(DropCourseTestInstancePostProcessor.class.getName());

    @Override
    public void postProcessTestInstance(Object o, ExtensionContext extensionContext) throws Exception {
        logger.info("Student Ahmet is provided");
        final StudentTestWithTestInstancePostProcessor.DropCourseFromStudent dropCourseFromStudent = (StudentTestWithTestInstancePostProcessor.DropCourseFromStudent) o;
        dropCourseFromStudent.studentAhmet = new Student("id1", "Ahmet", "Yilmaz");
        dropCourseFromStudent.addDropPeriodClosedSemester = addDropPeriodClosedSemester();
        dropCourseFromStudent.addDropPeriodOpenSemesters = addDropPeriodOpenSemesters();
        dropCourseFromStudent.notActiveSemester = notActiveSemester();
    }

    private Semester addDropPeriodOpenSemesters() {
        final Semester activeSemester = new Semester();
        final LocalDate semesterDate = LocalDate.of(activeSemester.getYear(), activeSemester.getTerm().getStartMonth(), 1);
        final LocalDate now = LocalDate.now();
        activeSemester.setAddDropPeriodInWeek(Long.valueOf(semesterDate.until(now, ChronoUnit.WEEKS)).intValue());
        return activeSemester;
    }

    private Semester addDropPeriodClosedSemester() {
        final Semester activeSemester = new Semester();
        activeSemester.setAddDropPeriodInWeek(0);
        if (LocalDate.now().getDayOfMonth() == 1) {
            activeSemester.setAddDropPeriodInWeek(-1);
        }
        return activeSemester;
    }

    private Semester notActiveSemester() {
        final Semester activeSemester = new Semester();
        return new Semester(LocalDate.of(activeSemester.getYear() - 1, 1, 1));
    }
}
