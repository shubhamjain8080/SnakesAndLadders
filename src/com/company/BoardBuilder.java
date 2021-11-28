package com.company;

import java.util.Scanner;

public class BoardBuilder {
    private final static Scanner scanner = new Scanner(System.in);
    public static final String VALUE_NOT_IN_RANGE =
            "Values have to be between 2-99. Please Select option again and proceed with correct values.";
    public static final String SNAKE_LADDER_OPTIONS = "1. Snake 2. Ladder 3. None";
    public static final String TOP_LESS_THAN_BOTTOM =
            "Top can not be on less or equal level to bottom. Please select the option again and provide correct values.";
    public static final String SNAKE_LADDER_ALREADY_EXIST =
            "There is already snake/ladder at this position. Please select the option again and proceed with values.";
    private static Board board = new Board();

    public static Board buildBoard(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add snakes' and ladders' positions : ");
        System.out.println(SNAKE_LADDER_OPTIONS);
        int option = scanner.nextInt();
        while (option!=3){
            if (option == 1){
                createSnakeOnBoard();
            }
            else if (option == 2) {
                createLadderOnBoard();
            }
            else {
                System.out.println("You have entered incorrect option. Please Enter the correct option.");
            }
            System.out.println(SNAKE_LADDER_OPTIONS);
            option = scanner.nextInt();
        }
        return board;
    }
    public static void createLadderOnBoard() {
        System.out.println("Enter the bottom position of ladder : ");
        int bottom = scanner.nextInt();
        System.out.println("Enter the top position of ladder: ");
        int top = scanner.nextInt();
        if (areValuesInRange(bottom, top)) {
            if ((bottom - 1) / 10 < (top - 1) / 10) {
                createSnakeOrLadderOnBoard(bottom, top);
            } else {
                System.out.println(TOP_LESS_THAN_BOTTOM);
            }
        }
        else {
            System.out.println(VALUE_NOT_IN_RANGE);
        }
    }

    public static void createSnakeOnBoard() {
        System.out.println("Enter the position of head of Snake : ");
        int headOfSnake = scanner.nextInt();
        System.out.println("Enter the position of tail of Snake : ");
        int tailOfSnake = scanner.nextInt();
        if (areValuesInRange(tailOfSnake, headOfSnake)) {
            if ((headOfSnake - 1) / 10 > (tailOfSnake - 1) / 10) {
                createSnakeOrLadderOnBoard(headOfSnake, tailOfSnake);
            } else {
                System.out.println(TOP_LESS_THAN_BOTTOM);
            }
        }
        else {
            System.out.println(VALUE_NOT_IN_RANGE);
        }
    }

    private static void createSnakeOrLadderOnBoard(int key, int value) {
        if (board.doesSnakeOrLadderExistAtPosition(key, value)) {
            System.out.println(SNAKE_LADDER_ALREADY_EXIST);
        } else {
            board.addSnakesOrLadderPosition(key, value);
        }
    }

    private static boolean areValuesInRange(int bottom, int top) {
        return bottom > 1 && bottom < 100 && top < 100 && top > 1;
    }
}
