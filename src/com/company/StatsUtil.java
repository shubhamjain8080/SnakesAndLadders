package com.company;

import java.util.List;

public class StatsUtil {

    public static void displayMaxMinAvgSlideAndClimb(List<Player> players) {
        int maxSlides = 0;
        int minSlides = 100;
        int maxClimbs = 0;
        int minClimb = 100;
        int sumOfClimbs = 0;
        int sumOfSlides = 0;
        for (Player player: players) {
            if (player.getTotalSlide() > maxSlides){
                maxSlides = player.getTotalSlide();
            }
            if (player.getTotalClimb() > maxClimbs){
                maxClimbs = player.getTotalClimb();
            }
            if (player.getTotalClimb() < minClimb){
                minClimb = player.getTotalClimb();
            }
            if (player.getTotalSlide() < minSlides){
                minSlides = player.getTotalSlide();
            }
            sumOfClimbs = sumOfClimbs + player.getTotalClimb();
            sumOfSlides = sumOfSlides + player.getTotalSlide();
        }

        System.out.println("Maximum Slides in the game for a player : " + maxSlides);
        System.out.println("Maximum Climbs in the game for a player : " + maxClimbs);
        System.out.println("Minimum Slides in the game for a player : " + minSlides);
        System.out.println("Minimum Climbs in the game for a player : " + minClimb);
        System.out.println("Average Slides in the game for a player : " + (sumOfSlides/ players.size()));
        System.out.println("Average Climbs in the game for a player : " + (sumOfClimbs/ players.size()));

    }


    public static void displaySingleTurnExclusiveStats(SimulatorStats simulatorStats) {
        System.out.println("Max Slide in one turn : " + simulatorStats.getMaxSlide());
        System.out.println("Max Climb in one turn : " + simulatorStats.getMaxClimb());
        System.out.println("Max roll in one turn: " + simulatorStats.getMaximumRolls());
        System.out.println("Max roll count in one turn: " + simulatorStats.getMaxRollCount());
    }
}
