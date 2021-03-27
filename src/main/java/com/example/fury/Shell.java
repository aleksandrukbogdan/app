package com.example.fury;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;


import androidx.fragment.app.Fragment;

public class Shell extends Activity  implements ExecuteFragment{
    private Fragment fragment;
    protected Bitmap[] pictures;
    protected Bitmap fury;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Если Android 4.4 - включить IMMERSIVE MODE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        setContentView(R.layout.shell);

    }

    @Override
    public void NumberTransaction(String fragment) {

    }

    @Override
    public void StartAlphabet(int letter_choose) {

    }

    @Override
    public void LoadingAlphabet(Bitmap v_story, Bitmap v_char, Bitmap[] pictures, String[] pic_string, boolean[] correct, int pixels) {

    }
}
