package com.example.directory_l5;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;


public class Test_easy extends AppCompatActivity {

    private Button btn_back_testeasy, btnback, btnnext, btnnext2;
    ImageButton imageButton, imageButton2;
    Dialog dialog, dialogEnd;
    public int count = 0;
    public int tankLeft, tankRight;
    SharedPreferences sp;
    public static final String FINAL_SCORE = "finalScoreEasy";
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    Array array = new Array();
    Random random = new Random();
    TextView textChro;
    final String SAVED_TEXT = "save_e";
    public InterstitialAd InterstitialAd;  //Реклама



    //ХРОНОМЕТР!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void startChronometer() {
        if (!running) {

            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }
    public void pauseChronometer() {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }
    public void resetChronometer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }

    private static String timeToString(long secs) {
        long
                min = (secs / 1000) / 60,
                sec = (secs / 1000) % 60;
        return String.format("%02d:%02d", min, sec);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Скрыть нижную панель навигации
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
        setContentView(R.layout.test_easy);
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
                    Intent i = new Intent(Test_easy.this, Test_activity.class);
                    startActivity(i);
                    finish();
                } catch (Exception e){

                }
            }
        });
        //закрыть рекламу конец

        btn_back_testeasy = findViewById(R.id.btn_back_testhard);
        imageButton = findViewById(R.id.imageButton);
        imageButton2 = findViewById(R.id.imageButton2);

        chronometer = findViewById(R.id.textView21);
        chronometer.setBase(SystemClock.elapsedRealtime());






        final int [] progress ={R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5,R.id.textView6,R.id.textView7,R.id.textView8,R.id.textView9,R.id.textView10,
                                R.id.textView11,R.id.textView12,R.id.textView13,R.id.textView14,R.id.textView15,R.id.textView16,R.id.textView17,R.id.textView18,R.id.textView19,R.id.textView20};




        // Картинка верх  и низ, рандом зависит от количества картинок сейас их 20 (боунд -19).В аррае сначала танки нененмецкие потом немецкие.
        tankLeft = random.nextInt(120);
        imageButton.setImageResource(array.img [tankLeft]);

        if (tankLeft>59)
        {tankRight=random.nextInt(60);}
        else
        {tankRight=(random.nextInt(60))+60;}

        imageButton2.setImageResource(array.img [tankRight]);


        // Кнопка для перехода в TestActivity
        btn_back_testeasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Test_easy.this, Test_activity.class);
                startActivity(i);
                finish();
                //overridePendingTransition(R.anim.side_in_left, R.anim.side_in_right);
            }
        });


        // Создание диалога-подскази-пояснения
        final Dialog dialog = new Dialog (this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//без титла диалог
        dialog.setContentView(R.layout.dialoglayout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон
        //dialog.setCancelable(false);//блок закрытия нижним бэком
        btnback = dialog.findViewById(R.id.btnback);
        dialog.show();

        btnback.setOnClickListener(new View.OnClickListener() {//кнопка закрыть диалог
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Test_easy.this, Test_activity.class);
                startActivity(i);
                finish();
                dialog.dismiss();
            }

        });

        btnnext = dialog.findViewById(R.id.btnnexthHardEndWin);
        btnnext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startChronometer();
                dialog.dismiss();

            }
        });



        // ДИАЛОГ В КОНЦЕ
        final Dialog dialogEnd = new Dialog (this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);//без титла диалог
        dialogEnd.setContentView(R.layout.dialoglayout_end_testeasy);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);
        textChro = dialogEnd.findViewById(R.id.textResultHard);//блок закрытия нижним бэком

        chronometer = findViewById(R.id.textView21);


        btnnext2=dialogEnd.findViewById(R.id.btnnextHardEnd);
        btnnext2.setOnClickListener(new View.OnClickListener() {//кнопка закрыть диалог
            @Override
            public void onClick(View v) {
                if (InterstitialAd.isLoaded()){
                    InterstitialAd.show();
                } else {
                Intent i = new Intent(Test_easy.this, Test_activity.class);
                startActivity(i);
                finish();

                dialogEnd.dismiss();}
            }

        });



        // Обработчик нажатий верхней кнопки
        imageButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imageButton2.setEnabled(false);//блокируем нажатие нижней кнопки, когда нажимается верхняя
                    if (tankLeft > tankRight) {
                        imageButton.setBackgroundResource(R.drawable.style_buuton_try);
                        sleepThread1();
                    } else {
                        imageButton.setBackgroundResource(R.drawable.style_buuton_false);
                        sleepThread1();
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    imageButton.setEnabled(false);
                    imageButton2.setEnabled(false);
                     if (tankLeft>tankRight) {

                         if (count<20){count=count+1;}

                         for (int i=0; i<20;i++) {
                             TextView tv = findViewById(progress[i]);
                             tv.setBackgroundResource(R.color.color_back_logo);}

                         for (int i=0; i<count;i++) {
                             TextView tv = findViewById(progress[i]);
                             tv.setBackgroundResource(R.color.colorGreen);}
                     }

                     else {
                         if (count>0){
                             if (count==1){count=0;}
                          else {count=count-1;}
                         }
                         for (int i=0; i<19;i++) {
                             TextView tv = findViewById(progress[i]);
                             tv.setBackgroundResource(R.color.color_back_logo);}

                         for (int i=0; i<count;i++) {
                             TextView tv = findViewById(progress[i]);
                             tv.setBackgroundResource(R.color.colorGreen);}
                     }
                     if(count==20){
                         chronometer = findViewById(R.id.textView21);
                         textChro = dialogEnd.findViewById(R.id.textResultHard);
                         int elapsed = (int)(SystemClock.elapsedRealtime()-chronometer.getBase());
                         textChro.setText(timeToString(elapsed));

                         // ПЕРЕДАЧА СЧЕТА ВТЕСТ АКТИВИТИ
                         SharedPreferences sp = getSharedPreferences("your_prefs", MODE_PRIVATE);
                         SharedPreferences.Editor editor = sp.edit();
                         editor.putInt("save_easy", elapsed);
                         editor.apply();

                         pauseChronometer();
                         dialogEnd.show();
                     }else{


                         sleepThread3();

                     }
                }

             return true;

            }
        });

        //Обработчик нажатия нижней кнопки
        imageButton2.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imageButton.setEnabled(false);
                    //блокируем нажатие нижней кнопки, когда нажимается верхняя
                    if (tankLeft < tankRight) {
                        imageButton2.setBackgroundResource(R.drawable.style_buuton_try);
                        sleepThread2();
                    } else {
                        imageButton2.setBackgroundResource(R.drawable.style_buuton_false);
                        sleepThread2();
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    imageButton.setEnabled(false);
                    imageButton2.setEnabled(false);
                    if (tankLeft<tankRight) {

                        if (count<20){count=count+1;}

                        for (int i=0; i<20;i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.color.color_back_logo);}

                        for (int i=0; i<count;i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.color.colorGreen);}
                    }

                    else {
                        if (count>0){
                            if (count==1){count=0;}
                            else {count=count-1;}
                        }
                        for (int i=0; i<19;i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.color.color_back_logo);}

                        for (int i=0; i<count;i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.color.colorGreen);}
                    }
                    if(count==20){
                        chronometer = findViewById(R.id.textView21);
                        textChro = dialogEnd.findViewById(R.id.textResultHard);
                        int elapsed = (int)(SystemClock.elapsedRealtime()-chronometer.getBase());
                        textChro.setText(timeToString(elapsed));
                        pauseChronometer();

                        // ПЕРЕДАЧА СЧЕТА ВТЕСТ АКТИВИТИ
                        SharedPreferences sp = getSharedPreferences("your_prefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("save_easy", elapsed);
                        editor.apply();

                        dialogEnd.show();
                    }else{


                        sleepThread3();
                    }
                }

                return true;

            }
        });



    }


    private void sleepThread1() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                imageButton.setBackgroundResource(R.drawable.style_buuton_test_menu);
            }
        }, 800);


    }

    private void sleepThread2() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                imageButton2.setBackgroundResource(R.drawable.style_buuton_test_menu);
            }
        }, 800);

    }

    private void sleepThread3() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                tankLeft = random.nextInt(120);
                imageButton.setImageResource(array.img [tankLeft]);

                if (tankLeft>59)
                {tankRight=random.nextInt(60);}
                else
                {tankRight=(random.nextInt(60))+60;}

                imageButton2.setImageResource(array.img [tankRight]);
                imageButton2.setEnabled(true);
                imageButton.setEnabled(true);

            }
        }, 800);

    }





//    private void sleepThread4() {
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                tankLeft = random.nextInt(21);
//                imageButton.setImageResource(array.img [tankLeft]);
//
//                if (tankLeft>9)
//                {tankRight=random.nextInt(10);}
//                else
//                {tankRight=(random.nextInt(9))+11;}
//
//                imageButton2.setImageResource(array.img [tankRight]);
//                imageButton.setEnabled(true);
//                imageButton2.setEnabled(true);
//
//            }
//        }, 800);
//
//    }


}
