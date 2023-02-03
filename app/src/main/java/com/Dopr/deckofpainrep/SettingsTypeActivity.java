package com.Dopr.deckofpainrep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deckofpainrep.R;

public class SettingsTypeActivity extends AppCompatActivity {

    private Button btnTypeApply;
    private EditText etHeartType;
    private EditText etDiamondType;
    private EditText etCloverType;
    private EditText etSpadeType;

    public static Toast mToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_type);

        mToast = Toast.makeText(this, "null", Toast.LENGTH_SHORT);

        btnTypeApply = findViewById(R.id.btn_type_apply);
        etHeartType = findViewById(R.id.et_type_heart);
        etDiamondType = findViewById(R.id.et_type_diamond);
        etCloverType = findViewById(R.id.et_type_clover);
        etSpadeType = findViewById(R.id.et_type_spade);

        SharedPreferenceUtil sharedPreference = new SharedPreferenceUtil(this);

        etHeartType.setText(sharedPreference.getHeartType());
        etDiamondType.setText(sharedPreference.getDiamondType());
        etCloverType.setText(sharedPreference.getCloverType());
        etSpadeType.setText(sharedPreference.getSpadeType());

        btnTypeApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heartType = etHeartType.getText().toString();
                String diamondType = etDiamondType.getText().toString();
                String cloverType = etCloverType.getText().toString();
                String spadeType = etSpadeType.getText().toString();

                sharedPreference.setHeartType(heartType);
                sharedPreference.setDiamondType(diamondType);
                sharedPreference.setCloverType(cloverType);
                sharedPreference.setSpadeType(spadeType);

                mToast.setText("적용되었습니다");
                mToast.show();

                Intent intent = new Intent(SettingsTypeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
    // 뒤로가기
    public void onBackPressed() {
        Intent intent = new Intent(SettingsTypeActivity.this, SettingsActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
}