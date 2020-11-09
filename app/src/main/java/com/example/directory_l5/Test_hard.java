package com.example.directory_l5;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;
import java.util.Timer;


public class Test_hard extends AppCompatActivity {

    private Button btn_back_testhard, button1,button2,button3,button4,btnnextHardTest,btnback,btnnext,btnnextHardTestWin;
    TextView textScore,textScore1, question,textResultScore,textViewLife;
    ImageView imgTanks;
    private Array mQuestions = new Array();
    private String mAnswer;
    private int mScore = 0;
    private int nScore = 3;
    private int numScore = 1;
    private int mQuestionsLength = mQuestions.imageTanksHard.length;
    Random r;
    Dialog dialogStart, dialogEndHard, dialogEndWin;
    private Timer timer;
    public com.google.android.gms.ads.InterstitialAd InterstitialAd;  //Реклама


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_hard);
        MobileAds.initialize(this, "ca-app-pub-6623352601098354~7993432716");
        InterstitialAd = new InterstitialAd(this);
        InterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        AdRequest adRequest= new AdRequest.Builder().build();
        InterstitialAd.loadAd(adRequest);

        //закрыть рекламу начало
        InterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                try {
                    Intent i = new Intent(Test_hard.this, Test_activity.class);
                    startActivity(i);
                    finish();
                } catch (Exception e){

                }
            }
        });


        //Скрыть нижную панель навигации________________________________
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
        //______________________________________________________________



        r = new Random();
        timer = new Timer();

        btn_back_testhard = findViewById(R.id.btn_back_testhard);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        imgTanks = findViewById(R.id.imageViewTanksHard);
        question = findViewById(R.id.textReqHardScore);
        textScore = findViewById(R.id.textScoreHardTest);
        textScore1 = findViewById(R.id.textScoreHardTest1);
        textViewLife = findViewById(R.id.textViewLife);







        // Создание диалога-подскази-пояснения вначале_______________________________________________________________________________________________
        final Dialog dialogStart = new Dialog (this);
        dialogStart.requestWindowFeature(Window.FEATURE_NO_TITLE);//без титла диалог
        dialogStart.setContentView(R.layout.dialoglayout_start_hard);
        dialogStart.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон
        dialogStart.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogStart.setCancelable(false);//блок закрытия нижним бэком
        btnback = dialogStart.findViewById(R.id.btnback);
        dialogStart.show();
        btnback.setOnClickListener(new View.OnClickListener() {//кнопка закрыть диалог
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Test_hard.this, Test_activity.class);
                startActivity(i);
                finish();
                dialogStart.dismiss();
            }

        });
        btnnext = dialogStart.findViewById(R.id.btnnexthHardEndWin);
        btnnext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogStart.dismiss();
            }
        });

        // ДИАЛОГ В КОНЦЕ
        final Dialog dialogEndHard = new Dialog (this);
        dialogEndHard.requestWindowFeature(Window.FEATURE_NO_TITLE);//без титла диалог
        dialogEndHard.setContentView(R.layout.dialoglayout_end_testhard);
        dialogEndHard.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон
        dialogEndHard.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEndHard.setCancelable(false);
        textResultScore=dialogEndHard.findViewById(R.id.chrono2);
        btnnextHardTest=dialogEndHard.findViewById(R.id.btnnextHardEnd);
        btnnextHardTest.setOnClickListener(new View.OnClickListener() {//кнопка закрыть диалог
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Test_hard.this, Test_activity.class);
                startActivity(i);
                finish();
                dialogEndHard.dismiss();
            }

        });

        // ДИАЛОГ В КОНЦE, КОГДА ПОБЕДИЛ
        final Dialog dialogEndWin = new Dialog (this);
        dialogEndWin.requestWindowFeature(Window.FEATURE_NO_TITLE);//без титла диалог
        dialogEndWin.setContentView(R.layout.dialoglayout_end_win_hardtest);
        dialogEndWin.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон
        dialogEndWin.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEndWin.setCancelable(false);
        btnnextHardTestWin=dialogEndWin.findViewById(R.id.btnnexthHardEndWin);
        btnnextHardTestWin.setOnClickListener(new View.OnClickListener() {//кнопка закрыть диалог
            @Override
            public void onClick(View v) {
                if (InterstitialAd.isLoaded()){
                    InterstitialAd.show();
                } else {
                Intent i = new Intent(Test_hard.this, Test_activity.class);
                startActivity(i);
                finish();
                dialogEndWin.dismiss();}
            }

        });
        // Создание диалога-подскази-пояснения вначале_______________________________________________________________________________________________






        updateQuestions(r.nextInt(mQuestionsLength));



        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    button4.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    if (button1.getText() == mAnswer) {
                        mScore++;
                        textScore.setText("" + mScore);
                        button1.setBackgroundResource(R.drawable.style_buuton_try);
                        sleepThread11();

                    }       else {
                        button1.setBackgroundResource(R.drawable.style_buuton_false);
                        nScore--;
                        textViewLife.setText("" + nScore);
                        sleepThread11();
                        if (nScore == 0) {
                            String tv = textScore.getText().toString();
                            textResultScore.setText(tv);
                            dialogEndHard.show();
                        }
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
//                    numScore++;
//                    question.setText(numScore+"/32");
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    button4.setEnabled(false);
                    if (mScore == 30) {
                        dialogEndWin.show();
                        // ПЕРЕДАЧА СЧЕТА В ТЕСТ АКТИВИТИ
                        SharedPreferences sph = getSharedPreferences("score_hrd", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sph.edit();
                        editor.putString("score_hrd", "УРОВЕНЬ ПРОЙДЕН");
                        editor.apply();
                    } else {sleepThread15();}

                }

                return false;
            }
        });



        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button1.setEnabled(false);
                    button4.setEnabled(false);
                    button3.setEnabled(false);
                    if (button2.getText() == mAnswer) {
                        mScore++;
                        textScore.setText("" + mScore);
                        button2.setBackgroundResource(R.drawable.style_buuton_try);
                        sleepThread12();

                    }       else {
                        button2.setBackgroundResource(R.drawable.style_buuton_false);
                        nScore--;
                        textViewLife.setText("" + nScore);
                        sleepThread12();
                        if (nScore == 0) {
                            String tv = textScore.getText().toString();
                            textResultScore.setText(tv);
                            dialogEndHard.show();
                        }
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
//                    numScore++;
//                    question.setText(numScore+"/32");
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    button4.setEnabled(false);
                    if (mScore == 30) {
                        dialogEndWin.show();
                        // ПЕРЕДАЧА СЧЕТА В ТЕСТ АКТИВИТИ
                        SharedPreferences sph = getSharedPreferences("score_hrd", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sph.edit();
                        editor.putString("score_hrd", "УРОВЕНЬ ПРОЙДЕН");
                        editor.apply();
                    } else {sleepThread15();}

                }

                return false;
            }
        });


        button3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button4.setEnabled(false);
                    if (button3.getText() == mAnswer) {
                        mScore++;
                        textScore.setText("" + mScore);
                        button3.setBackgroundResource(R.drawable.style_buuton_try);
                        sleepThread13();

                    }       else {
                        button3.setBackgroundResource(R.drawable.style_buuton_false);
                        nScore--;
                        textViewLife.setText("" + nScore);
                        sleepThread13();
                        if (nScore == 0) {
                            String tv = textScore.getText().toString();
                            textResultScore.setText(tv);
                            dialogEndHard.show();
                        }
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
//                    numScore++;
//                    question.setText(numScore+"/32");
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    button4.setEnabled(false);
                    if (mScore == 30) {
                        dialogEndWin.show();
                        // ПЕРЕДАЧА СЧЕТА В ТЕСТ АКТИВИТИ
                        SharedPreferences sph = getSharedPreferences("score_hrd", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sph.edit();
                        editor.putString("score_hrd", "УРОВЕНЬ ПРОЙДЕН");
                        editor.apply();
                    } else {sleepThread15();}

                }

                return false;
            }
        });


        button4.setOnTouchListener(new View.OnTouchListener() {
                                       @Override
                                       public boolean onTouch(View v, MotionEvent event) {

                                           if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                               button1.setEnabled(false);
                                               button2.setEnabled(false);
                                               button3.setEnabled(false);
                                               if (button4.getText() == mAnswer) {
                                                   mScore++;
                                                   textScore.setText("" + mScore);
                                                   button4.setBackgroundResource(R.drawable.style_buuton_try);
                                                   sleepThread14();

                                               }       else {
                                                   button4.setBackgroundResource(R.drawable.style_buuton_false);
                                                   nScore--;
                                                   textViewLife.setText("" + nScore);
                                                   sleepThread14();
                                                   if (nScore == 0) {
                                                       String tv = textScore.getText().toString();
                                                       textResultScore.setText(tv);
                                                       dialogEndHard.show();
                                                   }
                                               }
                                           }
                                           else if (event.getAction() == MotionEvent.ACTION_UP){
//                                               numScore++;
//                                               question.setText(numScore+"/32");
                                               button1.setEnabled(false);
                                               button2.setEnabled(false);
                                               button3.setEnabled(false);
                                               button4.setEnabled(false);
                                               if (mScore == 30) {
                                                   dialogEndWin.show();
                                                   // ПЕРЕДАЧА СЧЕТА В ТЕСТ АКТИВИТИ
                                                   SharedPreferences sph = getSharedPreferences("score_hrd", MODE_PRIVATE);
                                                   SharedPreferences.Editor editor = sph.edit();
                                                   editor.putString("score_hrd", "УРОВЕНЬ ПРОЙДЕН");
                                                   editor.apply();
                                               } else {sleepThread15();}

                                           }

                                           return false;
                                       }
                                   });




