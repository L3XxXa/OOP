package ru.nsu.malov.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.nsu.malov.Note;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

public class JsonMaker {
    private final String fileName = "Notes.json";
    private FileReader fileReader;
    private FileWriter fileWriter;
    private Gson gson;

    private void openFileToRead(){
        try {
            fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public void writeToFile(ArrayList<Note> notes) throws IOException {
        fileWriter = new FileWriter(fileName);
        gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(notes, ArrayList.class);
        Files.write(Path.of(fileName), Collections.singleton(json));
        System.out.println(json);
        fileWriter.write(json);
    }

    public ArrayList<Note> readJson() throws IOException {
        openFileToRead();
        gson = new GsonBuilder().setPrettyPrinting().create();
        String data = Files.readString(Path.of(fileName));
        Type type = new TypeToken<ArrayList<Note>>(){}.getType();
        return gson.fromJson(data, type);
    }

}
