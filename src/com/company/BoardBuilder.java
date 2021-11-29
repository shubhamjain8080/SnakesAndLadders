package com.company;

import java.util.Scanner;

public class BoardBuilder {
    private static Board board = new Board();

    public static Board buildBoard(){
        System.out.println("Please add snakes' and ladders' positions : ");
        System.out.println(Constants.SNAKE_LADDER_OPTIONS);
        int option = Constants.scanner.nextInt();
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
            System.out.println(Constants.SNAKE_LADDER_OPTIONS);
            option = Constants.scanner.nextInt();
        }
        return board;
    }
    private static void createLadderOnBoard() {
        System.out.println("Enter the bottom position of ladder : ");
        int bottom = Constants.scanner.nextInt();
        System.out.println("Enter the top position of ladder: ");
        int top = Constants.scanner.nextInt();
        if (areValuesInRange(bottom, top)) {
            if ((bottom - 1) / 10 < (top - 1) / 10) {
                board.createLadderOnBoard(bottom, top);
            } else {
                System.out.println(Constants.TOP_LESS_THAN_BOTTOM);
            }
        }
        else {
            System.out.println(Constants.VALUE_NOT_IN_RANGE);
        }
    }

    private static void createSnakeOnBoard() {
        System.out.println("Enter the position of head of Snake : ");
        int headOfSnake = Constants.scanner.nextInt();
        System.out.println("Enter the position of tail of Snake : ");
        int tailOfSnake = Constants.scanner.nextInt();
        if (areValuesInRange(tailOfSnake, headOfSnake)) {
            if ((headOfSnake - 1) / 10 > (tailOfSnake - 1) / 10) {
                board.createSnakeOnBoard(headOfSnake, tailOfSnake);
            } else {
                System.out.println(Constants.TOP_LESS_THAN_BOTTOM);
            }
        }
        else {
            System.out.println(Constants.VALUE_NOT_IN_RANGE);
        }
    }

    private static boolean areValuesInRange(int bottom, int top) {
        return bottom > 1 && bottom < 100 && top < 100 && top > 1;
    }
}
