package com.Dopr.deckofpainrep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deckofpainrep.R;

public class SettingsCountActivity extends AppCompatActivity {

    private Button btnCountApply;
    private EditText etACard;
    private EditText etJCard;
    private EditText etQCard;
    private EditText etKCard;
    private EditText etJokerCard;

    public static Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_count);

        btnCountApply = findViewById(R.id.btn_count_apply);
        etACard = findViewById(R.id.et_a_card);
        etJCard = findViewById(R.id.et_j_card);
        etQCard = findViewById(R.id.et_q_card);
        etKCard = findViewById(R.id.et_k_card);
        etJokerCard = findViewById(R.id.et_joker_card);

        mToast = Toast.makeText(this, "null", Toast.LENGTH_SHORT);




        //sharedPreferences
        SharedPreferenceUtil sharedPreference = new SharedPreferenceUtil(this);


        // et 초기화
        etACard.setText(String.valueOf(sharedPreference.getACardCount()));
        etJCard.setText(String.valueOf(sharedPreference.getJCardCount()));
        etQCard.setText(String.valueOf(sharedPreference.getQCardCount()));
        etKCard.setText(String.valueOf(sharedPreference.getKCardCount()));
        etJokerCard.setText(String.valueOf(sharedPreference.getJokerCardCount()));

        //적용하기
        btnCountApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int aCardCount = Integer.parseInt(etACard.getText().toString());
                int jCardCount = Integer.parseInt(etJCard.getText().toString());
                int qCardCount = Integer.parseInt(etQCard.getText().toString());
                int kCardCount = Integer.parseInt(etKCard.getText().toString());
                int jokerCardCount = Integer.parseInt(etJokerCard.getText().toString());

                sharedPreference.setACardCount(aCardCount);
                sharedPreference.setJCardCount(jCardCount);
                sharedPreference.setQCardCount(qCardCount);
                sharedPreference.setKCardCount(kCardCount);
                sharedPreference.setJokerCardCount(jokerCardCount);

                mToast.setText("적용되었습니다");
                mToast.show();

                Intent intent = new Intent(SettingsCountActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
    // 뒤로가기
    public void onBackPressed() {
        Intent intent = new Intent(SettingsCountActivity.this, SettingsActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
}