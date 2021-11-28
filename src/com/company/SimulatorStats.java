package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimulatorStats {
    private Player winner;
    private List<Integer> maximumRolls = new LinkedList<>();
    private int maxRollCount = 0;
    private int maxClimb = 0;
    private int maxSlide = 0;

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<Integer> getMaximumRolls() {
        return maximumRolls;
    }

    public void setMaximumRolls(List<Integer> maximumRolls) {
        this.maximumRolls = maximumRolls;
    }

    public int getMaxRollCount() {
        return maxRollCount;
    }

    public void setMaxRollCount(int maxRollCount) {
        this.maxRollCount = maxRollCount;
    }

    public int getMaxClimb() {
        return maxClimb;
    }

    public void setMaxClimb(int maxClimb) {
        this.maxClimb = maxClimb;
    }

    public int getMaxSlide() {
        return maxSlide;
    }

    public void setMaxSlide(int maxSlide) {
        this.maxSlide = maxSlide;
    }

    public void replaceRollInCaseBigger(List<Integer> rolls) {
        int sum = 0;
        for (Integer roll: rolls) {
            sum = sum + roll;
        }
        if (maximumRolls.isEmpty() || maxRollCount < sum){
            maximumRolls = rolls;
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
