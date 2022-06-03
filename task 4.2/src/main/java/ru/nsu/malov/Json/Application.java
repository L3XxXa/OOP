package ru.nsu.malov.Json;

import org.apache.commons.cli.*;
import ru.nsu.malov.Note;
import ru.nsu.malov.Notebook;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Application {
    private Options options;
    private Notebook notebook;
    private JsonMaker jsonMaker = new JsonMaker();

    public Application(){
       options = new Options();
       Option addNote = Option.builder("add").numberOfArgs(2).build();
       options.addOption(addNote);
       Option removeNote = Option.builder("rm").numberOfArgs(1).build();
       options.addOption(removeNote);
       Option showNote = Option.builder("show").hasArgs().optionalArg(true).build();
       options.addOption(showNote);
       Option telegram = Option.builder("tg").numberOfArgs(0).build();
       options.addOption(telegram);
       Option console = Option.builder("cl").numberOfArgs(0).build();
       options.addOption(console);
    }


    public void init(String[] args){
        CommandLineParser clParser = new DefaultParser();
        CommandLine cl = null;
        try {
            cl = clParser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        parseCLI(cl);
    }
    public void addNote(Note note)throws IOException {
        notebook = new Notebook();
        notebook.addNotes(jsonMaker.readJson());
        notebook.addNote(note);
        jsonMaker.writeToFile(notebook.showAll());
    }

    public void removeNote(String text) throws IOException {
        notebook = new Notebook();
        notebook.addNotes(jsonMaker.readJson());
        notebook.removeNote(text);
        jsonMaker.writeToFile(notebook.showAll());
    }

    public void showNote() throws IOException {
        notebook = new Notebook();
        ArrayList<Note> notes = jsonMaker.readJson();
        if (notes.size() == 0){
            System.out.println("I can't find notes. File is empty now");
        }
        else {
           notebook.addNotes(notes);
           System.out.println("========ALL NOTES=======");
           System.out.println(notebook.toString());
           System.out.println("========================");
        }
    }

    public void showNote(String before, String after, String key) throws IOException {
        notebook = new Notebook();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yy");
        LocalDateTime beforeDate = LocalDateTime.parse(before, dateTimeFormatter);
        LocalDateTime afterDate = LocalDateTime.parse(after, dateTimeFormatter);
        notebook.addNotes(jsonMaker.readJson());
        ArrayList<Note> res = notebook.showNotesByDate(beforeDate, afterDate, key);
        if (res.size() == 0){
            System.out.println("I can't find any note for your search query");
        }
        else{
            System.out.println("==========NOTES=========");
            System.out.println(notebook.toString());
            System.out.println("========================");
        }
    }

    private void parseCLI(CommandLine cl)  {
        if (cl.hasOption("add")){
            String[] values = cl.getOptionValues("add");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yy");
            String time = dateTimeFormatter.format(LocalDateTime.now());
            Note note = new Note(values[0], values[1], time);
            try {
                addNote(note);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (cl.hasOption("rm")){
            String s = cl.getOptionValue("rm");
            try {
                removeNote(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (cl.hasOption("show")){
            String[] values = cl.getOptionValues("show");
            if(values != null){
                try {
                    showNote(values[0], values[1], values[2]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    showNote();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
