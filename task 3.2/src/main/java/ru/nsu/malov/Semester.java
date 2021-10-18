package ru.nsu.malov;

import java.util.*;

public class Semester {
    TreeMap<String, Integer> semester = new TreeMap<>();

    public void addGrade(int grade, String subject) {
        this.semester.put(subject, grade);
    }

    public Collection<Integer> getGrades() {
        return semester.values();
    }
}
