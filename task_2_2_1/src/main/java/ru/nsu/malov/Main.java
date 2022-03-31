package ru.nsu.malov;

import ru.nsu.malov.json.JsonReader;
import ru.nsu.malov.json.setup.JsonSetupPizzeria;
import ru.nsu.malov.pizzeria.Pizzeria;

public class Main {
    private final static int time = 10000;

    public static void main(String[] args) {
        JsonReader jsonReader = new JsonReader();
        JsonSetupPizzeria jsonSetupPizzeria = jsonReader.readFile();
        Pizzeria pizzeria = new Pizzeria(jsonSetupPizzeria);
        pizzeria.run();
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pizzeria.stop();
    }
}
