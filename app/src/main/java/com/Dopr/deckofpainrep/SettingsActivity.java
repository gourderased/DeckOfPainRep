package com.Dopr.deckofpainrep;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import com.example.deckofpainrep.R;

public class SettingsActivity extends AppCompatActivity {

    private ImageButton btn_go_set;
    private ImageButton btn_go_card_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btn_go_set = findViewById(R.id.btn_go_set);
        btn_go_card_num = findViewById(R.id.btn_go_card_num);

        btn_go_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, SettingsSetActivity.class);
                startActivity(intent);
            }
        });

        btn_go_card_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, SettingsCountActivity.class);
                startActivity(intent);
            }
        });
    }



    public void onBackPressed() {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);


    }
}

