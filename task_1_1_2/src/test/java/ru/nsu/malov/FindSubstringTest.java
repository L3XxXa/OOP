package ru.nsu.malov;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.lang.String;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindSubstringTest {
    FindSubstring test = new FindSubstring();
    FileReader reader;

    @Test
    void findSubstring_smalltext() throws IOException {
        String filename = "input1.txt";
        String sample = "pirog";
        char[] samplechar = sample.toCharArray();
        reader = new FileReader(filename);
        ArrayList<Integer> res = test.findsub(reader, samplechar);
        ArrayList<Integer> ans = new ArrayList<Integer>();
        String str = new String(Files.readAllBytes(Path.of(filename)));
        int c = str.indexOf(sample);
        if (c == -1) {
            assertEquals(res, ans);
        } else {
            ans.add(c + 1);
            assertEquals(res, ans);
        }
    }

    @Test
    void findSubstring_bigtext() throws IOException {
        String filename = "input2.txt";
        String sample = "says";
        char[] samplechar = sample.toCharArray();
        reader = new FileReader(filename);
        ArrayList<Integer> res = test.findSubstring(reader, samplechar);
        ArrayList<Integer> ans = new ArrayList<Integer>();
        String str = new String(Files.readAllBytes(Path.of(filename)));
        int c = str.indexOf(sample);
        if (c == -1) {
            assertEquals(res, ans);
        } else {
            ans.add(c + 1);
            assertEquals(res, ans);
        }
    }

    @Test
    void findSubstring_emptytext() throws IOException {
        String filename = "input3.txt";
        String sample = "hello";
        char[] samplechar = sample.toCharArray();
        reader = new FileReader(filename);
        List<Integer> res = test.findSubstring(reader, samplechar);
        List<Integer> ans = new ArrayList<Integer>();
        String str = new String(Files.readAllBytes(Path.of(filename)));
        int c = str.indexOf(sample);
        if (c == -1) {
            assertEquals(res, ans);
        } else {
            ans.add(c + 1);
            assertEquals(res, ans);
        }
    }

    @Test
    void findSubstring_verybigtext() throws IOException {
        String filename = "input4.txt";
        String sample = "BIG BROTHER";
        char[] samplechar = sample.toCharArray();
        reader = new FileReader(filename);
        List<Integer> res = test.findSubstring(reader, samplechar);
        List<Integer> ans = new ArrayList<>();
        String str = new String(Files.readAllBytes(Path.of(filename)));
        int c = str.indexOf(sample);
        if (c == -1) {
            assertEquals(res, ans);
        } else {
            ans.add(c + 1);
            assertEquals(res, ans);
        }
    }
}