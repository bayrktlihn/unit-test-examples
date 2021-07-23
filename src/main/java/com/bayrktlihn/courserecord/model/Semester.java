package com.bayrktlihn.courserecord.model;

import java.time.LocalDate;
import java.util.Objects;

public class Semester {
    private final int year;
    private final Term term;
    private int addDropPeriodInWeek = 2;

    public Semester(LocalDate localDate) {
        this.year = localDate.getYear();
        this.term = term(localDate.getMonthValue());
    }

    public Semester() {
        final LocalDate now = LocalDate.now();
        this.year = now.getYear();
        this.term = term(now.getMonthValue());
    }

    private Term term(int monthValue) {

        if (monthValue >= Term.FALL.getStartMonth() || monthValue < Term.SPRING.getStartMonth()) {
            return Term.FALL;
        } else if (monthValue >= Term.SPRING.getStartMonth() && monthValue < Term.SUMMER.getStartMonth()) {
            return Term.SPRING;
        }

        return Term.SUMMER;
    }


    public boolean isActive() {
        return this.equals(new Semester());
    }

    public void setAddDropPeriodInWeek(int addDropPeriodInWeek) {
        this.addDropPeriodInWeek = addDropPeriodInWeek;
    }

    public boolean isAddDropAllowed() {
        if (!isActive()) {
            return false;
        }
        final LocalDate endOfAddDropPeriod = LocalDate.of(getYear(), getTerm().getStartMonth(), 1).plusWeeks(addDropPeriodInWeek);

        return !LocalDate.now().isAfter(endOfAddDropPeriod);
    }

    public enum Term {
        FALL(9), SPRING(2), SUMMER(6);

        private int startMonth;


        Term(int startMonth) {
            this.startMonth = startMonth;
        }

        public int getStartMonth() {
            return startMonth;
        }
    }

    public int getYear() {
        return year;
    }

    public Term getTerm() {
        return term;
    }

    @Override
    public String toString() {
        return this.getTerm().name() + " of " + this.getYear();
    }

    @Override
    public boolean equals(Object o) {

        if (!Semester.class.isInstance(o))
            return false;

        if (this == o) return true;
        Semester semester = (Semester) o;
        return year == semester.year && term == semester.term;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, term);
    }
}
