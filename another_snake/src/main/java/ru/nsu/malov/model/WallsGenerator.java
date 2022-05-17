package ru.nsu.malov.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class WallsGenerator {
    private final int MAX_CNT;

    private GameField gameField;
    private List<Point> walls;

    public WallsGenerator(GameField gameField, int MAX_CNT) {
        this.gameField = gameField;
        this.MAX_CNT = MAX_CNT;
        walls = new ArrayList<>();
    }

    public void generateWalls(Snake snake){
        while(walls.size() < MAX_CNT){
            Point wall;
            do {
                wall = new Point((int)(Math.random() * gameField.getROWS()), (int)(Math.random() * gameField.getCOLUMNS()));
            } while (walls.contains(wall) ||
                    ((wall.getX() == snake.getSTARTING_X()) || wall.getX() == snake.getSTARTING_X() + 1 || wall.getX() == snake.getSTARTING_X() + 2) &&
                    wall.getY() == snake.getSTARTING_Y());
            walls.add(wall);
        }
    }

    public void remove(){
        walls.removeAll(walls);
    }

    public List<Point> getWalls() {
        return walls;
    }
}
