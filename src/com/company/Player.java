package com.company;

import java.util.Map;

public class Player {
    private String name;
    private int numberOfRolls = 0;
    private int totalClimb = 0;
    private int totalSlide = 0;
    private int currentPosition;
    private int unluckyRolls = 0;
    private int luckyRolls = 0;

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

    public int getNumberOfRolls() {
        return numberOfRolls;
    }

    public int getTotalClimb() {
        return totalClimb;
    }

    public int getTotalSlide() {
        return totalSlide;
    }

    public int getLuckyRolls() {
        return luckyRolls;
    }

    public void setCurrentPositionBasedOnRoll(int roll, Board board, SimulatorStats simulatorStats){
        numberOfRolls++;
        if (currentPosition + roll <= 100) {
            currentPosition = currentPosition + roll;
        }
        final Map<Integer, Integer> snakes = board.getSnakes();
        if (snakes.containsKey(currentPosition)) {
            slidePlayer(simulatorStats, snakes);
        }
        else {
            Map<Integer, Integer> ladders = board.getLadders();
            if (ladders.containsKey(currentPosition)){
                climbPlayer(simulatorStats, ladders);
            }
            else if (didMissSnake(snakes)){
                this.luckyRolls = this.luckyRolls + 1;
            }
        }
        if (currentPosition == 100) {
            simulatorStats.setWinner(this);
            board.setFinished(true);
        }
    }

    private boolean didMissSnake(Map<Integer, Integer> snakes) {
        return snakes.containsKey(currentPosition - 1) || snakes.containsKey(currentPosition - 2);
    }

    private void climbPlayer(SimulatorStats simulatorStats, Map<Integer, Integer> ladders) {
        Integer nextPosition = ladders.get(currentPosition);
        this.luckyRolls = this.luckyRolls + 1;
        totalClimb = totalClimb + nextPosition - currentPosition;
        currentPosition = nextPosition;
    }

    private void slidePlayer(SimulatorStats simulatorStats, Map<Integer, Integer> snakes) {
        Integer nextPosition = snakes.get(currentPosition);
        this.unluckyRolls = this.unluckyRolls + 1;
        totalSlide = totalSlide + currentPosition - nextPosition;
        currentPosition = nextPosition;
    }
}
