package ru.nsu.malov.another_snake;

public class Parameters {
    private final int MAX_FOOD;
    private final int MAX_WALLS;
    private final int MAX_ROWS;
    private final int MAX_COLUMNS;
    private final int POINT_SIZE;
    private final int SCORE_FOR_WIN;
    private final int LEVEL;
    private final int HORIZONTAL_SIZE = 900;
    private final int VERTICAL_SIZE = 900;

    public Parameters(int max_food, int max_walls, int max_rows, int max_columns, int score_for_win, int level) {
        MAX_FOOD = max_food;
        MAX_WALLS = max_walls;
        MAX_ROWS = max_rows;
        MAX_COLUMNS = max_columns;
        LEVEL = level;
        POINT_SIZE = HORIZONTAL_SIZE / MAX_COLUMNS;
        SCORE_FOR_WIN = score_for_win;
    }

    public int getMAX_FOOD() {
        return MAX_FOOD;
    }

    public int getMAX_WALLS() {
        return MAX_WALLS;
    }

    public int getMAX_ROWS() {
        return MAX_ROWS;
    }

    public int getSCORE_FOR_WIN() {
        return SCORE_FOR_WIN;
    }

    public int getMAX_COLUMNS(){
        return MAX_COLUMNS;
    }

    public int getPOINT_SIZE() {
        return POINT_SIZE;
    }

    public int getHORIZONTAL_SIZE(){
        return HORIZONTAL_SIZE;
    }

    public int getVERTICAL_SIZE() {
        return VERTICAL_SIZE;
    }

    public int getLEVEL() {
        return LEVEL;
    }
}
