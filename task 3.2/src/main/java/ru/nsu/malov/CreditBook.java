package ru.nsu.malov;

import java.util.ArrayList;
import java.util.Collection;

public class CreditBook {
    /**
     * Fields:
     * group - number of student's group
     * name - student's name
     * sem - number of semester
     * gradeOfQualifyingWork - student's grade of qualifying work
     * Constants:
     * SEMESTERS - amount of semesters
     * semester - array for all semesters
     */
    private int group;
    private String name;
    private int gradeOfQualifyingWork;
    private int sem;
    private final int SEMESTERS = 8;
    private final Semester[] semester = new Semester[SEMESTERS];

    /**
     * Constructor - new credit book
     *
     * @param group       - student's group
     * @param name        - student's name
     * @param gradeOfWork - student's grade of qualifying work
     */
    public CreditBook(int group, String name, int gradeOfWork) {
        this.group = group;
        this.name = name;
        gradeOfQualifyingWork = gradeOfWork;
        for (int i = 0; i < SEMESTERS; i++) {
            semester[i] = new Semester();
        }
    }

    /**
     * Method to show group number
     *
     * @return group number
     */
    public int showGroup() {
        return group;
    }

    /**
     * Method to show number of semesters
     */
    public int showSem() {
        return sem;
    }

    /**
     * Method to show student's name
     *
     * @return student's name
     */
    public String showName() {
        return name;
    }

    /**
     * Method to show student's grade of the qualifying work
     *
     * @return student's grade of the qualifying work
     */
    public int showGradeOfQualifyingWork() {
        return gradeOfQualifyingWork;
    }

    /**
     * Method to add group number
     *
     * @param group - group number
     */
    public void addGroup(int group) {
        this.group = group;
    }

    /**
     * Method to add student's name
     *
     * @param name - student's name
     */
    public void addName(String name) {
        this.name = name;
    }

    /**
     * Method to set grade of qualifying work
     *
     * @param grade - grade of qualifying work
     */
    public void setGradeOfWork(int grade) {
        gradeOfQualifyingWork = grade;
    }

    /**
     * Method to add all grades in semester by subject
     *
     * @param sem - number of semester
     */
    public void addGrades(int sem, int grade, String subject) {
        this.sem = sem;
        semester[sem].addGrade(grade, subject);
    }

    /**
     * Method to get all student's grades
     *
     * @return arraylist with all grades
     */
    public ArrayList<Integer> getAllGrades() {
        ArrayList<Integer> grades = new ArrayList<>();
        for (int i = 0; i < SEMESTERS; i++) {
            grades.addAll(semester[i].getGrades());
        }
        return grades;
    }

    /**
     * Method to find an average grade
     *
     * @param grades - arraylist with all student's grades.
     * @return average student's grade.
     */
    public double averageGrade(ArrayList<Integer> grades) {
        double avgScore = 0;
        for (int grade : grades) {
            avgScore += grade;
        }
        avgScore /= grades.size();
        return avgScore;
    }

    /**
     * Collection with all grades in semester
     *
     * @return all grades in semester.
     */
    public Collection<Integer> getSemGrades(int sem) {
        return semester[sem].getGrades();
    }

    /**
     * Method to define can student have a red diploma
     *
     * @param creditBook - Student's credit book
     * @return true if student will have a red diploma
     */
    public boolean redDiploma(CreditBook creditBook) {
        ArrayList<Integer> grades = creditBook.getAllGrades();
        int fives = 0;
        for (int grade : grades) {
            if (grade < 4) {
                return false;
            }
            fives++;
        }
        return (double) (fives / grades.size()) >= 0.75 && (gradeOfQualifyingWork == 5);
    }

    /**
     * Method to define can student have an increased scholarship
     *
     * @param semester - number of semester
     * @return true if student will have an increased scholarship
     */
    public boolean increasedScholarship(int semester) {
        Collection<Integer> grades = getSemGrades(semester);
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

    /**
     * Method to define can student have a scholarship
     *
     * @param semester - number of semester
     * @return true if student will have a scholarship
     */
    public boolean scholarship(int semester) {
        Collection<Integer> grades = getSemGrades(semester);
        for (int grade : grades) {
            if (grade < 4) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to define can student have an increased scholarship by JetBrains
     *
     * @return true if student will have an increased scholarship by JetBrains
     */
    public boolean increasedScholarshipByJetBrains() {
        Collection<Integer> grades = getSemGrades(sem);
        if (sem>2){
            return false;
        }

        for (int grade : grades) {
            if (grade < 5) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to define can student have a scholarship by JetBrains
     *
     * @return true if student will have a scholarship by JetBrains
     */
    public boolean scholarshipByJetBrains() {
        if (sem>2){
            return false;
        }
        return increasedScholarship(sem);
    }
}
