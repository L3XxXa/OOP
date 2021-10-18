package ru.nsu.malov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;


class CreditBookTest {
    CreditBook l3xxxa;

    @BeforeEach
    private void setStudent() {
        l3xxxa = new CreditBook(20214, "Malov A.Y.", "FIT", "CS and SD", 0);
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
    public void averageScore_myAverageScore() {
        ArrayList<Integer> grades = l3xxxa.getAllGrades();
        double i = CreditBook.averageScore(grades);
        String result;
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        result = decimalFormat.format(i);
        Assertions.assertEquals("4.2", result);
    }

    @Test
    public void redDiploma_forMe() {
        l3xxxa.setGradeOfWork(5);
        boolean t;
        t = CreditBook.redDiploma(l3xxxa);
        Assertions.assertFalse(t);
        l3xxxa.setGradeOfWork(4);
        t = CreditBook.redDiploma(l3xxxa);
        Assertions.assertFalse(t);
    }

    @Test
    public void scholarship_forMe() {
        Assertions.assertFalse(l3xxxa.scholarship(l3xxxa, 2));
    }

    @Test
    public void averageScore_redDiploma_scholarship_goodStudent() {
        CreditBook goodStudent = new CreditBook(20214, "Zhmishenko V.A.", "FIT", "CS and SD", 0);
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
        double i = CreditBook.averageScore(grades);
        String result;
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        result = decimalFormat.format(i);
        Assertions.assertEquals("4.8", result);
        Assertions.assertTrue(goodStudent.scholarship(goodStudent, 2));
        Assertions.assertTrue(CreditBook.redDiploma(goodStudent));
        goodStudent.setGradeOfWork(4);
        Assertions.assertFalse(CreditBook.redDiploma(goodStudent));
    }

}