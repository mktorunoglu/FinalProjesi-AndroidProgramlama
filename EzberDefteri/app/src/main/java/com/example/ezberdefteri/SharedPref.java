package com.example.ezberdefteri;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    SharedPreferences mySharedPref;
    public SharedPref(Context context){
        mySharedPref = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }
    // saving switch info
    public void setNightModeState(Boolean state){
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }
    // loading switch info
    public Boolean loadNightModeState(){
        Boolean state = mySharedPref.getBoolean("NightMode", false);
        return state;
    }
}
