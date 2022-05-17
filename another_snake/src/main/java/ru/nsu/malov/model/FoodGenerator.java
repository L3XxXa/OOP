package ru.nsu.malov.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FoodGenerator {
    private final int MAX_FOOD;
    private final int HEIGHT;
    private final int WIDTH;

    private List<Point> food;

    public FoodGenerator(GameField gameField, int MAX_FOOD){
        this.MAX_FOOD = MAX_FOOD;
        HEIGHT = gameField.getROWS();
        WIDTH = gameField.getCOLUMNS();
        food = new ArrayList<>();

    }

    public void generateFood(WallsGenerator wallsGenerator, Snake snake) {
        while (food.size() < MAX_FOOD) {
            Point newFoodItem;
            do {
                newFoodItem = new Point((int) (Math.random() * HEIGHT), ((int) (Math.random() * WIDTH)));
            } while (wallsGenerator.getWalls().contains(newFoodItem) || snake.getPython().contains(newFoodItem) || food.contains(newFoodItem));
            food.add(newFoodItem);
        }
    }

    public List<Point> getFood() {
        return food;
    }

    public void remove(){
        food.removeAll(food);
    }
}
