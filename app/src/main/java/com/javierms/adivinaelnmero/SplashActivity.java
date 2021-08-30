package com.javierms.adivinaelnmero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private ImageView myImageview;
    private TextView myTextview;

    Animation animationImage;
    Animation animationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        myImageview = findViewById(R.id.imgPortada);
        myTextview = findViewById(R.id.txtTitulo);

        animationImage = AnimationUtils.loadAnimation(this, R.anim.image_animation);
        animationText = AnimationUtils.loadAnimation(this, R.anim.text_animation);

        myImageview.setAnimation(animationImage);
        myTextview.setAnimation(animationText);

        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent miIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(miIntent);
                finish();
            }
        }.start();

    }
}