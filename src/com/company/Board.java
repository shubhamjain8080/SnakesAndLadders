package com.company;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private Map<Integer, Integer> snakes = new HashMap<>();
    private Map<Integer, Integer> ladders = new HashMap<>();
    private boolean finished = false;

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Map<Integer, Integer> getSnakes() {
        return snakes;
    }

    public Map<Integer, Integer> getLadders() {
        return ladders;
    }

    public boolean doesSnakeOrLadderExistAtPosition(int key, int value) {
        return snakes.containsKey(key) || ladders.containsValue(key) ||
                snakes.containsKey(value) || ladders.containsValue(value);
    }

    public void createSnakeOnBoard(int headOfSnake, int tailOfSnake) {
        if (doesSnakeOrLadderExistAtPosition(headOfSnake, tailOfSnake)) {
            System.out.println(Constants.SNAKE_LADDER_ALREADY_EXIST);
        } else {
            snakes.put(headOfSnake, tailOfSnake);
        }
    }

    public void createLadderOnBoard(int bottom, int top) {
        if (doesSnakeOrLadderExistAtPosition(bottom, top)) {
            System.out.println(Constants.SNAKE_LADDER_ALREADY_EXIST);
        } else {
            ladders.put(bottom, top);
        }
    }
}
