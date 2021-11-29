package com.company;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.*;

class MainTest {
    @Test
    public void testMaximumSlideForPlayerNeedsToBeZeroWhenNoSnake(){
        InputStream sysInBackup = System.in;
        OutputStream sysOutBackup = System.out;
        ByteArrayInputStream in = new ByteArrayInputStream("3\n3\nA\nB\nC\nfalse".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
        String[] args = {};
        Main.main(args);

        Assert.assertTrue(out.toString().contains("Maximum Slides in the game for a player : 0"));
        Assert.assertTrue(out.toString().contains("Minimum Slides in the game for a player : 0"));
        Assert.assertTrue(out.toString().contains("Maximum Climbs in the game for a player : 0"));
        Assert.assertTrue(out.toString().contains("Minimum Climbs in the game for a player : 0"));

        System.setIn(sysInBackup);
        System.setOut(new PrintStream(sysOutBackup));
    }

}