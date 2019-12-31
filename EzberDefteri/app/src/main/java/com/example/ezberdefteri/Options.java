package com.example.ezberdefteri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class Options extends AppCompatActivity {
    private Switch myswitch;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState()==true){
            setTheme(R.style.darktheme);
        }else{
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        myswitch = (Switch) findViewById(R.id.switch1);
        if(sharedPref.loadNightModeState()==true){
            myswitch.setChecked(true);
        }
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked) {
                    sharedPref.setNightModeState(true);
                    restartApp();
                }else{
                    sharedPref.setNightModeState(false);
                    restartApp();
                }
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.5));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -100;
        getWindow().setAttributes(params);

        TextView tv = (TextView) findViewById(R.id.tv1);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this, Information.class);
                startActivity(intent);
            }
        });
    }

    public void restartApp(){
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }
}
