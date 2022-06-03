package ru.nsu.malov;

public class Note {
    private String name;
    private String text;
    private String time;

    public Note(String name, String text, String time) {
        this.text = text;
        this.time = time;
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public String getName(){
        return name;
    }

    public String getTime() {
        return time;
    }
}
