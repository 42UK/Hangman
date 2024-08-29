package org.example;

import java.util.LinkedList;

public class Hang {
    private static LinkedList<String> levelsQue;

    public Hang() {
        levelsQue = new LinkedList<>();
        levelsQue.add("""
                +-------+
                        |
                        |
                        |
                        |
                        |
                 ===============""");
        levelsQue.add("""
                 +-------+
                 |       |
                         |
                         |
                         |
                         |
                 ===============\
                """);
        levelsQue.add("""
                 +-------+
                 |       |
                 0       |
                         |
                         |
                         |
                 ===============\
                """);
        levelsQue.add("""
                 +-------+
                 |       |
                 0       |
                /        |
                         |
                         |
                 ===============""");
        levelsQue.add("""
                 +-------+
                 |       |
                 O       |
                / \\      |
                         |
                         |
                 ===============""");
        levelsQue.add("""
                  +-------+
                  |       |
                  0       |
                 /|\\      |
                          |
                          |
                 ===============\
                """);
        levelsQue.add("""
                  +-------+
                  |       |
                  0       |
                 /|\\      |
                 /        |
                          |
                 ===============\
                """);
        levelsQue.add("""
                  +-------+
                  |       |
                  0       |
                 /|\\      |
                 / \\      |
                          |
                 ===============\
                """);
    }

    public static String getLevel() {
        return levelsQue.pop();
    }
}


