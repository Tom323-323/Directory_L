package com.example.directory_l5;

import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class text_content_activity extends AppCompatActivity {

    private AdView adView;
    private ActionBar actionBar;
    private TextView text_content,text_content2,text_content3,text_content4;
    private SharedPreferences def_pref;
    private ImageView  i_content,i_content2,i_content3,i_content4,i_content5;
    //public Typeface face1,face2,face3,face4;
    private int category = 0;
    private int position = 0;

    private int[] tanks_array = {R.string.tank_1, R.string.tank_2, R.string.tank_3, R.string.tank_4,R.string.tank_5,R.string.tank_6,R.string.tank_7,R.string.tank_8,R.string.tank_9,R.string.tank_10,R.string.tank_11,R.string.tank_12,};
    private int[] tanks_array2 = {R.string.tank1_2,R.string.tank2_2,R.string.tank3_2,R.string.tank4_2,R.string.tank5_2,R.string.tank6_2,R.string.tank7_2,R.string.tank8_2,R.string.tank9_2,R.string.tank10_2,R.string.tank11_2,R.string.tank12_2,};
    private int[] tanks_array3 = {R.string.tank1_3,R.string.tank2_3,R.string.tank3_3,R.string.tank4_3,R.string.tank5_3,R.string.tank6_3,R.string.tank7_3,R.string.tank8_3,R.string.tank9_3,R.string.tank10_3,R.string.tank11_3,R.string.tank12_3,};
    private int[] tanks_array4 = {R.string.tank1_4,R.string.tank2_4,R.string.tank3_4,R.string.tank4_4,R.string.tank5_4,R.string.tank6_4,R.string.tank7_4,R.string.tank8_4,R.string.tank9_4,R.string.tank10_4,R.string.tank11_4,R.string.tank12_4,};

    private int[] sau_array = {R.string.sau_1, R.string.sau_2, R.string.sau_3, R.string.sau_4,R.string.sau_5,R.string.sau_6,R.string.sau_7,R.string.sau_8,R.string.sau_9,R.string.sau_10,R.string.sau_11, R.string.sau_12, R.string.sau_13, R.string.sau_14,R.string.sau_15,R.string.sau_16,R.string.sau_17,R.string.sau_18,R.string.sau_19,R.string.sau_20,R.string.sau_21,R.string.sau_22,};
    private int[] sau_array2 = {R.string.sau1_2,R.string.sau2_2,R.string.sau3_2,R.string.sau4_2,R.string.sau5_2,R.string.sau6_2,R.string.sau7_2,R.string.sau8_2,R.string.sau9_2,R.string.sau10_2,R.string.sau11_2,R.string.sau12_2,R.string.sau13_2,R.string.sau14_2,R.string.sau15_2,R.string.sau16_2,R.string.sau17_2,R.string.sau18_2,R.string.sau19_2,R.string.sau20_2,R.string.sau21_2,R.string.sau22_2,};
    private int[] sau_array3 = {R.string.sau1_3,R.string.sau2_3,R.string.sau3_3,R.string.sau4_3,R.string.sau5_3,R.string.sau6_3,R.string.sau7_3,R.string.sau8_3,R.string.sau9_3,R.string.sau10_3,R.string.sau11_3,R.string.sau12_3,R.string.sau13_3,R.string.sau14_3,R.string.sau15_3,R.string.sau16_3,R.string.sau17_3,R.string.sau18_3,R.string.sau19_3,R.string.sau20_3,R.string.sau21_3,R.string.sau22_3,};
    private int[] sau_array4 = {R.string.sau1_4,R.string.sau2_4,R.string.sau3_4,R.string.sau4_4,R.string.sau5_4,R.string.sau6_4,R.string.sau7_4,R.string.sau8_4,R.string.sau9_4,R.string.sau10_4,R.string.sau11_4,R.string.sau12_4,R.string.sau13_4,R.string.sau14_4,R.string.sau15_4,R.string.sau16_4,R.string.sau17_4,R.string.sau18_4,R.string.sau19_4,R.string.sau20_4,R.string.sau21_4,R.string.sau22_4,};

    private int[] btr_array = {R.string.btr_1, R.string.btr_2, R.string.btr_3, R.string.btr_4,R.string.btr_5,R.string.btr_6,R.string.btr_7,R.string.btr_8,R.string.btr_9,R.string.btr_10,R.string.btr_11,R.string.btr_12,};
    private int[] btr_array2 = {R.string.btr1_2,R.string.btr2_2,R.string.btr3_2,R.string.btr4_2,R.string.btr5_2,R.string.btr6_2,R.string.btr7_2,R.string.btr8_2,R.string.btr9_2,R.string.btr10_2,R.string.btr11_2,R.string.btr12_2,};
    private int[] btr_array3 = {R.string.btr1_3,R.string.btr2_3,R.string.btr3_3,R.string.btr4_3,R.string.btr5_3,R.string.btr6_3,R.string.btr7_3,R.string.btr8_3,R.string.btr9_3,R.string.btr10_3,R.string.btr11_3,R.string.btr12_3,};
    private int[] btr_array4 = {R.string.btr1_4,R.string.btr2_4,R.string.btr3_4,R.string.btr4_4,R.string.btr5_4,R.string.btr6_4,R.string.btr7_4,R.string.btr8_4,R.string.btr9_4,R.string.btr10_4,R.string.btr11_4,R.string.btr12_4,};

    private int[] info_array = {R.string.info_1, R.string.info_2,R.string.info_3,};
    private int[] version_free_array = {R.string.free_1, R.string.free_2,};
    // НЕОБХОДИО СОЗДАТЬ АРРАИ ДЛЯ ОСТАЛЬНЫХ РАЗДЕЛОВ 4 И 5!!!!!!

    //IMG ARRAY для ТАНКОВ
    private int[] tanks_array_img = {R.drawable.t_pz1_1, R.drawable.t_pz2_1, R.drawable.t_pz2l_1,R.drawable.t_nbfz_1,R.drawable.t_pz35_1,R.drawable.t_pz38_1,
            R.drawable.t_pz3_1,R.drawable.t_pz4_1,R.drawable.t_pz5_1,R.drawable.t_pz6_1,R.drawable.t_pz62_1,R.drawable.t_maus_1,};

    private int[] tanks_array_img2 = {R.drawable.t_pz1_2, R.drawable.t_pz2_2, R.drawable.t_pz2l_2,R.drawable.t_nbfz_2,R.drawable.t_pz35_2,R.drawable.t_pz38_2,
            R.drawable.t_pz3_2,R.drawable.t_pz4_2,R.drawable.t_pz5_2,R.drawable.t_pz6_2,R.drawable.t_pz62_2,R.drawable.t_maus_2,};

    private int[] tanks_array_img3 = {R.drawable.t_pz1_3, R.drawable.t_pz2_3, R.drawable.t_pz2l_3,R.drawable.t_nbfz_3,R.drawable.t_pz35_3,R.drawable.t_pz38_3,
            R.drawable.t_pz3_3,R.drawable.t_pz4_3,R.drawable.t_pz5_3,R.drawable.t_pz6_3,R.drawable.t_pz62_3,R.drawable.t_maus_3,};

    private int[] tanks_array_img4 = {R.drawable.t_pz1_4, R.drawable.t_pz2_4, R.drawable.t_pz2l_4,R.drawable.t_nbfz_4,R.drawable.t_pz35_4,R.drawable.t_pz38_4,
            R.drawable.t_pz3_4,R.drawable.t_pz4_4,R.drawable.t_pz5_4,R.drawable.t_pz6_4,R.drawable.t_pz62_4,R.drawable.t_maus_4,};

    private int[] tanks_array_img5 = {R.drawable.t_pz1_5, R.drawable.t_pz2_5, R.drawable.t_pz2l_5,R.drawable.t_nbfz_5,R.drawable.t_pz35_5,R.drawable.t_pz38_5,
            R.drawable.t_pz3_5,R.drawable.t_pz4_5,R.drawable.t_pz5_5,R.drawable.t_pz6_5,R.drawable.t_pz62_5,R.drawable.t_maus_5,};






    private int[] sau_array_img = {R.drawable.s_bis_1,R.drawable.s_pzjg1_1,R.drawable.s_hez_1,R.drawable.s_stpz_1,R.drawable.s_grill_1,R.drawable.s_mar2_1,
            R.drawable.s_mar3_1,R.drawable.s_wesp_1,R.drawable.s_stug3_1,R.drawable.s_stug4_1,R.drawable.s_stug42_1,R.drawable.s_brumb_1,R.drawable.s_nash_1,R.drawable.s_humm_1,
            R.drawable.s_jgpz4_1,R.drawable.s_dickm_1,R.drawable.s_jgdpan_1,R.drawable.s_sturem_1,R.drawable.s_ferd_1,R.drawable.s_sturtig_1,R.drawable.s_jgdttig_1,R.drawable.s_karl_1,};

    private int[] sau_array_img2 = {R.drawable.s_bis_2,R.drawable.s_pzjg1_2,R.drawable.s_hez_2,R.drawable.s_stpz_2,R.drawable.s_grill_2,R.drawable.s_mar2_2,
            R.drawable.s_mar3_2,R.drawable.s_wesp_2,R.drawable.s_stug3_2,R.drawable.s_stug4_2,R.drawable.s_stug42_2,R.drawable.s_brumb_2,R.drawable.s_nash_2,R.drawable.s_humm_2,
            R.drawable.s_jgpz4_2,R.drawable.s_dickm_2,R.drawable.s_jgdpan_2,R.drawable.s_sturem_2,R.drawable.s_ferd_2,R.drawable.s_sturtig_2,R.drawable.s_jgdttig_2,R.drawable.s_karl_2,};

    private int[] sau_array_img3 = {R.drawable.s_bis_3,R.drawable.s_pzjg1_3,R.drawable.s_hez_3,R.drawable.s_stpz_3,R.drawable.s_grill_3,R.drawable.s_mar2_3,
            R.drawable.s_mar3_3,R.drawable.s_wesp_3,R.drawable.s_stug3_3,R.drawable.s_stug4_3,R.drawable.s_stug42_3,R.drawable.s_brumb_3,R.drawable.s_nash_3,R.drawable.s_humm_3,
            R.drawable.s_jgpz4_3,R.drawable.s_dickm_3,R.drawable.s_jgdpan_3,R.drawable.s_sturem_3,R.drawable.s_ferd_3,R.drawable.s_sturtig_3,R.drawable.s_jgdttig_3,R.drawable.s_karl_3,};

    private int[] sau_array_img4 = {R.drawable.s_bis_4,R.drawable.s_pzjg1_4,R.drawable.s_hez_4,R.drawable.s_stpz_4,R.drawable.s_grill_4,R.drawable.s_mar2_4,
            R.drawable.s_mar3_4,R.drawable.s_wesp_4,R.drawable.s_stug3_4,R.drawable.s_stug4_4,R.drawable.s_stug42_4,R.drawable.s_brumb_4,R.drawable.s_nash_4,R.drawable.s_humm_4,
            R.drawable.s_jgpz4_4,R.drawable.s_dickm_4,R.drawable.s_jgdpan_4,R.drawable.s_sturem_4,R.drawable.s_ferd_4,R.drawable.s_sturtig_4,R.drawable.s_jgdttig_4,R.drawable.s_karl_4,};

    private int[] sau_array_img5 = {R.drawable.s_bis_5,R.drawable.s_pzjg1_5,R.drawable.s_hez_5,R.drawable.s_stpz_5,R.drawable.s_grill_5,R.drawable.s_mar2_5,
            R.drawable.s_mar3_5,R.drawable.s_wesp_5,R.drawable.s_stug3_5,R.drawable.s_stug4_5,R.drawable.s_stug42_5,R.drawable.s_brumb_5,R.drawable.s_nash_5,R.drawable.s_humm_5,
            R.drawable.s_jgpz4_5,R.drawable.s_dickm_5,R.drawable.s_jgdpan_5,R.drawable.s_sturem_5,R.drawable.s_ferd_5,R.drawable.s_sturtig_5,R.drawable.s_jgdttig_5,R.drawable.s_karl_5,};






    private int[] btr_array_img = {R.drawable.b_13_1, R.drawable.b_adgz_1, R.drawable.b_221_1,R.drawable.b_222_1,R.drawable.b_231_1,R.drawable.b_panar_1,
            R.drawable.b_234_1,R.drawable.b_247_1,R.drawable.b_250_1,R.drawable.b_251_1,R.drawable.b_254_1,R.drawable.b_p42_1,};
    private int[] btr_array_img2 = {R.drawable.b_13_2, R.drawable.b_adgz_2, R.drawable.b_221_2,R.drawable.b_222_2,R.drawable.b_231_1,R.drawable.b_panar_2,
            R.drawable.b_234_2,R.drawable.b_247_2,R.drawable.b_250_2,R.drawable.b_251_2,R.drawable.b_254_2,R.drawable.b_p42_2,};
    private int[] btr_array_img3 = {R.drawable.b_13_3, R.drawable.b_adgz_3, R.drawable.b_221_3,R.drawable.b_222_3,R.drawable.b_231_1,R.drawable.b_panar_3,
            R.drawable.b_234_3,R.drawable.b_247_3,R.drawable.b_250_3,R.drawable.b_251_3,R.drawable.b_254_3,R.drawable.b_p42_3,};
    private int[] btr_array_img4 = {R.drawable.b_13_4, R.drawable.b_adgz_4, R.drawable.b_221_4,R.drawable.b_222_4,R.drawable.b_231_1,R.drawable.b_panar_4,
            R.drawable.b_234_4,R.drawable.b_247_4,R.drawable.b_250_4,R.drawable.b_251_4,R.drawable.b_254_4,R.drawable.b_p42_4,};
    private int[] btr_array_img5 = {R.drawable.b_13_5, R.drawable.b_adgz_5, R.drawable.b_221_5,R.drawable.b_222_5,R.drawable.b_231_1,R.drawable.b_panar_5,
            R.drawable.b_234_5,R.drawable.b_247_5,R.drawable.b_250_5,R.drawable.b_251_5,R.drawable.b_254_5,R.drawable.b_p42_5,};



    private int[] info_array_img = {R.drawable.b_250_5, R.drawable.b_250_5, R.drawable.b_250_5,R.drawable.b_250_5};
    private int[] free_array_img = {R.drawable.b_250_5, R.drawable.b_250_5, R.drawable.b_250_5,R.drawable.b_250_5};
        // НЕОБХОДИО СОЗДАТЬ АРРАИ ДЛЯ ОСТАЛЬНЫХ РАЗДЕЛОВ 4 И 5!!!!!!

    private int[] tanks_name_title = {R.string.tank1_title,R.string.tank2_title,R.string.tank3_title,R.string.tank4_title,R.string.tank5_title,
            R.string.tank6_title,R.string.tank7_title,R.string.tank8_title,R.string.tank9_title,R.string.tank10_title,R.string.tank11_title,R.string.tank12_title,};
    private int[] sau_name_title = {R.string.sau1_title,R.string.sau2_title,R.string.sau3_title,R.string.sau4_title,R.string.sau5_title,R.string.sau6_title,
            R.string.sau7_title,R.string.sau8_title,R.string.sau9_title,R.string.sau10_title,R.string.sau11_title,R.string.sau12_title,R.string.sau13_title,
            R.string.sau14_title,R.string.sau15_title,R.string.sau16_title,R.string.sau17_title,R.string.sau18_title,R.string.sau19_title,R.string.sau20_title,
            R.string.sau21_title,R.string.sau22_title,};
    private int[] btr_name_title = {R.string.btr1_title,R.string.btr2_title,R.string.btr3_title,R.string.btr4_title,R.string.btr5_title,R.string.btr6_title,
            R.string.btr7_title,R.string.btr8_title,R.string.btr9_title,R.string.btr10_title,R.string.btr11_title,R.string.btr12_title,};

    private String[] info_name_title = {"О приложении", "Оценить приложение", "Политика конфиденциальности"};
    private String[] free_name_title = {"Один день без рекламы", "Версия без рекламы навсегда",};


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_text1);
        MobileAds.initialize(this,"ca-app-pub-6623352601098354~7993432716");
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        init();
        resive_intent();





    }



    private void resive_intent() {
        Intent i = getIntent();
        if (i != null) {
            category = i.getIntExtra("category", 0);
            position = i.getIntExtra("position", 0);
        }
        switch (category) {
            case 0:
                i_content.setImageResource(tanks_array_img[position]);
                i_content2.setImageResource(tanks_array_img2[position]);
                i_content3.setImageResource(tanks_array_img3[position]);
                i_content4.setImageResource(tanks_array_img4[position]);
                i_content5.setImageResource(tanks_array_img5[position]);

                text_content.setText(tanks_array[position]);
                text_content2.setText(tanks_array2[position]);
                text_content3.setText(tanks_array3[position]);
                text_content4.setText(tanks_array4[position]);

                actionBar.setTitle(tanks_name_title [position]);
                break;

            case 1:
                i_content.setImageResource(sau_array_img[position]);
                i_content2.setImageResource(sau_array_img2[position]);
                i_content3.setImageResource(sau_array_img3[position]);
                i_content4.setImageResource(sau_array_img4[position]);
                i_content5.setImageResource(sau_array_img5[position]);

                text_content.setText(sau_array[position]);
                text_content2.setText(sau_array2[position]);
                text_content3.setText(sau_array3[position]);
                text_content4.setText(sau_array4[position]);

                actionBar.setTitle(sau_name_title [position]);
                break;

            case 2:
                i_content.setImageResource(btr_array_img[position]);
                i_content2.setImageResource(btr_array_img2[position]);
                i_content3.setImageResource(btr_array_img3[position]);
                i_content4.setImageResource(btr_array_img4[position]);
                i_content5.setImageResource(btr_array_img5[position]);

                text_content.setText(btr_array[position]);
                text_content2.setText(btr_array2[position]);
                text_content3.setText(btr_array3[position]);
                text_content4.setText(btr_array4[position]);

                actionBar.setTitle(btr_name_title [position]);
                break;

            case 3:
//                i_content.setImageResource(info_array_img[position]);
//                text_content.setText(info_array[position]);
//                actionBar.setTitle(info_name_title [position]);
                break;

            case 4:
//                i_content.setImageResource(free_array_img[position]);
//                text_content.setText(version_free_array[position]);
//                actionBar.setTitle(free_name_title [position]);
                break;


        }
        //overridePendingTransition(R.anim.side_in_right, R.anim.side_in_left);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init (){
        def_pref = PreferenceManager.getDefaultSharedPreferences(this);
        text_content = findViewById(R.id.text_main_content);
        text_content2 = findViewById(R.id.text_main_content2);
        text_content3 = findViewById(R.id.text_main_content3);
        text_content4 = findViewById(R.id.text_main_content4);
        i_content = findViewById(R.id.image_content);
        i_content2 = findViewById(R.id.image_content2);
        i_content3 = findViewById(R.id.image_content3);
        i_content4 = findViewById(R.id.image_content4);
        i_content5 = findViewById(R.id.image_content5);
//        text_content.setTypeface(face1);
//        text_content2.setTypeface(face2);
//        text_content3.setTypeface(face3);
//        text_content4.setTypeface(face4);
        actionBar = getSupportActionBar();
        Typeface face1 = ResourcesCompat.getFont(text_content_activity.this,R.font.lora_variablefont_wght);
        Typeface face2 = ResourcesCompat.getFont(text_content_activity.this,R.font.merriweather_black);
        Typeface face3 = ResourcesCompat.getFont(text_content_activity.this,R.font.bebasneue_regular);
        Typeface face4 = ResourcesCompat.getFont(text_content_activity.this,R.font.poiretone_regular);


        //Изменение размера шрифта
        String text_size = def_pref.getString("text_size", "Средний");
        if (text_size != null) {
            switch (text_size) {
                case "Мелкий":
                    text_content.setTextSize(17);
                    text_content2.setTextSize(17);
                    text_content3.setTextSize(17);
                    text_content4.setTextSize(17);
                    break;
                case "Средний":
                    text_content.setTextSize(21);
                    text_content2.setTextSize(21);
                    text_content3.setTextSize(21);
                    text_content4.setTextSize(21);
                    break;
                case "Крупный":
                    text_content.setTextSize(25);
                    text_content2.setTextSize(25);
                    text_content3.setTextSize(25);
                    text_content4.setTextSize(25);
                    break;
            }
        }
        // изменение цвета текста
        String text_color = def_pref.getString("text_color", "Черный");
        if (text_color != null) {
            switch (text_color) {
                case "Черный":
                    text_content.setTextColor(getResources().getColor(R.color.colorBlack, null));
                    text_content2.setTextColor(getResources().getColor(R.color.colorBlack, null));
                    text_content3.setTextColor(getResources().getColor(R.color.colorBlack, null));
                    text_content4.setTextColor(getResources().getColor(R.color.colorBlack, null));
                    break;
                case "Красный":
                    text_content.setTextColor(getResources().getColor(R.color.colorRed, null));
                    text_content2.setTextColor(getResources().getColor(R.color.colorRed, null));
                    text_content3.setTextColor(getResources().getColor(R.color.colorRed, null));
                    text_content4.setTextColor(getResources().getColor(R.color.colorRed, null));
                    break;
                case "Зеленый":
                    text_content.setTextColor(getResources().getColor(R.color.colorGreen, null));
                    text_content2.setTextColor(getResources().getColor(R.color.colorGreen, null));
                    text_content3.setTextColor(getResources().getColor(R.color.colorGreen, null));
                    text_content4.setTextColor(getResources().getColor(R.color.colorGreen, null));
                    break;
            }
        }

         //Изменение шрифта 19.07.2020
        String text_fonts = def_pref.getString("text_fonts", "Вариант 1");
        if (text_fonts != null) {
            switch (text_fonts) {
                case "Вариант 1":
                    text_content.setTypeface(face1);
                    text_content2.setTypeface(face1);
                    text_content3.setTypeface(face1);
                    text_content4.setTypeface(face1);
//                    text_content.setTypeface(Typeface.createFromAsset(getAssets(), "font/lora_variablefont_wght.ttf"));
//                    text_content2.setTypeface(Typeface.createFromAsset(getAssets(), "font/lora_variablefont_wght.ttf"));
//                    text_content3.setTypeface(Typeface.createFromAsset(getAssets(), "font/lora_variablefont_wght.ttf"));
//                    text_content4.setTypeface(Typeface.createFromAsset(getAssets(), "font/lora_variablefont_wght.ttf"));

                    break;
                case "Вариант 2":
                    text_content.setTypeface(face2);
                    text_content2.setTypeface(face2);
                    text_content3.setTypeface(face2);
                    text_content4.setTypeface(face2);
//                    text_content.setTypeface(Typeface.createFromAsset(getAssets(), "font/merriweather_black.ttf"));
//                    text_content2.setTypeface(Typeface.createFromAsset(getAssets(), "font/merriweather_black.ttf"));
//                    text_content3.setTypeface(Typeface.createFromAsset(getAssets(), "font/merriweather_black.ttf"));
//                    text_content4.setTypeface(Typeface.createFromAsset(getAssets(), "font/merriweather_black.ttf"));
                    break;
                case "Вариант 3":
                    text_content.setTypeface(face3);
                    text_content2.setTypeface(face3);
                    text_content3.setTypeface(face3);
                    text_content4.setTypeface(face3);
//                    text_content.setTypeface(Typeface.createFromAsset(getAssets(), "font/bebasneue_regular.ttf"));
//                    text_content2.setTypeface(Typeface.createFromAsset(getAssets(), "font/bebasneue_regular.ttf"));
//                    text_content3.setTypeface(Typeface.createFromAsset(getAssets(), "font/bebasneue_regular.ttf"));
//                    text_content4.setTypeface(Typeface.createFromAsset(getAssets(), "font/bebasneue_regular.ttf"));
                    break;
                case "Вариант 4":
                    text_content.setTypeface(face4);
                    text_content2.setTypeface(face4);
                    text_content3.setTypeface(face4);
                    text_content4.setTypeface(face4);
//                    text_content.setTypeface(Typeface.createFromAsset(getAssets(),"font/poiretone_regular.ttf"));
//                    text_content2.setTypeface(Typeface.createFromAsset(getAssets(),"font/poiretone_regular.ttf"));
//                    text_content3.setTypeface(Typeface.createFromAsset(getAssets(),"font/poiretone_regular.ttf"));
//                    text_content4.setTypeface(Typeface.createFromAsset(getAssets(),"font/poiretone_regular.ttf"));
                    break;
            }
        }



    }

}
