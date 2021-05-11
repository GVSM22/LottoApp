package com.example.lottoapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
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

    int[] numbers = new int[5];

    private TextView textGenerateNumber;
    private TextView textGenerateNumber2;
    private TextView textGenerateNumber3;
    private TextView textGenerateNumber4;
    private TextView textGenerateNumber5;

    private static final int NUMBER_OF_TRIES = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main)  ;

        setupView();
    }

    private void setupView() {
        raffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomNumberGenerator();
            }
        });
        setupNumberFields();
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
        int numberOfInsertions = 0;
        ArrayList<Integer> numbers = getNumberListForTextInputEditText();
        for(int i = 0; i < numbers.size(); i++){
            if (numbers.get(i) == number){
                numberOfInsertions++;
            }
        }
        return numberOfInsertions >= 2;
    }

    private Integer getIntNumberFromStringNumber(String number){
        return Integer.parseInt(number);
    }

    private boolean isNumberValid(int number) {
        if (number >= 1 && number <= 50) {
            return true;
        } else {
            return false;
        }
    }

    private void setupTextWatcher(View view){
        if (view != null){
            TextInputEditText textInputEditText = (TextInputEditText) view;
            textInputEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(textInputEditText.getText() != null){
                        try {
                            int number = getIntNumberFromStringNumber(textInputEditText.getText().toString());
                            if (!isNumberValid(number)) {
                                showToast(R.string.invalid_number);
                                textInputEditText.getText().clear();
                            } else if (wasNumberAlreadyInserted(number)){
                                showToast(R.string.number_already_inserted);
                                textInputEditText.getText().clear();
                            }
                        } catch (Exception e){
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

    private ArrayList<Integer> getNumberListForTextInputEditText(){
        return getNumberListForTextInputEditText(firstNumber, secondNumber, thirdNumber, fourthNumber, fifthNumber);
    }

    private ArrayList<Integer> getNumberListForTextInputEditText(TextInputEditText... textInputEditTexts){
        ArrayList<Integer> numbers = new ArrayList<>();
        for (TextInputEditText textInputEditText: textInputEditTexts){
            try {
                Integer number = getIntNumberFromStringNumber(textInputEditText.getText().toString());
                numbers.add(number);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return numbers;
    }

    private void validateRaffleButtonConditions(){
        raffle.setEnabled(getNumberListForTextInputEditText().size() == NUMBER_OF_TRIES);
    }

    private void randomNumberGenerator(){
        final Random myRandom = new Random();
        for(int i = 0; i<=4; i++){
            numbers[i] = myRandom.nextInt(51 - 1) + 1;
        }

        Arrays.sort(numbers);
        textGenerateNumber.setText(String.valueOf(numbers[0]));
        textGenerateNumber2.setText(String.valueOf(numbers[1]));
        textGenerateNumber3.setText(String.valueOf(numbers[2]));
        textGenerateNumber4.setText(String.valueOf(numbers[3]));
        textGenerateNumber5.setText(String.valueOf(numbers[4]));
    }
}
