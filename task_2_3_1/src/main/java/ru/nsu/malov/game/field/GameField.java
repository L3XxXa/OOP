package ru.nsu.malov.game.field;

import ru.nsu.malov.game.field.generators.FoodPositionGenerator;

public class GameField {
    private final int SIZE = 10;
    private final int COLUMNS;
    private final int ROWS;
    private Point food;
    private FoodPositionGenerator foodPosition;

    public GameField(int width, int height) {
        COLUMNS = (int) width / SIZE;
        ROWS = (int) height / SIZE;
        foodPosition = new FoodPositionGenerator(height, width);
        food = foodPosition.setFood();
    }


    public int getCOLUMNS() {
        return COLUMNS;
    }

    public int getROWS() {
        return ROWS;
    }

    public void setFood(Point food) {
        this.food = food;
    }

    public Point getFood() {
        return food;
    }
}
