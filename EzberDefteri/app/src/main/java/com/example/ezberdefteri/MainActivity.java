package com.example.ezberdefteri;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static Database db;
    private RecyclerView rv;
    private ArrayList<ListItems> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new Database(this);

        Button kelimeEkle = (Button)findViewById(R.id.btnAdd);
        kelimeEkle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, AddRecord.class);
                startActivity(intent);
                finish();
            }
        });

        dataList = new ArrayList<ListItems>();
        readAllRecords();

        rv = (RecyclerView) findViewById(R.id.recView);
        Adapter adapter = new Adapter(this, dataList);
        rv.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
    }

    public void additem(String ad, String soyad){
        if(ad != "" && soyad != ""){
            ListItems temp = new ListItems();
            temp.setKelime(ad);
            temp.setAnlam(soyad);
            dataList.add(temp);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            options();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void options(){
        Intent intent = new Intent(MainActivity.this, Options.class);
        startActivity(intent);
    }

    private String[] sutunlar = {"kelime", "anlam"};
    private void readAllRecords(){
        SQLiteDatabase veriler = db.getReadableDatabase();
        Cursor okunanlar = veriler.query("veriler", sutunlar, null, null, null, null, null);
        while(okunanlar.moveToNext()){
            String oKelime = okunanlar.getString(okunanlar.getColumnIndex("kelime"));
            String oAnlam = okunanlar.getString(okunanlar.getColumnIndex("anlam"));
            additem(oKelime, oAnlam);
        }
    }

    public static void delete(String silinecek){
        db.onDelete(silinecek);
    }
}
