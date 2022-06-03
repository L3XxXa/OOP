package ru.nsu.malov.json;

import org.apache.commons.cli.*;
import ru.nsu.malov.Note;
import ru.nsu.malov.Notebook;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class App {
    private Options options;
    private Notebook notebook = new Notebook();
    private CommandLine commandLine;

    /**
     * Make new options for CL
     * */
    public App(){
        options = new Options();
        Option add = Option.builder("add").hasArgs().numberOfArgs(2).build();
        options.addOption(add);
        Option remove = Option.builder("rm").hasArgs().numberOfArgs(1).build();
        options.addOption(remove);
        Option help = Option.builder("help").build();
        options.addOption(help);
        Option show = Option.builder("show").optionalArg(true).build();
        options.addOption(show);
    }

    /**
     * Init command line parser and whole program
     * @param args - command line args
     * */

    public void init(String[] args){
        CommandLineParser commandLineParser = new DefaultParser();
        try {
            commandLine = commandLineParser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Can't parse your parameters.");
        }
        notebook.newFilename(commandLine.getArgList().get(0));
        parseCommandLine(commandLine);
    }

    private void add(String arg1, String arg2){
        notebook.addNote(arg1, arg2);
    }

    private void remove(String arg1){
        notebook.removeNote(arg1);
    }

    private void showAll(){
        List<Note> res = notebook.showAllNotes();
        if (res.size() == 0){
            System.out.println("No notes found.");
        }
        for (Note note:res) {
            System.out.println("============NOTE============");
            System.out.println(note);
            System.out.println("=============================\n");
        }
    }

    private void showNotesByDateKeys(String arg1, String arg2, String[] keys){
        DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm");
        LocalDateTime first = LocalDateTime.parse(arg1, simpleDateFormat);
        LocalDateTime second = LocalDateTime.parse(arg2, simpleDateFormat);
        List<Note> res = notebook.showNotesByDateKeys(first, second, keys);
        if (res.size() == 0){
            System.out.println("No notes found for your query");
        }
        else{
            for (Note note:res) {
                System.out.println("============NOTE============");
                System.out.println(note);
                System.out.println("=============================\n");
            }
        }
    }

    private void showHelpMessage(){
        String helpMsg = """
                Hello!
                This is console notebook made by Malov 'M@61c_L3XxXA' Alexey
                It has some options:
                '-add'(2) - add note. First arg - note name; second arg - text.
                '-rm'(1) - remove note. Arg - name of the note to remove.
                '-show'(0) - show all notes.
                '-show'(3+) - show notes for your searching query. First arg - date before; second arg - date after; others - keyword
                '-help'(0) - show help message
                If you didn't write any args, it will send you a help message""";
        System.out.println(helpMsg);
    }

    private void parseCommandLine(CommandLine commandLine) {
        if (commandLine.hasOption("add")){
            String[] args = commandLine.getOptionValues("add");
            add(args[0], args[1]);
        }
        else if (commandLine.hasOption("rm")){
            String[] args = commandLine.getOptionValues("rm");
            remove(args[0]);
        }
        else if (commandLine.hasOption("show")){
            String[] args = commandLine.getOptionValues("show");
            if (args == null){
                showAll();
            } else {
                String[] keys = Arrays.copyOfRange(args, 2, args.length);
                showNotesByDateKeys(args[0], args[1], keys);
            }
        }
        else {
            showHelpMessage();
        }
    }
}
