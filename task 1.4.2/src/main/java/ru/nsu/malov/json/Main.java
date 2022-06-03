package ru.nsu.malov.json;

import org.apache.commons.cli.CommandLine;

public class Main {
    /**
     * main method, get arguments of command line and start the init
     * */
    public static void main(String[] args) {
        App app = new App();
        app.init(args);

    }
}
