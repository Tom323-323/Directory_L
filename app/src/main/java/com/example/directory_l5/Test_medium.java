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

public class Test_medium extends AppCompatActivity {


    private Button btn_back_testeasy,buttonLeft,buttonRight, btnnextMedTestWin,btnbackmeddialog,btnnextdialmed,btnnextMedTest;
    private int Score = 0;
    private int ScoreNum = 1;
    private Array mQuestions = new Array();
    private int mQuestionsLength = mQuestions.imageTanksHard.length;
    TextView textViewScore, textVewNum,textResultScoreMed;
    ImageView imageViewTanks;
    private String mCorrectAnswerMedium;
    Random r;
    private Timer timer;
    public InterstitialAd InterstitialAd;  //Реклама



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_medium);
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
                    Intent i = new Intent(Test_medium.this, Test_activity.class);
                    startActivity(i);
                    finish();
                } catch (Exception e){

                }
            }
        });
        //закрыть рекламу конец

        buttonLeft = findViewById(R.id.buttonLeft);
        buttonRight = findViewById(R.id.buttonRight);
        textViewScore = findViewById(R.id.textViewScoreMed);
        textVewNum = findViewById(R.id.textViewNum);
        imageViewTanks = findViewById(R.id.imageTankMedium);

        r = new Random();
        timer = new Timer();

        btn_back_testeasy = findViewById(R.id.btn_back_testhard);


        //Скрыть нижную панель навигации
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );


        // Кнопка для перехода в TestActivity
        btn_back_testeasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Test_medium.this, Test_activity.class);
                startActivity(i);
                finish();
                //overridePendingTransition(R.anim.side_in_left, R.anim.side_in_right);
            }
        });




        updateQuestions(r.nextInt(mQuestionsLength));



        // Создание диалога-подскази-пояснения вначале_______________________________________________________________________________________________
        final Dialog dialogMedStart = new Dialog (this);
        dialogMedStart.requestWindowFeature(Window.FEATURE_NO_TITLE);//без титла диалог
        dialogMedStart.setContentView(R.layout.dialoglayout_medium_start);
        dialogMedStart.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон
        dialogMedStart.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogMedStart.setCancelable(false);//блок закрытия нижним бэком
        btnbackmeddialog = dialogMedStart.findViewById(R.id.btnback_med);
        dialogMedStart.show();
        btnbackmeddialog.setOnClickListener(new View.OnClickListener() {//кнопка закрыть диалог
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Test_medium.this, Test_activity.class);
                startActivity(i);
                finish();
                dialogMedStart.dismiss();
            }

        });
        btnnextdialmed = dialogMedStart.findViewById(R.id.btnnexth_dialog_med_start);
        btnnextdialmed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogMedStart.dismiss();
            }
        });

        // ДИАЛОГ В КОНЦЕ при пройгрыше
        final Dialog dialogMedEnd = new Dialog (this);
        dialogMedEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);//без титла диалог
        dialogMedEnd.setContentView(R.layout.dialoglayout_medium_end);
        dialogMedEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон
        dialogMedEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogMedEnd.setCancelable(false);
        textResultScoreMed=dialogMedEnd.findViewById(R.id.chronomed2);
        btnnextMedTest=dialogMedEnd.findViewById(R.id.btnnextMedEnd);
        btnnextMedTest.setOnClickListener(new View.OnClickListener() {//кнопка закрыть диалог
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Test_medium.this, Test_activity.class);
                startActivity(i);
                finish();
                dialogMedEnd.dismiss();
            }

        });

        // ДИАЛОГ В КОНЦE, КОГДА ПОБЕДИЛ
        final Dialog dialogMedEndWin = new Dialog (this);
        dialogMedEndWin.requestWindowFeature(Window.FEATURE_NO_TITLE);//без титла диалог
        dialogMedEndWin.setContentView(R.layout.dialoglayout_medium_end_win);
        dialogMedEndWin.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон
        dialogMedEndWin.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogMedEndWin.setCancelable(false);
        btnnextMedTestWin=dialogMedEndWin.findViewById(R.id.btnnexthMedEndWin);
        btnnextMedTestWin.setOnClickListener(new View.OnClickListener() {//кнопка закрыть диалог
            @Override
            public void onClick(View v){
                if (InterstitialAd.isLoaded()){
                    InterstitialAd.show();
                } else {
                Intent i = new Intent(Test_medium.this, Test_activity.class);
                startActivity(i);
                finish();
                dialogMedEndWin.dismiss();}
            }

        });
        // Создание диалога-подскази-пояснения вначале_______________________________________________________________________________________________




        buttonLeft.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonRight.setEnabled(false);//блокируем нажатие нижней кнопки, когда нажимается верхняя
                    if (buttonLeft.getText()==mCorrectAnswerMedium) {
                        Score++;
                        textViewScore.setText("" + Score);
                        buttonLeft.setBackgroundResource(R.drawable.style_buuton_try);
                        sleepThread1();
                    }
                    else {buttonLeft.setBackgroundResource(R.drawable.style_buuton_false);
                    dialogMedEnd.show();}
                }

                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    buttonLeft.setEnabled(false);
                    buttonRight.setEnabled(false);

                    if (Score == 20) {
                        dialogMedEndWin.show();
                        //диалог ВИН показать
                        // ПЕРЕДАЧА СЧЕТА ВТЕСТ АКТИВИТИ
                        SharedPreferences spm = getSharedPreferences("score_med", MODE_PRIVATE);
                        SharedPreferences.Editor editor = spm.edit();
                        editor.putString("score_med", "УРОВЕНЬ ПРОЙДЕН");
                        editor.apply();
                    }

                        else {sleepThread3();}
                }


                return false;

            }
        });


        buttonRight.setOnTouchListener(new View.OnTouchListener(){
                @Override
                public boolean onTouch (View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        buttonLeft.setEnabled(false);//блокируем нажатие нижней кнопки, когда нажимается верхняя
                        if (buttonRight.getText() == mCorrectAnswerMedium) {
                            Score++;
                            textViewScore.setText("" + Score);
                            buttonRight.setBackgroundResource(R.drawable.style_buuton_try);
                            sleepThread2();
                        } else {
                            buttonRight.setBackgroundResource(R.drawable.style_buuton_false);
                            dialogMedEnd.show();
                        }
                    }
                    else if (event.getAction() == MotionEvent.ACTION_UP) {
                        buttonLeft.setEnabled(false);
                        buttonRight.setEnabled(false);
                        if (Score == 20) {
                            dialogMedEndWin.show();
                            //диалог ВИН показать
                            // ПЕРЕДАЧА СЧЕТА ВТЕСТ АКТИВИТИ
                            SharedPreferences spm = getSharedPreferences("score_med", MODE_PRIVATE);
                            SharedPreferences.Editor editor = spm.edit();
                            editor.putString("score_med", "УРОВЕНЬ ПРОЙДЕН");
                            editor.apply();
                        } else {
                            sleepThread3();
                        }
                    }
                        return false;

                }

        });


    }


    private void sleepThread1(){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                buttonLeft.setBackgroundResource(R.drawable.style_buuton_test_menu);

            }
        }, 900);


    }

    private void sleepThread2(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                buttonRight.setBackgroundResource(R.drawable.style_buuton_test_menu);

            }
        }, 900);

    }

    private void sleepThread3() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                ScoreNum++;
                textVewNum.setText(ScoreNum+"/20");
                updateQuestions(r.nextInt(mQuestionsLength));
                buttonRight.setEnabled(true);
                buttonLeft.setEnabled(true);

            }
        }, 900);

    }

//    private void sleepThread4() {
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                updateQuestions(r.nextInt(mQuestionsLength));
//                buttonRight.setEnabled(true);
//                buttonLeft.setEnabled(true);
//
//            }
//        }, 900);
//
//    }





    private void updateQuestions (int num) {

        imageViewTanks.setImageResource(mQuestions.mImageQuestion(num));
        buttonLeft.setText(mQuestions.getChoise1med(num));
        buttonRight.setText(mQuestions.getChoise2med(num));
        mCorrectAnswerMedium=mQuestions.getCorrectAnswerMedium(num);
    }


}
