package ru.nsu.malov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


class CreditBookTest {
    CreditBook l3xxxa;

    @BeforeEach
    private void setStudent() {
        l3xxxa = new CreditBook(20214, "Malov A.Y.", 0);
        l3xxxa.addGrades(1, 4, "MathAn");
        l3xxxa.addGrades(1, 5, "Discrete Maths");
        l3xxxa.addGrades(1, 4, "Declarative programming");
        l3xxxa.addGrades(1, 5, "History");
        l3xxxa.addGrades(1, 5, "OKR");
        l3xxxa.addGrades(1, 3, "Imperative programming");
        l3xxxa.addGrades(2, 3, "MathAn");
        l3xxxa.addGrades(2, 3, "Discrete Maths");
        l3xxxa.addGrades(2, 5, "Declarative programming");
        l3xxxa.addGrades(2, 4, "Imperative programming");
        l3xxxa.addGrades(2, 5, "English");
        l3xxxa.addGrades(2, 4, "Digital platforms");
    }

    @Test
    public void averageScore_myAverageGrade() {
        ArrayList<Integer> grades = l3xxxa.getAllGrades();
        double i = l3xxxa.averageGrade();
        Assertions.assertEquals(i, 4.1, 0.1);
    }

    @Test
    public void showName_showGroup_showGradeOfQualifyingWork_myName() {
        String name = l3xxxa.showName();
        int group = l3xxxa.showGroup();
        int grade = l3xxxa.showGradeOfQualifyingWork();
        Assertions.assertEquals(group, 20214);
        Assertions.assertEquals(grade, 0);
        Assertions.assertEquals(name, "Malov A.Y.");
    }

    @Test
    public void redDiploma_forMe() {
        l3xxxa.setGradeOfWork(5);
        Assertions.assertFalse(l3xxxa.redDiploma());
        l3xxxa.setGradeOfWork(4);
        Assertions.assertFalse(l3xxxa.redDiploma());
    }

    @Test
    public void scholarship_forMe() {
        Assertions.assertFalse(l3xxxa.increasedScholarship(2));
    }

    @Test
    public void averageGrade_redDiploma_scholarship_goodStudent() {
        CreditBook goodStudent = new CreditBook(20214, "Zhmishenko V.A.", 0);
        goodStudent.addGrades(1, 4, "MathAn");
        goodStudent.addGrades(1, 5, "Discrete Maths");
        goodStudent.addGrades(1, 4, "Declarative programming");
        goodStudent.addGrades(1, 5, "History");
        goodStudent.addGrades(1, 5, "OKR");
        goodStudent.addGrades(1, 5, "Imperative programming");
        goodStudent.addGrades(2, 5, "MathAn");
        goodStudent.addGrades(2, 5, "Discrete Maths");
        goodStudent.addGrades(2, 5, "Declarative programming");
        goodStudent.addGrades(2, 5, "Imperative programming");
        goodStudent.addGrades(2, 5, "English");
        goodStudent.addGrades(2, 4, "Digital platforms");
        goodStudent.setGradeOfWork(5);
        ArrayList<Integer> grades = goodStudent.getAllGrades();
        double i = goodStudent.averageGrade();
        Assertions.assertEquals(4.8, i, 0.1);
        Assertions.assertTrue(goodStudent.increasedScholarship(2));
        Assertions.assertTrue(goodStudent.redDiploma());
        goodStudent.setGradeOfWork(4);
        Assertions.assertFalse(goodStudent.redDiploma());
    }

    @Test
    public void scholarship_goodStudent() {
        CreditBook goodStudent = new CreditBook(20214, "Zhmishenko V.A.", 0);
        goodStudent.addGrades(1, 4, "MathAn");
        goodStudent.addGrades(1, 4, "Discrete Maths");
        goodStudent.addGrades(1, 4, "Declarative programming");
        goodStudent.addGrades(1, 5, "History");
        goodStudent.addGrades(1, 5, "OKR");
        goodStudent.addGrades(1, 5, "Imperative programming");
        Assertions.assertFalse(goodStudent.increasedScholarship(1));
    }

    @Test
    public void addName_showName_newStudent(){
        CreditBook newStudent = new CreditBook(0, null, 0);
        newStudent.addName("Kim Jong Un");
        Assertions.assertEquals(newStudent.showName(), "Kim Jong Un");
    }

    @Test
    public void setGrade_showGrade_newStudent(){
        l3xxxa.setGradeOfWork(4);
        Assertions.assertEquals(l3xxxa.showGradeOfQualifyingWork(), 4);
        l3xxxa.setGradeOfWork(5);
        Assertions.assertEquals(l3xxxa.showGradeOfQualifyingWork(), 5);
    }

    @Test
    public void addGrades_showGrades_newStudent(){
        CreditBook goodStudent = new CreditBook(20214, "Zhmishenko V.A.", 0);
        goodStudent.addGrades(3, 5, "EASY TO CLAIM MONEY SMTH");
        goodStudent.addGrades(3, 5, "SMTH NOT INTERESTING");
        goodStudent.addGrades(3, 5, "GOOD SMTH");
        goodStudent.addGrades(3, 5, "SMTH");
        ArrayList<Integer> grades = goodStudent.getAllGrades();
        ArrayList<Integer> expectedGrades = new ArrayList<>();
        expectedGrades.addAll(Arrays.asList(5, 5, 5, 5));
        Assertions.assertEquals(expectedGrades, grades);
    }

    @Test
    public void scholarship_increasedScholarship_newStudent(){
        Assertions.assertFalse(l3xxxa.scholarship(1));
        Assertions.assertFalse(l3xxxa.increasedScholarship(1));
        l3xxxa.addGrades(3, 5, "Subject1");
        l3xxxa.addGrades(3, 4, "Subject2");
        l3xxxa.addGrades(3, 4, "Subject3");
        Assertions.assertTrue(l3xxxa.scholarship(3));
        Assertions.assertTrue(l3xxxa.increasedScholarship(3));
    }
    @Test
    public void addGrades_getSemGrades_newStudent(){
        CreditBook goodStudent = new CreditBook(20214, "Zhmishenko V.A.", 0);
        goodStudent.addGrades(3, 5, "EASY TO CLAIM MONEY SMTH");
        goodStudent.addGrades(3, 5, "SMTH NOT INTERESTING");
        goodStudent.addGrades(3, 5, "GOOD SMTH");
        goodStudent.addGrades(3, 5, "SMTH");
        goodStudent.addGrades(4, 4, "SMTH IN 4TH SEMESTER");
        ArrayList<Integer> grades = goodStudent.getSemGrades(3);
        ArrayList<Integer> expectedGrades = new ArrayList<>();
        expectedGrades.addAll(Arrays.asList(5, 5, 5, 5));
        Assertions.assertEquals(grades, expectedGrades);
        expectedGrades.clear();
        grades.clear();
        grades = goodStudent.getSemGrades(4);
        expectedGrades.add(4);
        Assertions.assertEquals(grades, expectedGrades);

    }

    @Test
    public void scholarshipByJetBrains_forThirdSemester() {
        CreditBook goodStudent = new CreditBook(20214, "Zhmishenko V.A.", 0);
        goodStudent.addGrades(3, 5, "EASY TO CLAIM MONEY SMTH");
        goodStudent.addGrades(3, 5, "SMTH NOT INTERESTING");
        goodStudent.addGrades(3, 5, "GOOD SMTH");
        goodStudent.addGrades(3, 5, "SMTH");
        Assertions.assertFalse(l3xxxa.increasedScholarshipByJetBrains());
        Assertions.assertFalse(l3xxxa.scholarshipByJetBrains());
    }
}