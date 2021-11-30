package ru.nsu.malov.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.malov.Note;
import ru.nsu.malov.Notebook;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    App app = new App();
    Notebook notebook = new Notebook();

    @Test
    public void parse1(){
        String[] cl = {"note", "-add", "Hello man", "Initial commit man"};
        app.init(cl);
        cl = new String[]{"note", "-show"};
        app.init(cl);
        cl = new String[]{"note", "-rm", "Hello man"};
        app.init(cl);
    }
    @Test
    public void mainTest(){
        String[] args = {"note", "-add", "Hello man", "Initial commit man"};
        Main.main(args);
        args = new String[]{"note", "-show"};
        Main.main(args);
        args = new String[]{"note", "-rm", "Hello man"};
        Main.main(args);
    }
}