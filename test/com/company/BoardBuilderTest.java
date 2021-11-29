package com.company;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

class BoardBuilderTest {


    @Test
    public void testCreateBoardWithoutSnakeAndLadder(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("3".getBytes());
        System.setIn(in);
        Board board = BoardBuilder.buildBoard();
        Assert.assertEquals(0, board.getSnakes().size());
        Assert.assertEquals(0, board.getLadders().size());
        System.setIn(sysInBackup);
    }

    @Test
    public void testCreateSnakeOnBoard(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n34\n21\n3".getBytes());
        System.setIn(in);
        Board board = BoardBuilder.buildBoard();
        Assert.assertEquals(1, board.getSnakes().size());
        Assert.assertEquals(String.valueOf(21), String.valueOf(board.getSnakes().get(34)));
        System.setIn(sysInBackup);
    }

    @Test
    public void testCreateLadderOnBoard(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("2\n21\n34\n3".getBytes());
        System.setIn(in);
        Board board = BoardBuilder.buildBoard();
        Assert.assertEquals(1, board.getLadders().size());
        Assert.assertEquals(String.valueOf(34), String.valueOf(board.getLadders().get(21)));
        System.setIn(sysInBackup);
    }

    @Test
    public void testShouldNotCreateLadderWithBottomBiggerThanTop(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("2\n34\n21\n3".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setIn(in);
        Board board = BoardBuilder.buildBoard();
        Assert.assertEquals(0, board.getLadders().size());
        System.setIn(sysInBackup);
    }

    @Test
    public void testShouldNotCreateSnakeWithTailBiggerThanHead(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n21\n34\n3".getBytes());
        System.setIn(in);
        Board board = BoardBuilder.buildBoard();
        Assert.assertEquals(0, board.getSnakes().size());
        System.setIn(sysInBackup);
    }

    @Test
    public void testShouldNotCreateSnakeWhenSnakeAlreadyExistsAtThePosition(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n34\n21\n1\n21\n5\n3".getBytes());
        System.setIn(in);
        Board board = BoardBuilder.buildBoard();
        Assert.assertEquals(1, board.getSnakes().size());
        System.setIn(sysInBackup);
    }

    @Test
    public void testShouldNotCreateSnakeWhenLadderAlreadyExistsAtThePosition(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("2\n21\n34\n1\n21\n5\n3".getBytes());
        System.setIn(in);
        Board board = BoardBuilder.buildBoard();
        Assert.assertEquals(0, board.getSnakes().size());
        System.setIn(sysInBackup);
    }

    @Test
    public void testShouldNotCreateLadderWhenSnakeAlreadyExistsAtThePosition(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n34\n21\n2\n21\n45\n3".getBytes());
        System.setIn(in);
        Board board = BoardBuilder.buildBoard();
        Assert.assertEquals(0, board.getLadders().size());
        System.setIn(sysInBackup);
    }

    @Test
    public void testShouldNotCreateLadderWhenLadderAlreadyExistsAtThePosition(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("2\n21\n34\n2\n4\n21\n3".getBytes());
        System.setIn(in);
        Board board = BoardBuilder.buildBoard();
        Assert.assertEquals(1, board.getLadders().size());
        System.setIn(sysInBackup);
    }

    @Test
    public void testShouldNotCreateSnakeWhenLThePositionIsOutOfBound(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n1\n99\n3".getBytes());
        System.setIn(in);
        Board board = BoardBuilder.buildBoard();
        Assert.assertEquals(0, board.getSnakes().size());
        System.setIn(sysInBackup);
    }

    @Test
    public void testShouldNotCreateLadderWhenLThePositionIsOutOfBound(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("2\n4\n100\n3".getBytes());
        System.setIn(in);
        Board board = BoardBuilder.buildBoard();
        Assert.assertEquals(0, board.getLadders().size());
        System.setIn(sysInBackup);
    }

}