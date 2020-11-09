package com.example.directory_l5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class Test_activity extends AppCompatActivity {


    private Button button_back, btn_easy, btn_medium, btn_hard;

    TextView textEasy, textMedium,textHard;




    // ПЕРЕВОД ИЗ МИЛИСЕКУНД В 00:24
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
        setContentView(R.layout.test_tanks_activity);


        // ПОТОК ДЛЯ СОХРАНЕНИЯ СЧЕТА В ТЕСТ АКТИВИТИ
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                load();
             }
        }, 0);








        button_back = findViewById(R.id.button_back_from_test_main);
        btn_easy = findViewById(R.id.btn_easy);
        btn_medium = findViewById(R.id.btn_medium);
        btn_hard = findViewById(R.id.btn_hard);
        textEasy = findViewById(R.id.textViewEasyScore);
        textMedium = findViewById(R.id.textViewMediumScore);
        textHard = findViewById(R.id.textViewHardScore);

        // Кнопка для перехода в MainActivity
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Test_activity.this, MainActivity.class);
                startActivity(i);
                finish();
                //overridePendingTransition(R.anim.side_in_left, R.anim.side_in_right);
            }
        });



        //Кнопка выбора легкого уровня
        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(Test_activity.this, Test_easy.class);
                startActivity(b);
                finish();
            }
        });


        //Кнопка выбора среднего уровня
        btn_medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(Test_activity.this, Test_medium.class);
                startActivity(c);
                finish();
            }
        });


        //Кнопка выбора сложного уровня
        btn_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = new Intent(Test_activity.this, Test_hard.class);
                startActivity(d);
                finish();
            }
        });

    }
    //МЕТОД СОХРАНЕНИЯ  СЧЕТА
    @SuppressLint("ResourceAsColor")
    private void load (){
        //чтение и сохранение нового score easy
        SharedPreferences sp = getSharedPreferences("your_prefs", MODE_PRIVATE);
        int elapsed = sp.getInt("save_easy", 0);
        textEasy.setText("ПОСЛЕДНИЙ РЕЗУЛЬТАТ: "+timeToString(elapsed));

        if (elapsed!=0){
            textEasy.setTextColor(getResources().getColor(R.color.colorGreen));
        }

        //чтение и сохранение нового score medium
        SharedPreferences spm = getSharedPreferences("score_med", MODE_PRIVATE);
        String st = spm.getString("score_med","УРОВЕНЬ НЕ ПРОЙДЕН");
        textMedium.setText(st);
        if (st.equals("УРОВЕНЬ ПРОЙДЕН")){
            textMedium.setTextColor(getResources().getColor(R.color.colorGreen));

        }

        //чтение и сохранение нового score hard
        SharedPreferences sph = getSharedPreferences("score_hrd", MODE_PRIVATE);
        String sth = sph.getString("score_hrd","УРОВЕНЬ НЕ ПРОЙДЕН");
        textHard.setText(sth);
        if (sth.equals("УРОВЕНЬ ПРОЙДЕН")){
            textHard.setTextColor(getResources().getColor(R.color.colorGreen));
        }




    }



}