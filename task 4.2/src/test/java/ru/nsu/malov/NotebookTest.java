package ru.nsu.malov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class NotebookTest {
    Notebook notebook;
    @BeforeEach
    private void init(){
        notebook = new Notebook();
    }

    @Test
    public void Eva01() throws IOException {
        notebook.addNote("IKARI");
        System.out.println(notebook.showAll());
    }
}