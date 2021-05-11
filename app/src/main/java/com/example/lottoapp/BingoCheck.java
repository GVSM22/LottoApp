package com.example.lottoapp;

import java.util.Arrays;

public class BingoCheck {

    private final int[] winningNumbers;

    public BingoCheck(int[] winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public boolean checkResult(int[] gameNumbers) {
        return Arrays.equals(winningNumbers, gameNumbers);
    }
}
