package ru.nsu.malov;

import ru.nsu.malov.json.WorkWithJson;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Notebook {
    private List<Note> notebook = new ArrayList<>();
    private String filename = "Notebook";
    private WorkWithJson workWithJson = new WorkWithJson(filename);

    /**
     * write to file with name
     * @param name - name of the file to write
     * */
    public void newFilename(String name){
        filename = name;
        workWithJson = new WorkWithJson(name);
    }

    public String getFilename() {
        return filename;
    }

    /**
     * add note
     * @param name - name of the note
     * @param text - text of the note
     * */
    public void addNote(String name, String text){
        Note note = new Note(name, text);
        notebook.addAll(workWithJson.jsonReader());
        notebook.add(note);
        workWithJson.jsonWriter(notebook);
    }

    /**
     * remove note
     * @param name - remove note with this name
     * */
    public void removeNote(String name){
        notebook.removeIf(note -> note.getName().equals(name));
        workWithJson.jsonWriter(notebook);
    }

    /**
     * show notes sorted by date before, date after and keywords
     * @param first - date after
     * @param second - date before
     * @param keys - keywords
     * @return list of the notes for this query
     * */

    public List<Note> showNotesByDateKeys(LocalDateTime first, LocalDateTime second, String[] keys){
        List<Note> notes = workWithJson.jsonReader();
        for (String key:keys) {
           notes = notes.stream().filter(note -> note.getText().contains(key)).toList();
        }
        return notes.stream().filter(note -> note.getLocalDate().isBefore(second)).filter(note -> note.getLocalDate().isAfter(first)).toList();
    }

    /**
     * show all notes
     * @return list of all notes
     * */

    public List<Note> showAllNotes(){
        return workWithJson.jsonReader();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int c = 0;
        for (Note note : notebook) {
            c++;
            stringBuilder.append("#")
                    .append(c)
                    .append("\n")
                    .append("Name: ")
                    .append(note.getName())
                    .append("\n")
                    .append("Text:")
                    .append(note.getText())
                    .append("\n")
                    .append("Time:")
                    .append(note.getLocalDate())
                    .append("\n");

        }
        return stringBuilder.toString();
    }


}
