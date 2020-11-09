package com.example.directory_l5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


public class Logo_Activity extends Activity {
    private Animation logoAnim,buttonLogoAnim ;
    private Button bAnim;
   // private ImageView logoImage;
    private ImageView logoImage;
    boolean singleBack = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Скрыть нижную панель навигации
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);



//Скрыть нижную панель навигации
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//Скрыть нижную панель навигации

        setContentView(R.layout.logo_1);
        //init();
        startLogoTime();
    }


// Анимация лого и кнопки старта
    private void init()
    {   logoAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.l_anim);
        buttonLogoAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_anim);
        //logoImage = findViewById(R.id.imageView2);
        logoImage.startAnimation(logoAnim);
    }



    @Override                      // ВОЗВРАТ ИЗ ИТЕМОВ В ШТОРКУ БЕЗ ЛОГО, Т.Е. ИЗ КОНТЕНТ АКТИВИТИ -ОБРАТНО- СПИСОК ТАНКОВ -ОБРАТНО- ШТОРКА С МЕНЮ (ИЗНАЧАЛЬНО ВЗЯТО ИЗ ГЛАВНОЙ АКТИВИТИ)
    public void onBackPressed() {


        // Двойной БЭК для выхода с подсказкой!!!
        if (singleBack) {
                    super.onBackPressed();
                    return;
               }

               this.singleBack = true;
                Toast.makeText(this, "Нажмите кнопку НАЗАД еще раз, чтобы выйти из приложения", Toast.LENGTH_SHORT).show();

               new Handler().postDelayed(new Runnable() {

                   @Override
                   public void run() {
                       singleBack=false;
                   }
               }, 2000);
    }





// Кнопка для перехода в MainActivity
    //public void buttn_start_main(View view) {
    //    Intent i = new Intent(Logo_Activity.this,MainActivity.class);
    //    startActivity(i);
   // }

 // КНОПКА ВЫХОДА ИЗ ПРИЛОЖЕНИЯ ИЗ ЛОГО
   // public void buttn_exit_main(View view) {
    // finishAffinity(); // КРАШ ПРИЛОЖЕНИЯ, ВОЗМОЖНО НЕ ВЕРНО!!! КНОПКА ИЗ ПАНЕЛИ (ВЫХОД)
   // }





    //Функция автоматических переход от лого
    private void startLogoTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {Thread.sleep(3000);} catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(Logo_Activity.this,MainActivity.class);
                startActivity(i);
            }
        }).start();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
