package com.example.fury;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ImageButton btn_feed, btn_pet, btn_clean, btn_walk, btn_sleep;
    tamagochi tamagochi1 = new tamagochi(0, 5, 0, 10);
    ProgressBar pB_hungry, pB_happy, pB_clean, pB_tired;
    TextView time, text_hungry, text_happy, text_clean, text_tired, text_time;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
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

        /*//Проверка целостности БД
        SQLiteDatabase sdb;
        sdb = new Database(this).getReadableDatabase();
        Cursor cursor = sdb.query("Words", new String[]{Database.WORD_COLUMN,
                Database.LETTER_COLUMN, Database.PIC_COLUMN}, null, null, null, null, null);
        try {
            version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException ignored) {
        }*/

        /*//Проверка актуальной версии. Если не равно - значит было установлено обновление и следует запустить предзагрузку
        if (cursor.getCount() < 1 || !version.equals(prefs.getString("version", "0"))) {
            prefs.edit().putString("version", version).apply();
            finish();
            Intent i = new Intent(this, Preloading.class);
            startActivity(i);
            cursor.close();
        } else {
            cursor.close();
            sdb.close();*/

        setContentView(R.layout.activity_main);

        //Открытие фона из постоянного кэша
        getWindow().setBackgroundDrawable(new BitmapDrawable(getResources(), new ImageHelper(this).openFile("room.png")));

        btn_feed = findViewById(R.id.btn_feed);
        btn_pet = findViewById(R.id.btn_pet);
        btn_clean = findViewById(R.id.btn_clean);
        btn_walk = findViewById(R.id.btn_walk);
        btn_sleep = findViewById(R.id.btn_sleep);

        /*((ImageButton) findViewById(R.id.btn_feed)).setImageBitmap(new ImageHelper(this).openFile("feed.png"));
        ((ImageButton) findViewById(R.id.btn_pet)).setImageBitmap(new ImageHelper(this).openFile("pet.png"));
        ((ImageButton) findViewById(R.id.btn_clean)).setImageBitmap(new ImageHelper(this).openFile("clean.png"));
        ((ImageButton) findViewById(R.id.btn_walk)).setImageBitmap(new ImageHelper(this).openFile("walk.png"));
        ((ImageButton) findViewById(R.id.btn_sleep)).setImageBitmap(new ImageHelper(this).openFile("sleep.png"));
        //btn_feed.setImageResource(R.mipmap.feed2);*/

        pB_hungry = findViewById(R.id.progressBar_hungry);
        pB_happy = findViewById(R.id.progressBar_happy);
        pB_clean = findViewById(R.id.progressBar_clean);
        pB_tired = findViewById(R.id.progressBar_tired);

        time = findViewById(R.id.time);
        text_hungry = findViewById(R.id.text_hungry);
        text_happy = findViewById(R.id.text_happy);
        text_clean = findViewById(R.id.text_clean);
        text_tired = findViewById(R.id.text_tired);


        btn_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tamagochi1.feed();
                pB_hungry.setProgress(tamagochi1.getHungriness());
            }
        });

        btn_pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tamagochi1.pet();
                //аналогично pB_happy.setProgress(tamagochi1.getHappiness());

            }
        });

        btn_clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tamagochi1.clean();
            }
        });

        btn_walk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tamagochi1.walk();
            }
        });

        btn_sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tamagochi1.sleep();
            }
        });

    }


    public void Feed(View view) {
        tamagochi1.feed();
        pB_happy.setProgress(tamagochi1.getHappiness());
    }
}