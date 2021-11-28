package com.company;

import com.sun.java.swing.action.AboutAction;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {

    private Map<Integer, Integer> snakesAndLadders = new HashMap<>();
    private boolean finished = false;

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Map<Integer, Integer> getSnakesAndLadders() {
        return snakesAndLadders;
    }

    public void setSnakesAndLadders(Map<Integer, Integer> snakesAndLadders) {
        this.snakesAndLadders = snakesAndLadders;
    }

    public void addSnakesOrLadderPosition(int key, int value) {
        snakesAndLadders.put(key, value);
    }

    public boolean doesSnakeOrLadderExistAtPosition(int key, int value) {
        return snakesAndLadders.containsKey(key) || snakesAndLadders.containsValue(key) ||
                snakesAndLadders.containsKey(value) || snakesAndLadders.containsValue(value);
    }
}
