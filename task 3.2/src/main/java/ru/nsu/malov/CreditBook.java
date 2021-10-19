package ru.nsu.malov;

import java.util.ArrayList;
import java.util.Collection;

public class CreditBook{
    private final int group;
    private final String name;
    private static int gradeOfQualifiedWork;
    final private int SEMESTERS = 8;
    private final Semester[] semester = new Semester[SEMESTERS + 1];

    public CreditBook(int group, String name, int gradeOfWork) {
        this.group = group;
        this.name = name;
        gradeOfQualifiedWork = gradeOfWork;
        for (int i = 1; i < SEMESTERS + 1; i++) {
            Semester sem = new Semester();
            semester[i] = sem;
        }
    }

    public void setGradeOfWork(int grade) {
        gradeOfQualifiedWork = grade;
    }

    public void addGrades(int sem, int grade, String subject) {
        semester[sem].addGrade(grade, subject);
    }

    public ArrayList<Integer> getAllGrades() {
        ArrayList<Integer> grades = new ArrayList<>();
        for (int i = 1; i < SEMESTERS + 1; i++) {
            grades.addAll(semester[i].getGrades());
        }
        return grades;
    }

    public static double averageScore(ArrayList<Integer> grades) {
        double avgScore = 0;
        for (int grade : grades) {
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
        for (int grade : grades) {
            if (grade < 4) {
                return false;
            }
        }
        return !(averageScore(grades) < 4.5) && (gradeOfQualifiedWork == 5);
    }

    public boolean scholarship(CreditBook creditBook, int semester) {
        Collection<Integer> grades = creditBook.getSemGrades(semester);
        int fours = 0;
        for (int grade : grades) {
            if (grade < 4) {
                return false;
            }
            if (grade == 4) {
                fours++;
            }

        }
        return fours < 3;
    }

}
