package ru.nsu.malov;

import java.util.ArrayList;
import java.util.Collection;

public class CreditBook extends Semester {
    private int group;
    private String name;
    private static int gradeOfQualifiedWork;
    final private int SEMESTERS = 9;
    private final Semester[] semester = new Semester[SEMESTERS];


    public CreditBook(int group, String name, int gradeOfWork) {
        this.group = group;
        this.name = name;
        gradeOfQualifiedWork = gradeOfWork;
        for (int i = 1; i < SEMESTERS; i++) {
            Semester sem = new Semester();
            semester[i] = sem;
        }
    }

    public void setGradeOfWork(int grade) {
        this.gradeOfQualifiedWork = grade;
    }

    public void addGrades(int sem, int grade, String subject) {
        semester[sem].addGrade(grade, subject);
    }

    public ArrayList<Integer> getAllGrades() {
        ArrayList<Integer> grades = new ArrayList<>();
        for (int i = 1; i < SEMESTERS; i++) {
            grades.addAll(semester[i].getGrades());
        }
        return grades;
    }

    public static double averageScore(ArrayList<Integer> grades) {
        double avgScore = 0;
        for (int grade :
                grades
        ) {
            avgScore += grade;
        }
        avgScore /= grades.size();
        return avgScore;
    }

    public Collection<Integer> getSemGrades(int sem) {
        return semester[sem].getGrades();
    }

    public static boolean redDiploma(CreditBook creditBook) {
        ArrayList<Integer> grades = creditBook.getAllGrades();
        if (grades.stream().anyMatch(i -> i < 4)) {
            return false;
        }
        return !(averageScore(grades) < 4.5) && (gradeOfQualifiedWork == 5);
    }

    public boolean scholarship(CreditBook creditBook, int semester) {
        Collection<Integer> grades = creditBook.getSemGrades(semester);
        for (int grade : grades
        ) {
            if (grade < 4) {
                return false;
            }

        }
        return true;
    }

}