//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                numScore++;
//                question.setText(numScore+"/32");
//                if (button1.getText()==mAnswer){
//                    mScore++;
//                    textScore.setText(""+mScore);
//                    button1.setBackgroundResource(R.drawable.style_buuton_try);
//                    sleepThread1();
//                    if (mScore == 30) {
//                        dialogEndWin.show();
//                        //диалог ВИН показать
//                        // ПЕРЕДАЧА СЧЕТА ВТЕСТ АКТИВИТИ
//                        SharedPreferences sph = getSharedPreferences("score_hrd", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sph.edit();
//                        editor.putString("score_hrd", "УРОВЕНЬ ПРОЙДЕН");
//                        editor.apply();
//                    }
//                } else {
//                    button1.setBackgroundResource(R.drawable.style_buuton_false);
//                    nScore--;
//                    textViewLife.setText(""+nScore);
//                    sleepThread1();
//                    if (nScore==0){
//                        String tv = textScore.getText().toString();
//                        textResultScore.setText(tv);
//                        dialogEndHard.show();
//                    }else{}
//                }
//            }
//        });
//
//



        // Кнопка для перехода в TestActivity_________________________________________________
        btn_back_testhard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Test_hard.this, Test_activity.class);
                startActivity(i);
                finish();
                //overridePendingTransition(R.anim.side_in_left, R.anim.side_in_right);
            }
        });
        // Кнопка для перехода в TestActivity_________________________________________________

    }

    private void sleepThread11() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                button1.setBackgroundResource(R.drawable.style_buuton_test_menu);
            }
        }, 1200);


    }

    private void sleepThread12() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                button2.setBackgroundResource(R.drawable.style_buuton_test_menu);
            }
        }, 1200);

    }

    private void sleepThread13() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                button3.setBackgroundResource(R.drawable.style_buuton_test_menu);
            }
        }, 1200);

    }

    private void sleepThread14() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                button4.setBackgroundResource(R.drawable.style_buuton_test_menu);
            }
        }, 1200);

    }

    private void sleepThread15() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                numScore++;
                question.setText(numScore+"/32");
                updateQuestions(r.nextInt(mQuestionsLength));
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
                button4.setEnabled(true);

            }
        }, 1200);

    }

    private void updateQuestions (int num) {
        //question.setText(mQuestions.getQuestion(num));
        imgTanks.setImageResource(mQuestions.mImageQuestion(num));
        button1.setText(mQuestions.getChoise1(num));
        button2.setText(mQuestions.getChoise2(num));
        button3.setText(mQuestions.getChoise3(num));
        button4.setText(mQuestions.getChoise4(num));

        mAnswer=mQuestions.getCorrectAnswer(num);
    }



}
