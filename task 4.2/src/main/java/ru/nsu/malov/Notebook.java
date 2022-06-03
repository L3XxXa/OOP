package ru.nsu.malov;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.*;

public class Notebook{
    private ArrayList<Note> notes;
    private DateTimeFormatter dateTimeFormatter;

    public Notebook() {
        notes = new ArrayList<>();
        dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yy");
    }
    public void addNote(Note note){
        notes.add(note);
    }

    public void addNote(String name, String text) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Note note = new Note(name, text, dateTimeFormatter.format(localDateTime));
        notes.add(note);
    }
    
    public void addNotes(ArrayList<Note> noteInput){
        notes.addAll(noteInput);
    }

    public void removeNote(String text) {
        notes.removeIf(note -> text.equals(note.getText()));
    }

    public ArrayList<Note> showAll(){
        return notes;
    }

    public ArrayList<Note> showNotesByDate(LocalDateTime before, LocalDateTime after, String key){
        ArrayList<Note> res = new ArrayList<>();
        LocalDateTime beforeLocal = LocalDateTime.parse(before.toString());
        LocalDateTime afterLocal = LocalDateTime.parse(after.toString());
        LocalDateTime localDateTime;
        for (Note note:notes) {
            localDateTime = LocalDateTime.parse(note.getTime(), dateTimeFormatter);
            if(beforeLocal.isBefore(localDateTime) && afterLocal.isAfter(localDateTime) && note.getText().contains(key)){
                res.add(note);
            }
        }
        return res;
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        int c = 0;
        for (Note note:notes) {
            c++;
            stringBuilder.append("#"+c)
                    .append("\n")
                    .append("Name: ")
                    .append(note.getName())
                    .append("\n")
                    .append("Text:")
                    .append(note.getText())
                    .append("\n")
                    .append("Time:")
                    .append(note.getTime())
                    .append("\n");

        }
        return stringBuilder.toString();
    }
}



