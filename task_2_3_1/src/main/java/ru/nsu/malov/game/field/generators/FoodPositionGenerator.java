package ru.nsu.malov.game.field.generators;

import ru.nsu.malov.game.field.Point;

import java.util.Random;

public class FoodPositionGenerator {
    private final int HEIGHT;
    private final int WIDTH;
    private Random random;
    private Point foodPosition;

    public FoodPositionGenerator(int height, int width) {
        this.HEIGHT = height;
        this.WIDTH = width;
        random = new Random();
    }

    public Point setFood(){
        foodPosition = new Point(random.nextInt(WIDTH), random.nextInt(HEIGHT));
        return foodPosition;
    }
}
