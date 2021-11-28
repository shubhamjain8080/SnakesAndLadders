package com.company;

import java.util.Map;

public class Player {
    private String name;
    private int currentPosition;
    private String positionString;
    private String rollString;
    private int unluckyRolls = 0;
    private int luckyRolls = 0;

    public String getRollString() {
        return rollString;
    }

    public void setRollString(String rollString) {
        this.rollString = rollString;
    }

    public String getPositionString() {
        return positionString;
    }

    public void setPositionString(String positionString) {
        this.positionString = positionString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getUnluckyRolls() {
        return unluckyRolls;
    }

    public void setUnluckyRolls(int unluckyRolls) {
        this.unluckyRolls = unluckyRolls;
    }

    public void increaseCountOfUnluckyRoll() {
        this.unluckyRolls = this.unluckyRolls + 1;
    }

    public void increaseLuckyRollCount() {
        this.luckyRolls = this.luckyRolls + 1;
    }

    public void setCurrentPositionBasedOnRoll(int roll, Board board, SimulatorStats simulatorStats){
        final Map<Integer, Integer> snakesAndLadders = board.getSnakesAndLadders();
        if (currentPosition + roll <= 100) {
            currentPosition = currentPosition + roll;
        }
        if (snakesAndLadders.containsKey(currentPosition)) {
            Integer nextPosition = snakesAndLadders.get(currentPosition);
            if (currentPosition > nextPosition) {
                this.unluckyRolls = this.unluckyRolls + 1;
                simulatorStats.getSlides().add(currentPosition-nextPosition);
            } else {
                this.luckyRolls = this.luckyRolls + 1;
                simulatorStats.getClimbs().add(nextPosition-currentPosition);
            }
            currentPosition = nextPosition;
        }
        if (currentPosition == 100) {
            simulatorStats.setWinner(this);
            board.setFinished(true);
        }
    }
}
