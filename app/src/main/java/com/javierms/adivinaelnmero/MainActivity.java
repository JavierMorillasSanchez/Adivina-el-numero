package com.javierms.adivinaelnmero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private RadioButton radio2, radio3, radio4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        radio2 = findViewById(R.id.rdbTwoDigits);
        radio3 = findViewById(R.id.rdbThreeDigits);
        radio4 = findViewById(R.id.rdbFourDigits);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(getApplicationContext(), GameActivity.class);

                if(!radio2.isChecked() && !radio3.isChecked() && !radio4.isChecked()){
                    Snackbar.make(v, "Por favor, selecciona la cantidad de dígitos.", Snackbar.LENGTH_LONG).show();
                } else {
                    if (radio2.isChecked()){
                        miIntent.putExtra("two",true);
                    }
                    if (radio3.isChecked()){
                        miIntent.putExtra("three",true);
                    }
                    if (radio4.isChecked()){
                        miIntent.putExtra("four",true);
                    }

                    startActivity(miIntent);
                }

            }
        });
    }
}