package com.Dopr.deckofpainrep;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

import com.example.deckofpainrep.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class CardActivity extends AppCompatActivity {

    private AdView mAdView;

    private ImageButton btn_card;
    private ImageButton btn_reset;
    private TextView txt_count;
    private TextView txt_totalTime;


    private long backBtnTime = 0; // 뒤로가기

    public static Toast mToast; //토스트 메세지

    Random random = new Random();

    int[] arr_cards = {
            R.drawable.card_diamond_ace,
            R.drawable.card_diamond_two,
            R.drawable.card_diamond_three,
            R.drawable.card_diamond_four,
            R.drawable.card_diamond_five,
            R.drawable.card_diamond_six,
            R.drawable.card_diamond_seven,
            R.drawable.card_diamond_eight,
            R.drawable.card_diamond_nine,
            R.drawable.card_diamond_ten,
            R.drawable.card_diamond_junior,
            R.drawable.card_diamond_queen,
            R.drawable.card_diamond_king,
            R.drawable.card_spade_ace,
            R.drawable.card_spade_two,
            R.drawable.card_spade_three,
            R.drawable.card_spade_four,
            R.drawable.card_spade_five,
            R.drawable.card_spade_six,
            R.drawable.card_spade_seven,
            R.drawable.card_spade_eight,
            R.drawable.card_spade_nine,
            R.drawable.card_spade_ten,
            R.drawable.card_spade_junior,
            R.drawable.card_spade_queen,
            R.drawable.card_spade_king,
            R.drawable.card_heart_ace,
            R.drawable.card_heart_two,
            R.drawable.card_heart_three,
            R.drawable.card_heart_four,
            R.drawable.card_heart_five,
            R.drawable.card_heart_six,
            R.drawable.card_heart_seven,
            R.drawable.card_heart_eight,
            R.drawable.card_heart_nine,
            R.drawable.card_heart_ten,
            R.drawable.card_heart_junior,
            R.drawable.card_heart_queen,
            R.drawable.card_heart_king,
            R.drawable.card_clover_ace,
            R.drawable.card_clover_two,
            R.drawable.card_clover_three,
            R.drawable.card_clover_four,
            R.drawable.card_clover_five,
            R.drawable.card_clover_six,
            R.drawable.card_clover_seven,
            R.drawable.card_clover_eight,
            R.drawable.card_clover_nine,
            R.drawable.card_clover_ten,
            R.drawable.card_clover_junior,
            R.drawable.card_clover_queen,
            R.drawable.card_clover_king,
            R.drawable.card_joker1,
            R.drawable.card_joker2,
    };

    int cardNum = 10; // 카드 총 개수(조커 유무)
    int[] arr_cardUsed = new int[cardNum]; // 카드 사용여부 확인 배열
    int count = 0; // 세트 진행 횟수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        //배너광고 레이아웃 가져오기
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //총 시간 계산
        long startTime = System.currentTimeMillis();

        //토스트 메세지 초기화
        mToast = Toast.makeText(this, "null", Toast.LENGTH_LONG);

        // 버튼, 텍스트 초기화
        btn_card = findViewById(R.id.btn_card);
        btn_reset = findViewById(R.id.btn_reset);
        txt_count = findViewById(R.id.txt_count);
        txt_count.setText(count + " / " + cardNum);
        txt_totalTime = findViewById(R.id.txt_totalTime);


        // 리셋 버튼
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardActivity.this, CardActivity.class);
                startActivity(intent);
            }
        });


        // cardUsed 배열 0으로 초기화
        for(int i =0; i < cardNum; i++) {
            arr_cardUsed[i] = 0;
        }

        //카드 클릭 이벤트
        btn_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count < cardNum) {
                    while(true) {
                        int i = random.nextInt(cardNum);

                        if (arr_cardUsed[i] == 0) {
                            btn_card.setImageResource(arr_cards[i]);
                            arr_cardUsed[i] = 1;
                            count++;

                            // i를 1부터 13으로 변환
                            if(i >= 13 && i <= 25) i -= 12;
                            else if(i <= 38) i -= 25;
                            else if(i <= 51) i -= 38;
                            else i = 10;

                            break;
                        }
                    }

                    txt_count.setText(count + " / " + cardNum);
                }
                // 카드 다 뽑은 후 총 소요시간 표시 & 카드화면 reset버튼 전환
                else {
                    long curTime = System.currentTimeMillis();
                    int time = (int) (curTime - startTime) /1000;
                    int sec = time % 60;
                    int min = time/ 60 % 60;




                    btn_card.setVisibility(View.GONE);


                    btn_card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(CardActivity.this, CardActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

    // 뒤로가기 두번 : 종료
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