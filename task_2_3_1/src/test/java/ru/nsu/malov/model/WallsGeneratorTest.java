package ru.nsu.malov.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WallsGeneratorTest {
    private GameField gameField;
    private WallsGenerator wallsGenerator;
    private List<Point> walls;
    private Snake snake;
    private FoodGenerator foodGenerator;
    @BeforeEach
    private void init(){
        gameField = new GameField(900, 900, 20, 20, 900/20);
        wallsGenerator = new WallsGenerator(gameField, 10);
        foodGenerator = new FoodGenerator(gameField, 10);
        walls = new ArrayList<>();
        snake = new Snake(gameField, foodGenerator, wallsGenerator);
    }

    @Test
    public void generateWalls(){
        wallsGenerator.generateWalls(snake);
        Assertions.assertEquals(10, wallsGenerator.getWalls().size());
    }

    @Test
    public void removeWalls(){
        wallsGenerator.generateWalls(snake);
        wallsGenerator.remove();
        Assertions.assertEquals(0, wallsGenerator.getWalls().size());
    }
}