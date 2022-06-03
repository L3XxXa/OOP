package ru.nsu.malov;

import java.time.LocalDateTime;

public class Note {
    private String name;
    private String text;
    private LocalDateTime localDate;

    /**
     * make note with name and text
     * @param name - note name
     * @param text - note text
     * */
    public Note(String name, String text){
        this.name = name;
        this.text = text;
        localDate = LocalDateTime.now();
    }

    /**
     * get note name
     * @return note name
     * */
    public String getName() {
        return name;
    }

    /**
     * get note text
     * @return note text
     * */
    public String getText() {
        return text;
    }

    /**
     * get note date
     * @return note date
     * */
    public LocalDateTime getLocalDate() {
        return localDate;
    }

    @Override
    public String toString() {
        return "Name='" + name + "\n" +
                "text='" + text + "\n" +
                ", localDate=" + localDate +
                '}';
    }
}

