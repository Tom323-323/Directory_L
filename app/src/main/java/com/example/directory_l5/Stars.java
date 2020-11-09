package com.example.directory_l5;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;

public class Stars extends AppCompatActivity {

    private Button btn_stars,btn_stars2;

    ReviewManager manager;
    ReviewInfo reviewInfo;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        manager = ReviewManagerFactory.create(Stars.this);
        Task <ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {

            @Override
            public void onComplete(Task<ReviewInfo> task) {
                if (task.isSuccessful()) {
                    reviewInfo = task.getResult();
                    Task <Void> flow = manager.launchReviewFlow(Stars.this,reviewInfo);
                    flow.addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void result) {

                        }
                    });
                } else {
                    Toast.makeText(Stars.this,"error",Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Скрыть нижную панель навигации
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );



        btn_stars = findViewById(R.id.btn_s);
        btn_stars2 = findViewById(R.id.button_back_info);
        btn_stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url ="market://";
//                Intent i = new Intent (Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
            }
        });

        btn_stars2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(Stars.this, MainActivity.class);
                startActivity(g);
                finish();
            }
        });
    }
}
