package com.Dopr.deckofpainrep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deckofpainrep.R;

import org.w3c.dom.Text;

public class CardEndActivity extends AppCompatActivity {

    private int min;
    private int sec;
    private TextView txtTotalTime;
    private ImageButton btnGoHome;
    public static Toast mToast;
    private long backBtnTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_end);

        txtTotalTime = findViewById(R.id.txt_total_time);
        btnGoHome = findViewById(R.id.btn_go_home);

        mToast = Toast.makeText(this, "카드를 모두 뽑았습니다", Toast.LENGTH_SHORT);
        mToast.show();

        Intent intent = getIntent();
        min = intent.getIntExtra("min", 0);
        sec = intent.getIntExtra("sec", 0);
        System.out.println(min);
        System.out.println(sec);
        txtTotalTime.setText("총 수행 시간 : " + min + "분 "+ sec + "초");


        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardEndActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime) {
            Intent intent = new Intent(CardEndActivity.this, MainActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }
        else {
            backBtnTime = curTime;
            mToast.setText("한 번 더 누르면 메인화면으로 나가집니다.");
            mToast.show();
        }
    }

}