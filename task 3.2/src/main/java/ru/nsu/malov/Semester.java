package ru.nsu.malov;

import java.util.*;

public class Semester {
    /**
     * Field:
     * semester - mapping of subject and grade
     */
    private Map<String, Integer> semester = new HashMap<>();

    /**
     * Method to add grade by subject
     *
     * @param grade   - grade
     * @param subject - subject
     */
    public void addGrade(int grade, String subject) {
        this.semester.put(subject, grade);
    }

    /**
     * Method to get all grades
     *
     * @return all semester grades
     */
    public Collection<Integer> getGrades() {
        return semester.values();
    }
}
