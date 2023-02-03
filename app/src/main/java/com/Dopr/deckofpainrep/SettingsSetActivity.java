package com.Dopr.deckofpainrep;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deckofpainrep.R;

public class SettingsSetActivity extends AppCompatActivity {

    private EditText et_set;
    private Button btn_set_apply;
    public static Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_set);

        et_set = findViewById(R.id.setNumber);
        btn_set_apply = findViewById(R.id.setApply);
        mToast = Toast.makeText(this, "null", Toast.LENGTH_SHORT);


        //sharedPreferences
        SharedPreferenceUtil sharedPreference = new SharedPreferenceUtil(this);

        et_set.setText(String.valueOf(sharedPreference.getSet()));

        //적용하기
        btn_set_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int set = Integer.parseInt(et_set.getText().toString());
                //값 유효성 확인
                if (set == 0 || set >= 55) {
                    mToast.setText("1 ~ 54 사이를 입력해주세요");
                }
                else {
                    sharedPreference.setSet(set);
                    mToast.setText("적용되었습니다");
                    mToast.show();

                    Intent intent = new Intent(SettingsSetActivity.this, SettingsActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
    //뒤로가기
    public void onBackPressed() {
            Intent intent = new Intent(SettingsSetActivity.this, SettingsActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
    }

}