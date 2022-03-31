package ru.nsu.malov.json;

import com.google.gson.Gson;
import ru.nsu.malov.json.setup.JsonSetupPizzeria;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonReader {
    private String fileName;
    private File file;
    private Reader bufferedJsonReader;
    private Gson gson;

    /**
     * JsonReader class constructor with special fileName
     * @param fileName - file name
     * */
    public JsonReader(String fileName){
        this.fileName = fileName;
        file = new File(fileName);
    }

    /**
     * JsonReader class constructor with default filename
     * */
    public JsonReader(){
        fileName = "pizzeria.json";
        file = new File(fileName);
    }

    /**
     * Read json file and set up Pizzeria
     * @return Pizzeria set up
     * */

    public JsonSetupPizzeria readFile(){
        JsonSetupPizzeria jsonSetupPizzeria;
        if (!file.exists()){
            try {
                file.createNewFile();
                System.err.println("Your JSON file was made, but it's still empty. Set up it please.");
                return null;
            } catch (IOException e) {
                System.err.println("Error while making file");
            }
        }
        gson = new Gson();
        try {
            bufferedJsonReader = Files.newBufferedReader(Path.of(fileName));
        } catch (IOException e) {
            System.err.println("Error while setting up reader");
        }
        jsonSetupPizzeria = gson.fromJson(bufferedJsonReader, JsonSetupPizzeria.class);
        try {
            bufferedJsonReader.close();
        } catch (IOException e) {
            System.err.println("Error while closing file");
        }
        return jsonSetupPizzeria;
    }

}
