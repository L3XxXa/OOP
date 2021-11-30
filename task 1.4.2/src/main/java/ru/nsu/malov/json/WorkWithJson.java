package ru.nsu.malov.json;

import com.google.gson.*;
import ru.nsu.malov.Note;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class WorkWithJson {
    private final String fileExtension = ".json";
    private final String defaultName = "Notebook";
    private final String fileName;
    private FileReader fileReader;
    private FileWriter fileWriter;
    private File file;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm");
    private final Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                        (json, typeOfT, context) ->
            LocalDateTime.parse(json.getAsString(), dateTimeFormatter.withLocale(Locale.ENGLISH)))
            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>)
                        (src, typeOfSrc, context) -> new JsonPrimitive(dateTimeFormatter.format(src)))
            .setPrettyPrinting().create();

    /**
     * initialize filename
     * @param name - filename without file extension
     * */
    public WorkWithJson(String name){
        fileName = name + fileExtension;
    }

    public String getFileName() {
        return fileName;
    }

    /**
     * write to *.json
     * @param notes - list of notes to write
     * */
    public void jsonWriter(List<Note> notes)  {
        String notebook = gson.toJson(notes);
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.write(notebook);
            Files.write(Path.of(fileName), Collections.singleton(notebook));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * read *.json file
     * @return list of notes from *.json file
     * */
    public List<Note> jsonReader(){
        File file = new File(fileName);
        if (file.length() == 0){
            try {
                fileWriter = new FileWriter(fileName);
                fileWriter.write("[]");
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileReader = new FileReader(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Note[] res = gson.fromJson(fileReader, Note[].class);
        return Arrays.stream(res).toList();
    }

}

