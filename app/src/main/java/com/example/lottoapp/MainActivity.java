package com.example.lottoapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText firstNumber;
    private TextInputEditText secondNumber;
    private TextInputEditText thirdNumber;
    private TextInputEditText fourthNumber;
    private TextInputEditText fifthNumber;

    private Button raffle;

    private NumberChecker playedNumbers;
    private int[] numbers = new int[5];

    private TextView textGenerateNumber;
    private TextView textGenerateNumber2;
    private TextView textGenerateNumber3;
    private TextView textGenerateNumber4;
    private TextView textGenerateNumber5;

    private ImageView trophy;

    private static final int NUMBER_OF_TRIES = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();
    }

    private void setupView() {
        setupNumberFields();
        setupTextGenerate();
        raffle.setOnClickListener(v -> {
            if (playedNumbers.numbersReady()) {
                String raffleString = getString(R.string.raffle);
                if (raffle.getText().toString().equals(raffleString)) {
                    randomNumberGenerator();
                    if (playedNumbers.allEquals(numbers)) {
                        trophy = findViewById(R.id.winner_trophy);
                        trophy.setVisibility(View.VISIBLE);
                        trophy.setOnClickListener(i -> trophy.setVisibility(View.INVISIBLE));
                    }
                    String resetString = getString(R.string.reset);
                    raffle.setText(resetString);
                } else {
                    String placeholder = getString(R.string.placeholder);
                    textGenerateNumber.setText(placeholder);
                    textGenerateNumber2.setText(placeholder);
                    textGenerateNumber3.setText(placeholder);
                    textGenerateNumber4.setText(placeholder);
                    textGenerateNumber5.setText(placeholder);
                }
            }
        });
    }

    private void setupTextGenerate() {
        textGenerateNumber = findViewById(R.id.resultFirstNumber);
        textGenerateNumber2 = findViewById(R.id.resultSecondNumber);;
        textGenerateNumber3 = findViewById(R.id.resultThirdNumber);;
        textGenerateNumber4 = findViewById(R.id.resultFourthNumber);;
        textGenerateNumber5 = findViewById(R.id.resultFifthNumber);;
    }

    private void setupNumberFields() {
        firstNumber = findViewById(R.id.first_number);
        setupTextWatcher(firstNumber);

        secondNumber = findViewById(R.id.second_number);
        setupTextWatcher(secondNumber);

        thirdNumber = findViewById(R.id.third_number);
        setupTextWatcher(thirdNumber);

        fourthNumber = findViewById(R.id.fourth_number);
        setupTextWatcher(fourthNumber);

        fifthNumber = findViewById(R.id.fifth_number);
        setupTextWatcher(fifthNumber);

        raffle = findViewById(R.id.raffle);
    }

    private void showToast(@StringRes int errorMessageId) {
        Toast.makeText(this, errorMessageId, Toast.LENGTH_SHORT).show();
    }

    private boolean wasNumberAlreadyInserted(int number) {
        int[] numbers = getNumberListForTextInputEditText()
                .stream()
                .mapToInt(Integer::intValue)
                .toArray();

        playedNumbers = new NumberChecker(numbers);
        return playedNumbers.exists(number);
    }

    private Integer getIntNumberFromStringNumber(String number) {
        return Integer.parseInt(number);
    }

    private boolean isNumberValid(int number) {
        return number >= 1 && number <= 50;
    }

    private void setupTextWatcher(View view) {
        if (view != null) {
            TextInputEditText textInputEditText = (TextInputEditText) view;
            textInputEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (textInputEditText.getText() != null) {
                        try {
                            int number = getIntNumberFromStringNumber(textInputEditText.getText().toString());
                            if (!isNumberValid(number)) {
                                showToast(R.string.invalid_number);
                                textInputEditText.getText().clear();
                            } else if (wasNumberAlreadyInserted(number)) {
                                showToast(R.string.number_already_inserted);
                                textInputEditText.getText().clear();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        validateRaffleButtonConditions();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    private ArrayList<Integer> getNumberListForTextInputEditText() {
        return getNumberListForTextInputEditText(firstNumber, secondNumber, thirdNumber, fourthNumber, fifthNumber);
    }

    private ArrayList<Integer> getNumberListForTextInputEditText(TextInputEditText... textInputEditTexts) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (TextInputEditText textInputEditText : textInputEditTexts) {
            try {
                Integer number = getIntNumberFromStringNumber(textInputEditText.getText().toString());
                numbers.add(number);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return numbers;
    }

    private void validateRaffleButtonConditions() {
        raffle.setEnabled(getNumberListForTextInputEditText().size() == NUMBER_OF_TRIES);
    }

    private void randomNumberGenerator() {
        final Random myRandom = new Random();
        for (int i = 0; i <= 4; i++) {
            numbers[i] = myRandom.nextInt(51 - 1) + 1;
        }

        Arrays.sort(numbers);

        AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f);
        fadeIn.setDuration(1200);
        fadeIn.setFillAfter(true);

        textGenerateNumber.startAnimation(fadeIn);
        textGenerateNumber.setText(String.valueOf(numbers[0]));

        textGenerateNumber2.startAnimation(fadeIn);
        textGenerateNumber2.setText(String.valueOf(numbers[1]));

        textGenerateNumber3.startAnimation(fadeIn);
        textGenerateNumber3.setText(String.valueOf(numbers[2]));

        textGenerateNumber4.startAnimation(fadeIn);
        textGenerateNumber4.setText(String.valueOf(numbers[3]));
        
        textGenerateNumber5.startAnimation(fadeIn);
        textGenerateNumber5.setText(String.valueOf(numbers[4]));



    }
}
