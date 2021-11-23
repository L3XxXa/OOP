package ru.nsu.malov;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.malov.Json.Application;
import ru.nsu.malov.Json.JsonMaker;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class NotebookTest {
    Notebook notebook;
    JsonMaker jsonMaker;
    Application application;

    @BeforeEach
    private void init(){
        notebook = new Notebook();
        jsonMaker = new JsonMaker();
        application = new Application();
    }

    @Test
    public void clTest1() throws ParseException, IOException {
        System.out.println("TESTING");
        String[] args1 = {"-add", "Ikari Shinji", "Ikari Shinji lox"};
        String[] args2 = {"-show"};

        application.init(args1);
        application.init(args1);
        String[] args3 = {"-rm", "Ikari Shinji lox"};
        application.init(args3);
        application.init(args1);
        application.init(args2);
    }

    @Test
    public void clTest2() throws ParseException, IOException{
        String[] args1 = {"-add", "Van", "Boy next door"};
        application.init(args1);
        String[] args2 = {"-show", "03:12:23 23/10/21", "03:12:23 23/12/21", "next"};
        application.removeNote("next");
        application.init(args2);
    }
}