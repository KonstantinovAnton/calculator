package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

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
    Button history;

    String act;
    String sign;
    int activiti;
    int index;

float res;
    float num1;
    float num2;


    String[] arrayOfStrings;


    boolean fnum;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        act = "";
        fnum = true;
        index = 0;
        arrayOfStrings = new String[10];

        firstNumber = findViewById(R.id.firstNumber);
        secondNumber = findViewById(R.id.secondNumber);
        result = findViewById(R.id.result);
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
        history = findViewById(R.id.history);


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
        history.setOnClickListener(this);
        zero.setOnClickListener(this);


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
                } else {
                    numText = secondNumber.getText().toString();
                    numText += button.getText().toString();
                    secondNumber.setText(numText);
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

                num1 = Float.valueOf(firstNumber.getText().toString());
                num2 = Float.valueOf(secondNumber.getText().toString());
                if(activiti==1){
                res = num1 + num2;
                arrayOfStrings[index] = Float.toString(num1) + " + " + Float.toString(num2)+" = "+Float.toString(res);
                }
                if(activiti==2){
                    res = num1 - num2;
                    arrayOfStrings[index] = Float.toString(num1) + " - " + Float.toString(num2)+" = "+Float.toString(res);
                }
                if(activiti==3){
                    res = num1 * num2;
                    arrayOfStrings[index] = Float.toString(num1) + " * " + Float.toString(num2)+" = "+Float.toString(res);
                }
                if(activiti==4){
                    res = num1 / num2;
                    arrayOfStrings[index] = Float.toString(num1) + " / " + Float.toString(num2)+" = "+Float.toString(res);
                }

                result.setText("= " + String.valueOf(res));

                index++;
                break;


            case R.id.clear:
                firstNumber.setText("");
                secondNumber.setText("");
                result.setText("");
                signArea.setText("");
                fnum = !fnum;

                break;

            case R.id.history:
                Intent intent = new Intent(this,MainActivity2.class);
                intent.putExtra("values",arrayOfStrings);
                startActivity(intent);



                break;
        }
    }
}