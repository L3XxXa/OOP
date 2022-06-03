package ru.nsu.malov.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FoodGenerator {
    private final int MAX_FOOD;
    private final int HEIGHT;
    private final int WIDTH;

    private List<Point> food;

    /**
     * Constructor for the food generator class
     *
     * @param gameField - game field
     * @param MAX_FOOD  - maximal amount of the food on the field
     */
    public FoodGenerator(GameField gameField, int MAX_FOOD) {
        this.MAX_FOOD = MAX_FOOD;
        HEIGHT = gameField.getROWS();
        WIDTH = gameField.getCOLUMNS();
        food = new ArrayList<>();

    }


    /**
     * Generates food and checks that it is not in the snake or wall
     *
     * @param wallsGenerator - walls generator
     * @param snake          - snake
     */
    public void generateFood(WallsGenerator wallsGenerator, Snake snake) {
        while (food.size() < MAX_FOOD) {
            Point newFoodItem;
            do {
                newFoodItem = new Point((int) (Math.random() * HEIGHT), ((int) (Math.random() * WIDTH)));
            } while (wallsGenerator.getWalls().contains(newFoodItem) || snake.getPython().contains(newFoodItem) || food.contains(newFoodItem));
            food.add(newFoodItem);
        }
    }

    /**
     * Getter for List food
     *
     * @return list with food
     */
    public List<Point> getFood() {
        return food;
    }

    /**
     * Removes all food from the field
     */
    public void remove() {
        food.removeAll(food);
    }
}
