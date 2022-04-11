package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    Button back;
    Button btnAdd;
    Button btnClear;

    EditText etName;
    EditText etEmail;

    DBHelper dbHelper;
    SQLiteDatabase database;
    ContentValues contentValues;

    TableLayout dbOutput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        back = findViewById(R.id.back);
        btnAdd = findViewById(R.id.btnAdd);

        btnClear = findViewById(R.id.btnClear);


        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);




        back.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        btnClear.setOnClickListener(this);

        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();




        UpdateTable();


    }

    public void UpdateTable()
    {
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);
            TableLayout dbOutput = findViewById(R.id.dbOutput);
            dbOutput.removeAllViews();
            do{
                TableRow dbOutputRow = new TableRow(this);
                dbOutputRow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                LinearLayout.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

                TextView outputID = new TextView(this);
                params.weight = 1.0f;
                outputID.setLayoutParams(params);
                outputID.setText(cursor.getString(idIndex));
                dbOutputRow.addView(outputID);

                TextView outputIName = new TextView(this);
                params.weight = 3.0f;
                outputIName.setLayoutParams(params);
                outputIName.setText(cursor.getString(nameIndex));
                dbOutputRow.addView(outputIName);

                TextView outputMail = new TextView(this);
                params.weight = 3.0f;
                outputMail.setLayoutParams(params);
                outputMail.setText(cursor.getString(emailIndex));
                dbOutputRow.addView(outputMail);

                Button deleteBtn = new Button(this);
                deleteBtn.setOnClickListener(this);
                params.weight = 1.0f;
                deleteBtn.setLayoutParams(params);
                deleteBtn.setText("Удалить запись");
                deleteBtn.setId(cursor.getInt(idIndex));
                dbOutput.addView(deleteBtn);

                dbOutput.addView(dbOutputRow);

            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public void onClick(View view) {





        switch (view.getId()) {
            case R.id.back:
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        break;
            case R.id.btnAdd:
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();

                contentValues = new ContentValues();
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_MAIL, email);

                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                UpdateTable();
                break;



            case R.id.btnClear:
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                UpdateTable();
                TableLayout dbOutput = findViewById(R.id.dbOutput);
                dbOutput.removeAllViews();
                break;
            default:
                View outputDBRow = (View) view.getParent();
                ViewGroup outputDB = (ViewGroup) outputDBRow.getParent();
                outputDB.removeView(outputDBRow);
                outputDB.invalidate();

                database.delete(DBHelper.TABLE_CONTACTS, DBHelper.KEY_ID + " = ?", new String[]{String.valueOf((view.getId()))});
               contentValues = new ContentValues();

                Cursor cursorUpdater =  database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
                if(cursorUpdater.moveToFirst())
                {
                    int idIndex = cursorUpdater.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursorUpdater.getColumnIndex(DBHelper.KEY_NAME);
                    int emailIndex = cursorUpdater.getColumnIndex(DBHelper.KEY_MAIL);
                    int realID = 1;
                    do{
                        if(cursorUpdater.getInt(idIndex)>realID)
                        {
                            contentValues.put(DBHelper.KEY_ID, realID);
                            contentValues.put(DBHelper.KEY_NAME, cursorUpdater.getString(nameIndex));
                            contentValues.put(DBHelper.KEY_MAIL,cursorUpdater.getString(emailIndex));
                            database.replace(DBHelper.TABLE_CONTACTS,null,contentValues);

                        }
                        realID++;


                    } while (cursorUpdater.moveToNext());
                    if(cursorUpdater.moveToLast()){
                        database.delete(DBHelper.TABLE_CONTACTS,DBHelper.KEY_ID + " = ?", new String[]{cursorUpdater.getString(idIndex)});
                    }
                    UpdateTable();
                }

                break;


    }
}
}