package com.company;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        System.out.println("Please add snakes' and ladders' positions : ");
        System.out.println("How many snakes?");
        Scanner in = new Scanner(System.in);
        int numberOfSnakes = in.nextInt();
        fillSnakesOnBoard(board, numberOfSnakes);

        System.out.println("How many Ladders?");
        int numberOfLadders = in.nextInt();
        fillLaddersOnBoard(board, numberOfLadders);

        boolean simulation = true;
        while (simulation) {
            runGameSimulation(board);
            System.out.println("Run simulation Again? : true/false");
            simulation = in.nextBoolean();
        }
    }

    private static void runGameSimulation(Board board) {
        Scanner simulationScanner = new Scanner(System.in);
        System.out.println("How many Players?");
        int numberOfPlayers = simulationScanner.nextInt();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player();
            System.out.println(String.format("Please enter the name of Player %s : ", i+1));
            player.setName(simulationScanner.next());
            player.setCurrentPosition(1);
            player.setPositionString("1 ");
            player.setRollString("");
            players.add(i, player);
        }
        boolean finished = false;
        int i = 0;
        while (!finished) {
            Player currentPlayer = players.get(i);
            int currentPosition = currentPlayer.getCurrentPosition();
            int roll;
            do {
                roll = ThreadLocalRandom.current().nextInt(1,7);
                if (currentPosition + roll <= 100) {
                    currentPosition = currentPosition + roll;
                }
                if (board.getSnakes().containsKey(currentPosition)) {
                    currentPosition = board.getSnakes().get(currentPosition);
                    currentPlayer.setUnluckyRolls(currentPlayer.getUnluckyRolls() + 1);
                }
                if (board.getLadders().containsKey(currentPosition)) {
                    currentPosition = board.getLadders().get(currentPosition);
                    currentPlayer.setLuckyRolls(currentPlayer.getLuckyRolls() + 1);
                }
                if (currentPosition == 100) {
                    finished = true;
                    System.out.println(currentPlayer.getName() + " has won");
                }
                currentPlayer.setCurrentPosition(currentPosition);
                String po = currentPlayer.getPositionString() + currentPosition + " ";
                String ro = currentPlayer.getRollString() + roll + " ";
                currentPlayer.setRollString(ro);
                currentPlayer.setPositionString(po);
            }while (roll == 6 && !finished);
            i = (i + 1) % numberOfPlayers;
        }
        for (Player player:players) {
            System.out.println("Position String for : " + player.getName() + ": " + player.getPositionString());
            System.out.println("Roll String for : " + player.getName() + ": " + player.getRollString());
        }
    }

    private static void fillLaddersOnBoard(Board board,int numberOfLadders) {
        Scanner ladderInputScanner = new Scanner(System.in);
        Map<Integer, Integer> ladders = board.getLadders();
        for (int i = 0; i < numberOfLadders; i++) {
            System.out.println(String.format("Enter the bottom of %s :  ", i+1));
            int bottomOfLadder = ladderInputScanner.nextInt();
            System.out.println(String.format("Enter the top of %s :  ", i+1));
            int topOfLadder = ladderInputScanner.nextInt();
            if (bottomOfLadder > 1 || topOfLadder < 100) {
                if ((bottomOfLadder - 1) / 10 < (topOfLadder - 1) / 10) {
                    if (board.getSnakes().containsKey(bottomOfLadder) || board.getSnakes().containsValue(bottomOfLadder)) {
                        System.out.println("There is already snake at this position. Please Enter the values again.");
                        i = i - 1;
                    } else {
                        if (ladders.containsValue(bottomOfLadder) || ladders.containsKey(topOfLadder)) {
                            System.out.println("This positioning of ladder is going to make someone climb by ladders " +
                                    "twice in one round. Please Enter the value again.");
                            i = i - 1;
                        }
                        else {
                            ladders.put(bottomOfLadder, topOfLadder);
                        }
                    }
                } else {
                    System.out.println("Ladder's bottom can't be on less or equal level to top. Please enter value again.");
                    i = i - 1;
                }
            }
            else {
                System.out.println("Any value has to be between 2-99 for ladders");
            }
        }
    }

    private static void fillSnakesOnBoard(Board board, int numberOfSnakes) {
        Scanner snakeDetailsScanner = new Scanner(System.in);
        Map<Integer, Integer> snakes = board.getSnakes();
        for (int i = 0; i < numberOfSnakes; i++) {
            System.out.println(String.format("Enter the head of %s :  ", i+1));
            int headOfSnake = snakeDetailsScanner.nextInt();
            System.out.println(String.format("Enter the tail of %s :  ", i+1));
            int tailOfSnake = snakeDetailsScanner.nextInt();
            if (tailOfSnake > 1 || headOfSnake < 100) {
                if ((headOfSnake - 1) / 10 > (tailOfSnake - 1) / 10) {
                    if (snakes.containsValue(headOfSnake) || snakes.containsKey(tailOfSnake)) {
                        System.out.println("This positioning of snake is going to make someone bit by snakes " +
                                "twice in one round. Please Enter the value again.");
                        i = i - 1;
                    } else {
                        snakes.put(headOfSnake, tailOfSnake);
                    }
                } else {
                    System.out.println("Snake's head can't be on less or equal level to tail, please enter the values again.");
                    i = i - 1;
                }
            }
            else {
                System.out.println("Any value has to be between 2-99 for snakes");
            }
        }
    }
}
