package com.example.directory_l5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

//___________________________________________________
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
//_________________________________________________________ ТЕСТОВАЯ ДИЧЬ
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.directory_l5.R.id.nav_tanks;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ListView stankS;
    private String[] array;
    private ArrayAdapter<String> adapter;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private int category_index;
    boolean singleBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//_____________________________________
        AppCenter.start(getApplication(), "164f5196-bffc-4296-a1d0-77ba38a1f9a3",
                Analytics.class, Crashes.class);
 //__________________________________________________ тестовая дичь

        //Скрыть нижную панель навигации
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
        setContentView(R.layout.activity_main);






//Скрыть нижную панель навигации
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//Скрыть нижную панель навигации


        stankS = findViewById(R.id.spisokT);
        array = getResources().getStringArray(R.array.tanksR);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>(Arrays.asList(array)));
        stankS.setAdapter(adapter);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.tanksname);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);



        drawer.openDrawer(GravityCompat.START);// Боковая шторка при первом запуске открытая




        // Добавлена кнопка выхода бокового меню |||
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




        navigationView.setNavigationItemSelectedListener(this);
        stankS.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(MainActivity.this,text_content_activity.class);
                intent.putExtra("category", category_index);
                intent.putExtra("position", position);

                startActivity(intent);
            }
        });


    }



    @Override                          // ВОЗВРАТ ИЗ ИТЕМОВ В ШТОРКУ БЕЗ ЛОГО, Т.Е. ИЗ КОНТЕНТ АКТИВИТИ -ОБРАТНО- СПИСОК ТАНКОВ -ОБРАТНО- ШТОРКА С МЕНЮ
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        // Двойной БЭК для выхода с подсказкой!!!
        //if (singleBack) {
        //            super.onBackPressed();
        //            return;
        //        }
        //
        //        this.singleBack = true;
        //        Toast.makeText(this, "Нажмите кнопку НАЗАД еще раз, чтобы выйти из приложения", Toast.LENGTH_SHORT).show();
        //
        //        new Handler().postDelayed(new Runnable() {
        //
        //            @Override
        //            public void run() {
        //                singleBack=false;
        //            }
        //        }, 2000);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        toolbar.setTitle(R.string.tanksname);
        return true;
    }


// Настройки раздела приложения шрифт,цвет т т.д.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override //ГЛАВНОЕ МЕНЮ РАЗДЕЛЫ ТАНКИ САУ ВИКТОРИНЫ О ПРОГРАММЕ И Т.Д.
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
int id = menuItem.getItemId();
        if (id == nav_tanks)//ТАНКИ
        {
        rasdelRasp(R.string.tanksname,R.array.tanksR,0);
        }

        else if (id == R.id.nav_sau)//САУ
        {
        rasdelRasp(R.string.sauname,R.array.sauR,1);
        }

        else if (id == R.id.nav_btr)//БТР
        {
        rasdelRasp(R.string.btrname,R.array.brtR,2);
        }

        else if (id == R.id.nav_quiz)//ТЕСТ НА ЗНАНИЕ
        {
            Intent a = new Intent(MainActivity.this,Test_activity.class);
            startActivity(a); finish();
            //overridePendingTransition(R.anim.side_in_right, R.anim.side_in_left);
            //rasdelRasp(R.string.nav_quiz,R.array.info,3);
        }

        else if (id == R.id.inf)//ИНФОРМАЦИЯ
        {
            Intent a = new Intent(MainActivity.this, Stars.class);
            startActivity(a); finish();
        }

        else if (id == R.id.free)//ВЕРСИЯ БЕЗ РЕКЛАМЫ
        {
            rasdelRasp(R.string.version_free,R.array.free_version,4);
        }

        else if (id == R.id.info)//ВЫХОД
        {
            //rasdelRasp(R.string.info,R.array.info,4);
            finishAffinity(); // КРАШ ПРИЛОЖЕНИЯ, ВОЗМОЖНО НЕ ВЕРНО!!! КНОПКА ИЗ ПАНЕЛИ (ВЫХОД)
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    //Названия статей вверху титлы
    private void rasdelRasp (int title, int arrayS, int index) {
        toolbar.setTitle(title);
        array = getResources().getStringArray(arrayS);
        adapter.clear();
        adapter.addAll(array);
        adapter.notifyDataSetChanged();
        category_index = index;
    }






}
