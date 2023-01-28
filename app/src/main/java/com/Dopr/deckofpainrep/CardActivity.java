package com.Dopr.deckofpainrep;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

import com.example.deckofpainrep.R;
import com.google.android.gms.ads.AdRequest;
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
    private TextView txt_end;
    private TextView txt_info;

    private int flag = 0;

    private long backBtnTime = 0; // 뒤로가기

    public static Toast mToast; //토스트 메세지


    Random random = new Random();

    int[] arr_cards = {
            //diamond    i 0 ~ 12
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
            R.drawable.card_diamond_ace,
            //heart     i 13 ~ 25
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
            R.drawable.card_heart_ace,
            //spade     i 26 ~ 38
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
            R.drawable.card_spade_ace,

            //clover    i 39 ~ 51
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
            R.drawable.card_clover_ace,

            //joker     i 51 ~ 53
            R.drawable.card_joker1,
            R.drawable.card_joker2,
    };

    int totalCardNum = 54;
    int[] arr_cardUsed = new int[totalCardNum]; // 카드 사용여부 확인 배열
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

        //sharedpreference 초기화
        SharedPreferenceUtil sharedPreference = new SharedPreferenceUtil(this);
        int setNum = sharedPreference.getSet();





        //총 시간 계산
        long startTime = System.currentTimeMillis();

        //토스트 메세지 초기화
        mToast = Toast.makeText(this, "null", Toast.LENGTH_SHORT);

        // 버튼, 텍스트 초기화
        btn_card = findViewById(R.id.btn_card);
        btn_reset = findViewById(R.id.btn_reset);
        txt_count = findViewById(R.id.txt_count);
        txt_count.setText(count + " / " + setNum);
        txt_totalTime = findViewById(R.id.txt_totalTime);
        txt_end = findViewById(R.id.txt_end);
        txt_info = findViewById(R.id.txt_info);





       // 리셋 버튼
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardActivity.this, CardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        // cardUsed 배열 0으로 초기화
        for(int i =0; i < setNum; i++) {
            arr_cardUsed[i] = 0;
        }

        //카드 클릭 이벤트
        btn_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(count < setNum) {
                    //카드 뽑힌 후 문양과 수 확인 변수 초기화
                    int patternNum;
                    int alphaNum;
                    String pattern;
                    int executeNum;

                    //안뽑힌 카드가 나올 때 까지 반복
                    while(true) {
                        int i = random.nextInt(totalCardNum);
                        if (arr_cardUsed[i] == 0) {
                            btn_card.setImageResource(arr_cards[i]);
                            arr_cardUsed[i] = 1;
                            count++;

                            // i 가 수인지 a,j,k,q,조커 인지 확인
                            alphaNum = distinguishAlpha(i);

//                            if(alphaNum == 14) { //조커일 경우
//                                executeNum = jokerCard;
//                            }
//                            else if(alphaNum == 13) {
//                                executeNum = jCard;
//                            }
//                            else if(alphaNum == 12) {
//                                executeNum = qCard;
//                            }
//                            else if(alphaNum == 11) {
//                                executeNum = kCard;
//                            }
//                            else {
//                                executeNum = i + 1;
//                            }
//                            // i로 부터 문양 구분
//                            patternNum = distinguishPattern(i);
//
//                            if(patternNum == 1) {
//                                pattern = diamondCard;
//                            }
//                            else if(patternNum == 2) {
//                                pattern = heartCard;
//                            }
//                            else if (patternNum == 3) {
//                                pattern = spadeCard;
//                            }
//                            else if(patternNum == 4) {
//                                pattern = cloverCard;
//                            }
//                            else {
//                                pattern = "";
//                            }

                            break;
                        }
                    }
                    //왼쪽 상단
                    txt_count.setText(count + " / " + setNum);
                    //카드 하단 운동 종류와 횟수 표시

                    txt_info.setText(" " +   "회");
                    
                }
                // 카드 다 뽑은 후 총 소요시간 표시 & 카드화면 reset버튼 전환
                else {
                    long curTime = System.currentTimeMillis();
                    int time = (int) (curTime - startTime) /1000;
                    int sec = time % 60;
                    int min = time/ 60 % 60;


                    btn_card.setImageResource(R.drawable.card_end);

                    txt_end.setText("운동 끝!");
                    if(flag == 0) {
                        txt_totalTime.setText("총 수행 시간 : " + min + "분 "+ sec + "초");
                        flag = 1;
                    }
                    btn_reset.setImageResource(R.drawable.card_btn_home);

                    btn_reset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(CardActivity.this, MainActivity.class);

                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

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
            Intent intent = new Intent(CardActivity.this, MainActivity.class);

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

    public int distinguishAlpha(int i) {
        //조커
        if(i == 52 || i == 53) {
            return 14;
        }
        else {
            i = i % 13;
            if (i == 9) return 9; // j
            else if(i==10) return 10; // q
            else if(i == 11) return 11; // k
            else if (i == 12) return 12; // a
            else return i;
        }
    }

    public int distinguishPattern(int i) {
        if(i >= 39 && i <=51) {
            return 4;
        }
        else if(i >=26 && i <= 38) {
            return 3;
        }
        else if(i >=13 && i <= 25) {
            return 2;
        }
        else if(i <= 12) {
            return 1;
        }
        else { // joker
            return 0;
        }
    }


}