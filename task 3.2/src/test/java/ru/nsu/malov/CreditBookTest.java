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
        double i = l3xxxa.averageGrade(grades);
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
        Assertions.assertFalse(l3xxxa.redDiploma(l3xxxa));
        l3xxxa.setGradeOfWork(4);
        Assertions.assertFalse(l3xxxa.redDiploma(l3xxxa));
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
        double i = l3xxxa.averageGrade(grades);
        Assertions.assertEquals(4.8, i, 0.1);
        Assertions.assertTrue(goodStudent.increasedScholarship(2));
        Assertions.assertTrue(goodStudent.redDiploma(goodStudent));
        goodStudent.setGradeOfWork(4);
        Assertions.assertFalse(goodStudent.redDiploma(goodStudent));
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
    public void allMethods_newStudent() {
        CreditBook easyMoneySniper = new CreditBook(0, null, 0);
        easyMoneySniper.addGroup(20214);
        easyMoneySniper.addName("Kevin Durant");
        easyMoneySniper.setGradeOfWork(4);
        easyMoneySniper.addGrades(1, 5, "Dribbling");
        easyMoneySniper.addGrades(1, 5, "Shooting");
        easyMoneySniper.addGrades(1, 5, "Positioning");
        easyMoneySniper.addGrades(1, 4, "Passing");
        ArrayList<Integer> grades = easyMoneySniper.getAllGrades();
        ArrayList<Integer> gradesExpected = new ArrayList<>(Arrays.asList(5, 4, 5, 5));
        Assertions.assertEquals(easyMoneySniper.showSem(), 1);
        Assertions.assertEquals(easyMoneySniper.averageGrade(grades), 4.75, 0.1);
        Assertions.assertFalse(easyMoneySniper.redDiploma(easyMoneySniper));
        Assertions.assertEquals(grades, gradesExpected);
        Assertions.assertTrue(easyMoneySniper.increasedScholarship(1));
        Assertions.assertTrue(easyMoneySniper.scholarship(1));
        Assertions.assertTrue(easyMoneySniper.scholarshipByJetBrains(1));
        Assertions.assertFalse(easyMoneySniper.increasedScholarshipByJetBrains(1));
        Assertions.assertEquals(easyMoneySniper.showGroup(), 20214);
        Assertions.assertEquals(easyMoneySniper.showGradeOfQualifyingWork(), 4);
        Assertions.assertEquals(easyMoneySniper.showName(), "Kevin Durant");
    }

}