package ru.nsu.malov;

import java.io.*;

public class CalculateString {
    private final String input = "input.txt";
    private final String output = "output.txt";
    /**
     * Method to open file, calculate string and execute method to write the answer to the file
     */

    Calculator calculator = new Calculator();
    public void calculateString() throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(input));
            String line = bufferedReader.readLine();
            double res = calculator.calculate(line);
            String resStr = Double.toString(res);
            writeAnswerToFile(resStr);
            bufferedReader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    private void writeAnswerToFile(String res) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(output);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(res);
            printWriter.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}
