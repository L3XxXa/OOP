package ru.nsu.malov;

import com.google.gson.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.*;

import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

public class Notebook {
    Gson gson;
    private final String file = "Notes.json";
    private ArrayList<Note> notes;
    DateTimeFormatter dateTimeFormatter;
    Note note;

    public Notebook() {
        notes = new ArrayList<>();
        dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yy");
        gson = new GsonBuilder().create();
    }

    public void addNote(String text) throws IOException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Note note = new Note(text, dateTimeFormatter.format(localDateTime));
        notes.add(note);
        gson.toJson(notes, new FileWriter(file));


    }

    public void removeNote(String text) {
        if (note.getText().equals(text)) {
            notes.remove(note);
        }
    }

    public String showAll() throws FileNotFoundException {
        JsonReader jsonReader = new JsonReader(new FileReader(file));
        try {
            gson.toJson(notes, new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.toJson(notes);
    }

    public String show(LocalDateTime before, LocalDateTime after, String word) {
        ArrayList<Note> result = new ArrayList<>();
       // JsonReader jsonReader = new JsonReader(new FileReader(file));
        String text;
        for (Note note : notes) {
           LocalDateTime localDateTime = LocalDateTime.parse(note.getTime(), dateTimeFormatter);
            text = note.getText();
            if (localDateTime.isAfter(after) && localDateTime.isBefore(before) && text.contains(word)) {
                result.add(note);
            }
        }
        try {
            gson.toJson(result, new FileWriter(file));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return gson.toJson(result);
    }
}

class Note {
    private String text;
    private String time;

    public Note(String text, String time) {
        this.text = text;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }
}


