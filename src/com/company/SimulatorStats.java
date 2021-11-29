package com.company;

import java.util.LinkedList;
import java.util.List;

public class SimulatorStats {
    private Player winner;
    private List<Integer> maximumRollsInOneTurn = new LinkedList<>();
    private int maxRollCount = 0;
    private int maxClimb = 0;
    private int maxSlide = 0;

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<Integer> getMaximumRollsInOneTurn() {
        return maximumRollsInOneTurn;
    }

    public int getMaxRollCount() {
        return maxRollCount;
    }

    public int getMaxClimb() {
        return maxClimb;
    }

    public int getMaxSlide() {
        return maxSlide;
    }

    public void replaceRollInCaseBigger(List<Integer> rolls) {
        int sum = 0;
        for (Integer roll: rolls) {
            sum = sum + roll;
        }
        if (maximumRollsInOneTurn.isEmpty() || maxRollCount < sum){
            maximumRollsInOneTurn = rolls;
            maxRollCount = sum;
        }
    }

    public void setMaxClimbAndSlides(int afterPosition, int beforePosition) {
        if (beforePosition > afterPosition && ((beforePosition - afterPosition) > maxSlide)){
            maxSlide = beforePosition - afterPosition;
        }
        if (beforePosition < afterPosition && ((afterPosition - beforePosition) > maxClimb)){
            maxClimb = afterPosition - beforePosition;
        }
    }
}
