package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;

public class PlayGame {

    private StringBuilder currentWord;   // Текущая строка
    private StringBuilder hideWord;      // Строка с символам _ которые меняют на буквы
    private StringBuilder necessaryWord; // Нужно для проверки с итоговым словом

    public void start(Scanner in) {
        System.out.println("Для начала новой игры нажмите 1, для выхода 0");
        if (in.hasNextInt()) {
            int num = in.nextInt();
            if (num == 1) {
                runGame(in);
            } else {
                System.out.println("\u001b[35;1mBye-Bye\u001b[0m");
                System.exit(0);
            }
        }
    }

    private void runGame(Scanner in) {
        Hang hng = new Hang();
        int countMistake = 8;                                    // Счётчик допущенных ошибок
        StringBuilder wrongSymbol = new StringBuilder();         // Для вывода не правильно введенных символов
        getWord();
        System.out.println("Слово загадано, можете начинать, вводите по одной букве " + "\nТеперь, вам необходимо отгадать слово, oно состоит из \u001B[31m" + necessaryWord.length() + "\u001B[0m букв.");
        while (countMistake >= 0) {
            String symbol = in.next().toLowerCase();
            if (check(symbol) || checkDuplicateSymbol(symbol)) {
                replaceCharToSymbol(symbol);
                System.out.print("Не правильно введенные символы " + "- " + wrongSymbol + "\n");
                System.out.println("Колличесвто оставшихся попыток - " + countMistake);
                System.out.println(hideWord);
                if (hideWord.toString().contentEquals(necessaryWord)) {
                    System.out.println("\u001B[32mВы отгадали слово и тем самым спасли человека \u001B[0m");
                    start(in);
                }
            } else {
                wrongSymbol.append(symbol).append(" ");
                System.out.print("Не правильно введенные символы " + "- " + wrongSymbol + "\n");
                System.out.println("Колличесвто оставшихся попыток - " + --countMistake);
                System.out.println(hideWord.toString()); //Выводит правильно введенные символы
                System.out.println(hng.getLevel()); // Выводит виселицу
            }
            if (countMistake == 0) {
                System.out.println("\u001B[31mВы проиграли \u001B[0m");
                start(in);
                break;
            }
        }
    }

    private void getWord() {
        long numberLineWithWord = (long) (Math.random() * 108); // Случайным образом выбирается число (номер строки) для слова 108 - колличество строк в словаре
        try {
            String str = Files.lines(Paths.get("src/main/resources/words.txt")).skip(numberLineWithWord).findFirst().get().toLowerCase(Locale.ROOT);
            currentWord = new StringBuilder(str);
            necessaryWord = new StringBuilder(str);
            createHideWord();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createHideWord() {
        hideWord = new StringBuilder();
        hideWord.append("_".repeat(Math.max(0, currentWord.length())));
    }

    private void replaceCharToSymbol(String str) {
        while (currentWord.toString().contains(str)) {
            hideWord.replace(currentWord.toString().indexOf(str), currentWord.toString().indexOf(str) + 1, str);
            currentWord.replace(currentWord.toString().indexOf(str), currentWord.toString().indexOf(str) + 1, "_");
            if (!currentWord.toString().contains(str)) {
                break;
            }
        }
    }

    private boolean check(String str) {
        return currentWord.toString().contains(str);
    }

    private boolean checkDuplicateSymbol(String symbol) {
        return hideWord.toString().contains(symbol);
    }
}
