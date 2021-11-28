package com.company;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Board board = BoardBuilder.buildBoard();
        boolean simulation = true;
        while (simulation) {
            runGameSimulation(board);
            System.out.println("Run simulation Again? : true/false");
            simulation = scanner.nextBoolean();
        }
    }

    private static void runGameSimulation(Board board) {
        SimulatorStats simulatorStats = new SimulatorStats();
        board.setFinished(false);
        Scanner simulationScanner = new Scanner(System.in);
        System.out.println("How many Players?");
        int numberOfPlayers = simulationScanner.nextInt();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player();
            System.out.println(String.format("Please enter the name of Player %s : ", i+1));
            player.setName(simulationScanner.next());
            player.setCurrentPosition(1);
            players.add(i, player);
        }
        int i = 0;
        while (!board.isFinished()) {
            Player currentPlayer = players.get(i);
            int roll;
            List<Integer> slides = new LinkedList<>();
            int beforePosition = currentPlayer.getCurrentPosition();
            do {
                roll = ThreadLocalRandom.current().nextInt(1,7);
                currentPlayer.setCurrentPositionBasedOnRoll(roll, board,simulatorStats);
                slides.add(roll);
            }while (roll == 6 && !board.isFinished());

            simulatorStats.setMaxClimbAndSlides(currentPlayer.getCurrentPosition(), beforePosition);
            simulatorStats.replaceRollInCaseBigger(slides);
            i = (i + 1) % numberOfPlayers;
        }

        System.out.println("Winner : " + simulatorStats.getWinner().getName());
        StatsUtil.displaySingleTurnExclusiveStats(simulatorStats);
        StatsUtil.displayMaxMinAvgSlideAndClimb(players);
    }

}
