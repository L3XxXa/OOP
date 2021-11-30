package ru.nsu.malov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.malov.json.WorkWithJson;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotebookTest {
    Notebook notebook = new Notebook();

    @Test
    public void addNoteShowAllNotes(){
        notebook.addNote("abobus", "magic abobus in the sky");
        Note note = new Note("abobus", "magic abobus in the sky");
        Assertions.assertEquals(note.getName(), notebook.showAllNotes().get(0).getName());
        Assertions.assertEquals(note.getText(), notebook.showAllNotes().get(0).getText());
        Assertions.assertEquals(note.getLocalDate().withNano(0).withSecond(0), notebook.showAllNotes().get(0).getLocalDate());
        notebook.removeNote("abobus");
    }

    @Test
    public void removeNoteShowAllNotes(){
        notebook.addNote("hello", "it's too much man");
        notebook.removeNote("hello");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> notebook.showAllNotes().get(0));
    }

    @Test
    public void showNotesByDateTime(){
        notebook.addNote("hello", "BoJack horseman is the best cartoon");
        notebook.addNote("hello", "Press F for Sara Lynn");
        LocalDateTime localDateTime = LocalDateTime.now();
        Note note = new Note("hello", "Press F for Sara Lynn");
        String[] keys = {"Sara"};
        Note res = notebook.showNotesByDateKeys(LocalDateTime.parse("2020-11-30T23:35"), LocalDateTime.parse("2023-11-30T23:35"), keys).get(0);
        Assertions.assertEquals(res.getName(), note.getName());
        notebook.removeNote("hello");

    }

    @Test
    public void newFileName(){
        notebook.newFilename("Abobus");
        WorkWithJson workWithJson = new WorkWithJson("Abobus");
        Assertions.assertEquals(workWithJson.getFileName(), notebook.getFilename()+".json");

    }



}