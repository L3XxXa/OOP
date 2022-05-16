package ru.nsu.malov.model;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class FoodGenerator {
    private final int HEIGHT;
    private final int WIDTH;
    private Random random;
    private Point food;

    public FoodGenerator(GameField gameField){
        HEIGHT = gameField.getROWS();
        WIDTH = gameField.getCOLUMNS();
        random = new Random();
    }

    public void generateFood(List<Point> snake){
        int positionX = random.nextInt(0, WIDTH);
        int positionY = random.nextInt(0, HEIGHT);
        for (int i = 0; i < snake.size(); i++) {
            if (snake.get(i).getX() == positionX && snake.get(i).getY() == positionY) {
                positionX = random.nextInt(0, WIDTH);
                positionY = random.nextInt(0, HEIGHT);
            }
        }
        food = (new Point(random.nextInt(positionX), random.nextInt(positionY)));
    }

    public Point getFood() {
        return food;
    }
}
