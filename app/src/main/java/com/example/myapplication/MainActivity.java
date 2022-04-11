package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.FloatArrayEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView firstNumber;
    TextView secondNumber;
    TextView result;
    TextView signArea;
    TextView historyWindow;

    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button plus;
    Button minus;
    Button multiply;
    Button divide;
    Button equals;
    Button clear;
    Button zero;
    Button button1;
    Button getRes;
    Button forward;
    Button backward;
    Button goToDB;

    String act;
    String sign;

    int activiti;
    int index;
    int indexForShow;


float res;
    float num1;
    float num2;
    float[] arrayOfResults;

    String[] arrayOfOperations;

    boolean fnum;
    boolean firstNumberIsAdded;
    boolean secondNumberIsAdded;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        act = "";
        fnum = true;
        firstNumberIsAdded = false;
        secondNumberIsAdded = false;

        index = -1;


        arrayOfOperations = new String[10];
        arrayOfResults = new float[10];

        firstNumber = findViewById(R.id.firstNumber);
        secondNumber = findViewById(R.id.secondNumber);
        result = findViewById(R.id.result);
        historyWindow = findViewById(R.id.historyWindow);
        signArea = findViewById(R.id.signArea);

        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);

        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        equals = findViewById(R.id.equals);
        clear = findViewById(R.id.clear);

        getRes = findViewById(R.id.getRes);
        forward = findViewById(R.id.forward);
        backward = findViewById(R.id.backward);
        goToDB = findViewById(R.id.goToDB);



        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        divide.setOnClickListener(this);
        multiply.setOnClickListener(this);
        clear.setOnClickListener(this);
        equals.setOnClickListener(this);
        getRes.setOnClickListener(this);
        forward.setOnClickListener(this);
        backward.setOnClickListener(this);
        zero.setOnClickListener(this);
        goToDB.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zero:
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:

                Button button = (Button) view;
                String numText;

                if (fnum) {
                    numText = firstNumber.getText().toString();
                    numText += button.getText().toString();
                    firstNumber.setText(numText);
                    firstNumberIsAdded = true;
                } else {
                    numText = secondNumber.getText().toString();
                    numText += button.getText().toString();
                    secondNumber.setText(numText);
                    secondNumberIsAdded = true;
                }


                break;
            case R.id.plus:
                button1 = (Button) view;
                if (act == button1.getText().toString()) {
                    act = button1.getText().toString();
                    if (!fnum) {
                        fnum = !fnum;
                    }
                } else {

                    fnum = !fnum;
                }
                activiti = 1;



                signArea.setText("+");
                break;
            case R.id.minus:
                button1 = (Button) view;
                if (act == button1.getText().toString()) {
                    act = button1.getText().toString();
                    if (!fnum) {
                        fnum = !fnum;
                    }
                } else {

                    fnum = !fnum;
                }
                activiti = 2;



                signArea.setText("-");
                break;

            case R.id.multiply:
                button1 = (Button) view;
                if (act == button1.getText().toString()) {
                    act = button1.getText().toString();
                    if (!fnum) {
                        fnum = !fnum;
                    }
                } else {

                    fnum = !fnum;
                }
                activiti = 3;



                signArea.setText("*");
                break;
            case R.id.divide:
                button1 = (Button) view;
                if (act == button1.getText().toString()) {
                    act = button1.getText().toString();
                    if (!fnum) {
                        fnum = !fnum;
                    }
                } else {

                    fnum = !fnum;
                }
                activiti = 4;



                signArea.setText("/");

                break;




            case R.id.equals:

                if( firstNumberIsAdded == true && secondNumberIsAdded == true) {

                    num1 = Float.valueOf(firstNumber.getText().toString());
                    num2 = Float.valueOf(secondNumber.getText().toString());

                    index++;

                    if (index == 10) {
                        for (int i = 0; i < 9; i++) {
                            arrayOfResults[i] = arrayOfResults[i + 1];
                            arrayOfOperations[i] = arrayOfOperations[i + 1];
                        }
                        index = 9;
                    }

                    if (activiti == 1) {

                        res = num1 + num2;
                        arrayOfOperations[index] = Float.toString(num1) + " + " + Float.toString(num2) + " = " + Float.toString(res);
                    }
                    if (activiti == 2) {
                        res = num1 - num2;
                        arrayOfOperations[index] = Float.toString(num1) + " - " + Float.toString(num2) + " = " + Float.toString(res);

                    }
                    if (activiti == 3) {
                        res = num1 * num2;
                        arrayOfOperations[index] = Float.toString(num1) + " * " + Float.toString(num2) + " = " + Float.toString(res);

                    }
                    if (activiti == 4) {
                        res = num1 / num2;
                        arrayOfOperations[index] = Float.toString(num1) + " / " + Float.toString(num2) + " = " + Float.toString(res);
                    }

                    result.setText("= " + String.valueOf(res));

                    arrayOfResults[index] = res;
                    indexForShow = index + 1;

                }
                break;


            case R.id.clear:
                firstNumber.setText("");
                secondNumber.setText("");
                result.setText("");
                signArea.setText("");
                historyWindow.setText("");
                fnum = !fnum;
                firstNumberIsAdded = false;
                secondNumberIsAdded = false;

                break;

            case R.id.backward:

                firstNumber.setText("");
                secondNumber.setText("");
                result.setText("");
                signArea.setText("");

                if(indexForShow!=0) indexForShow--;
                historyWindow.setText(arrayOfOperations[indexForShow]);
                break;

            case R.id.forward:

                firstNumber.setText("");
                secondNumber.setText("");
                result.setText("");
                signArea.setText("");

                if(indexForShow != index) indexForShow++;
                historyWindow.setText(arrayOfOperations[indexForShow]);

                break;

            case R.id.getRes:

                historyWindow.setText("");
                firstNumber.setText(Float.toString(arrayOfResults[indexForShow]));
                secondNumber.setText("");
                result.setText("");
                signArea.setText("");
                fnum = true;

                firstNumberIsAdded = true;

                break;
            case R.id.goToDB:
                Intent intent = new Intent(this,MainActivity2.class);
                startActivity(intent);
                break;
        }
    }
}