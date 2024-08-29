package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PlayGame game = new PlayGame();
        System.out.println("""
                                
                Приветсвую вас, дорогой игрок. Это игра под названием виселица.
                За каждую совершенную ошибку будет появляться новая часть виселицы
                Колличество попыток ограничено их - \u001B[36m8\u001B[0m
                Желаем вам удачи!""");
        game.start(in);
    }
}
