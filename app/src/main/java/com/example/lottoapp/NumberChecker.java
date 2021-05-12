package com.example.lottoapp;

import java.util.Arrays;

public class NumberChecker {

    private final int[] gameNumbers;

    public NumberChecker(int[] gameNumbers) {
        this.gameNumbers = Arrays.stream(gameNumbers).sorted().toArray();
    }

    public boolean allEquals(int[] resultNumbers) {
        return Arrays.equals(gameNumbers, Arrays.stream(resultNumbers).sorted().toArray());
    }

    public boolean exists(int number) {
        return Arrays.stream(gameNumbers).anyMatch(i -> i == number);
    }

    public long numOfMatches(int[] resultNumbers) {
        return Arrays.stream(gameNumbers)
                .filter(i -> Arrays.stream(resultNumbers).anyMatch(v -> v == i))
                .count();
    }

    public boolean numbersReady() {
        return gameNumbers.length == 5;
    }
}
