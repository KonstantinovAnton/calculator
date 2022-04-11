package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Autorization extends AppCompatActivity implements View.OnClickListener {

    Button loginBtn;
    Button signinBtn;

    EditText usernameField;
    EditText passwordField;

    DBHelper dbHelper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorization);


        loginBtn = findViewById(R.id.loginBtn);
        signinBtn = findViewById(R.id.signinBtn);
        usernameField = findViewById(R.id.usernameField);
        passwordField = findViewById(R.id.passwordField);

        loginBtn.setOnClickListener(this);
        signinBtn.setOnClickListener(this);

        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginBtn:
                Cursor logCursor =  database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

                boolean logged = false;

                if(logCursor.moveToFirst())
                {
                    int usernameIndex = logCursor.getColumnIndex(DBHelper.KEY_NAME);
                    int passwordIndex = logCursor.getColumnIndex(DBHelper.KEY_MAIL);
                    do{
                        if(usernameField.getText().toString().equals(logCursor.getString(usernameIndex)) && passwordField.getText().toString().equals(logCursor.getString(passwordIndex))){
                            startActivity(new Intent(this,MainActivity.class));
                            logged = true;
                            break;
                        }
                    }while(logCursor.moveToNext());
                }
                logCursor.close();

                if(!logged) Toast.makeText(this,"Нету такого", Toast.LENGTH_LONG).show();

                break;

            case R.id.signinBtn:
                Cursor signCursor =  database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

                boolean finded = false;
                if(signCursor.moveToFirst()){
                    int usernameIndex = signCursor.getColumnIndex(DBHelper.KEY_NAME);
                    do{
                        if(usernameField.getText().toString().equals(signCursor.getString(usernameIndex))){
                            Toast.makeText(this,"Уже зареган", Toast.LENGTH_LONG).show();
                            finded = true;
                       break;

                        }
                    }while(signCursor.moveToNext());
                    if(!finded){
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(DBHelper.KEY_NAME,usernameField.getText().toString());
                        contentValues.put(DBHelper.KEY_MAIL, passwordField.getText().toString());
                        database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                        Toast.makeText(this,"Регистрация прошла",Toast.LENGTH_LONG).show();
                    }
                    signCursor.close();
                    break;
                }

                break;
    }
}}
