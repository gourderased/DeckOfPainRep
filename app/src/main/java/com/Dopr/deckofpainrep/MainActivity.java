package com.Dopr.deckofpainrep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.deckofpainrep.R;

public class MainActivity extends AppCompatActivity {


    private ImageButton btn_settings;

    private Button btn_how;
    private Button btn_start;

    private long backBtnTime = 0;

    public static Toast mToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //토스트 초기화
        mToast = Toast.makeText(this, "null", Toast.LENGTH_LONG);



        // 설정
        btn_settings = findViewById(R.id.btn_settings);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        //운동 시작
        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CardActivity.class);
                startActivity(intent);
            }
        });

        //운동 방법
        btn_how = findViewById(R.id.btn_how);
        btn_how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HowActivity.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime) {
            finishAffinity();
        }
        else {
            backBtnTime = curTime;
            mToast.setText("한 번 더 누르면 종료됩니다.");
            mToast.show();
        }
    }
}