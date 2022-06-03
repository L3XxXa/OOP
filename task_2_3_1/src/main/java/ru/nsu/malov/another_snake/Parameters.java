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

    /**
     * Constructor for parameters class
     *
     * @param max_food      - max food on the field at same time
     * @param max_walls     - amount of max walls on the field at same time
     * @param max_rows      - amount of rows of the field
     * @param max_columns   - amount of columns of the field
     * @param score_for_win - amount of scores is needed to win
     * @param level         - difficulty level
     */
    public Parameters(int max_food, int max_walls, int max_rows, int max_columns, int score_for_win, int level) {
        MAX_FOOD = max_food;
        MAX_WALLS = max_walls;
        MAX_ROWS = max_rows;
        MAX_COLUMNS = max_columns;
        LEVEL = level;
        POINT_SIZE = HORIZONTAL_SIZE / MAX_COLUMNS;
        SCORE_FOR_WIN = score_for_win;
    }


    /**
     * Getter for MAX_FOOD
     *
     * @return amount of max food on the field at same time
     */
    public int getMAX_FOOD() {
        return MAX_FOOD;
    }

    /**
     * Getter for MAX_WALLS
     *
     * @return amount of max walls on the field at same time
     */
    public int getMAX_WALLS() {
        return MAX_WALLS;
    }

    /**
     * Getter for MAX_ROWS
     *
     * @return amount of rows of the field
     */
    public int getMAX_ROWS() {
        return MAX_ROWS;
    }

    /**
     * Getter for SCORE_FOR_WIN
     *
     * @return amount of scores is needed to win
     */
    public int getSCORE_FOR_WIN() {
        return SCORE_FOR_WIN;
    }

    /**
     * Getter for MAX_COLUMNS
     *
     * @return amount of columns of the field
     */
    public int getMAX_COLUMNS() {
        return MAX_COLUMNS;
    }

    /**
     * Getter for POINT_SIZE
     *
     * @return size of the point of the field
     */
    public int getPOINT_SIZE() {
        return POINT_SIZE;
    }

    /**
     * Getter for HORIZONTAL_SIZE
     *
     * @return size of the field on X axis
     */
    public int getHORIZONTAL_SIZE() {
        return HORIZONTAL_SIZE;
    }

    /**
     * Getter for VERTICAL_SIZE
     *
     * @return size of the field on Y axis
     */
    public int getVERTICAL_SIZE() {
        return VERTICAL_SIZE;
    }

    /**
     * Getter for LEVEL
     *
     * @return difficulty level
     */
    public int getLEVEL() {
        return LEVEL;
    }
}
