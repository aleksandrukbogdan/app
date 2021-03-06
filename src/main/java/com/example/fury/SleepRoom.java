package com.example.fury;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class SleepRoom extends FragmentActivity {

    Chronometer mChronometer;
    ImageButton btn_kitchen, btn_pet, btn_bath, btn_room, btn_sleep;
    tamagochi tamagochi1 = new tamagochi(5, 5, 5, 5);
    ProgressBar pB_hungry, pB_happy, pB_clean, pB_tired;

    ImageView bgsleeproom;
    boolean firstPlayed = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        //Если Android 4.4 - включить  IMMERSIVE MODE

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }

        /*//Проверка целостности БД
        SQLiteDatabase sdb;
        sdb = new Database(this).getReadableDatabase();
        Cursor cursor = sdb.query("Tamagochi", new String[]{Database.HUNGRINESS_COLUMN,
                Database.HAPPINESS_COLUMN, Database.CLEANLINESS_COLUMN, Database.STRENGTH_COLUMN}, null, null, null, null, null);
        try {
            version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        //Проверка актуальной версии. Если не равно - значит было установлено обновление и следует запустить предзагрузку

            cursor.close();
            sdb.close();*/

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        //Если всё удачно, запуск активности
        setContentView(R.layout.sleeproom_activity);


        mChronometer = findViewById(R.id.chronos);
        mChronometer.setCountDown(false);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

                long elapsedMillis = mChronometer.getBase();
                tamagochi1.passTime();
                pB_hungry.setProgress(tamagochi1.getHungriness());
                pB_happy.setProgress(tamagochi1.getHappiness());
                pB_clean.setProgress(tamagochi1.getCleanliness());
                pB_tired.setProgress(tamagochi1.getStrength());
            }

        });

        btn_room = findViewById(R.id.btn_room);
        btn_kitchen = findViewById(R.id.btn_kitchen);
        /*btn_pet = findViewById(R.id.btn_pet);*/
        btn_bath = findViewById(R.id.btn_bath);
        btn_sleep = findViewById(R.id.btn_sleep);


        pB_hungry = findViewById(R.id.progressBar_hungry);
        pB_happy = findViewById(R.id.progressBar_happy);
        pB_clean = findViewById(R.id.progressBar_clean);
        pB_tired = findViewById(R.id.progressBar_tired);

        pB_hungry.setProgress(tamagochi1.getHungriness());
        pB_happy.setProgress(tamagochi1.getHappiness());
        pB_clean.setProgress(tamagochi1.getCleanliness());
        pB_tired.setProgress(tamagochi1.getStrength());

        bgsleeproom = findViewById(R.id.bgsleeproom);

    }

    public void Room(View view) {
        startActivity(new Intent(SleepRoom.this, MainActivity.class));

    }

    public void Kitchen(View view) {
        Intent intent = new Intent(SleepRoom.this, Kitchen.class);
        startActivity(intent);

    }

    /*public void Pet(View view) {
        tamagochi1.pet();
        pB_happy.setProgress(tamagochi1.getHappiness());

    }*/

    public void Bath(View view) {
        startActivity(new Intent(SleepRoom.this, Bath.class));

    }

    public void Sleep(View view) {
        /*tamagochi1.sleep();
        pB_tired.setProgress(tamagochi1.getStrength());*/
        startActivity(new Intent(SleepRoom.this, SleepRoom.class));
    }


    public void onFury(View view) {
        Toast.makeText(getApplicationContext(), "I come", Toast.LENGTH_SHORT).show();
        tamagochi1.pet();
        pB_happy.setProgress(tamagochi1.getHappiness());
    }

    public void Light(View view) {

        if (!firstPlayed) {

            firstPlayed = true;
            bgsleeproom.setImageResource(R.drawable.sleeproomdark);

        } else {

            firstPlayed = false;
            bgsleeproom.setImageResource(R.drawable.sleeproom);

        }

    }
}
