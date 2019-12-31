package com.example.ezberdefteri;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRecord extends Activity {
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        db = new Database(this);
        final EditText et11 = (EditText) findViewById(R.id.editText1);
        final EditText et12 = (EditText) findViewById(R.id.editText2);

        showSoftKeyboard(et11);

        Button b1 = (Button)findViewById(R.id.button1);
        Button b2 = (Button)findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(et11.getText().toString().length() != 0 && et12.getText().toString().length() != 0){
                    db.addRecord(et11.getText().toString(), et12.getText().toString());
                    Toast.makeText(getApplicationContext(), "Kaydedildi.", Toast.LENGTH_SHORT).show();
                    et11.setText("");
                    et12.setText("");
                    showSoftKeyboard(et11);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Lütfen alanları doldurunuz.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                gotoMainActivity();
            }
        });
    }

    @Override
    public void onBackPressed() {
        gotoMainActivity();
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void gotoMainActivity(){
        Intent intent = new Intent(AddRecord.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}