package com.example.lottoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] numbers = new int[5];

        final Random myRandom = new Random();

        Button buttonGenerate = (Button)findViewById(R.id.generate);
        final TextView textGenerateNumber = (TextView)findViewById(R.id.firstNumber);
        final TextView textGenerateNumber2 = (TextView)findViewById(R.id.secondNumber);
        final TextView textGenerateNumber3 = (TextView)findViewById(R.id.thirdNumber);
        final TextView textGenerateNumber4 = (TextView)findViewById(R.id.fourthNumber);
        final TextView textGenerateNumber5 = (TextView)findViewById(R.id.fifthNumber);

        buttonGenerate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int i = 0;
                for(i = 0; i<=4; i++){
                    numbers[i] = myRandom.nextInt(51 - 1) + 1;
                }

                Arrays.sort(numbers);
                textGenerateNumber.setText(String.valueOf(numbers[0]));
                textGenerateNumber2.setText(String.valueOf(numbers[1]));
                textGenerateNumber3.setText(String.valueOf(numbers[2]));
                textGenerateNumber4.setText(String.valueOf(numbers[3]));
                textGenerateNumber5.setText(String.valueOf(numbers[4]));
            }
        });
    }


}