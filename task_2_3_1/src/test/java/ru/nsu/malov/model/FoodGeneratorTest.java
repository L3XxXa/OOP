package ru.nsu.malov.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodGeneratorTest {
    private FoodGenerator foodGenerator;
    private Snake snake;
    private  GameField gameField;
    private WallsGenerator wallsGenerator;

    @BeforeEach
    private void init(){
        gameField = new GameField(900, 900, 5, 5, 900/20);
        foodGenerator = new FoodGenerator(gameField, 24);
        wallsGenerator = new WallsGenerator(gameField, 1);
        snake = new Snake(gameField, foodGenerator, wallsGenerator);
    }

    @Test
    public void generateFood(){
        Assertions.assertEquals(0, foodGenerator.getFood().size());
        foodGenerator.generateFood(wallsGenerator, snake);
        Assertions.assertEquals(24, foodGenerator.getFood().size());
    }

    @Test
    public void remove(){
        foodGenerator.generateFood(wallsGenerator, snake);
        Assertions.assertEquals(24, foodGenerator.getFood().size());
        foodGenerator.remove();
        Assertions.assertEquals(0, foodGenerator.getFood().size());
    }

}