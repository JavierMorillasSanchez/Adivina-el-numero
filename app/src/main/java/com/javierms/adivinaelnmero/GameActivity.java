package com.javierms.adivinaelnmero;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView txtUltimaSuposicion, txtPistas, txtIntentos;
    Button btnConfirmar;
    EditText etxtNumber;
    int intentos = 10;

    Random r = new Random();
    int miRandom, intentosRestantes;

    boolean twoDigits, threeDigits, fourDigits;

    ArrayList<Integer> guessList = new ArrayList<>();
    int userAttemps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        txtIntentos = findViewById(R.id.txtIntentos);
        txtPistas = findViewById(R.id.txtPista);
        txtUltimaSuposicion = findViewById(R.id.txtUltimaSuposicion);
        btnConfirmar = findViewById(R.id.btnConfirmar);
        etxtNumber = findViewById(R.id.etxtNumber);

        twoDigits = getIntent().getBooleanExtra("two", false);
        threeDigits = getIntent().getBooleanExtra("three", false);
        fourDigits = getIntent().getBooleanExtra("four", false);

        if(twoDigits == true){
            miRandom = r.nextInt(90)+10;
        }

        if(threeDigits == true){
            miRandom = r.nextInt(900)+100;
        }

        if(fourDigits == true){
            miRandom = r.nextInt(9000)+1000;
        }

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String miValor = etxtNumber.getText().toString();

                if(miValor.isEmpty()){
                    Snackbar.make(v, "Por favor, introduce un número.", Snackbar.LENGTH_LONG).show();
                } else {

                    txtUltimaSuposicion.setVisibility(View.VISIBLE);
                    txtPistas.setVisibility(View.VISIBLE);
                    txtIntentos.setVisibility(View.VISIBLE);

                    userAttemps++;
                    intentos--;
                    intentosRestantes = 10 - intentos;

                    int userGuess = Integer.parseInt(miValor);
                    guessList.add(userGuess);

                    txtUltimaSuposicion.setText("Tu última suposicion fue: "+miValor);
                    txtIntentos.setText("Número de intentos restanes: "+intentos);

                    if (miRandom == userGuess){

                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("¡Adivina el número!");
                        builder.setCancelable(false);
                        builder.setMessage("¡Enhorabuena! Tu número ha sido "+userGuess+"\n\n Has adivinado mi número en "+ intentosRestantes+
                                " intentos. \n\n Tus números han sido: "+guessList
                                + "\n\n ¿Quieres jugar otra vez?");
                        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent miIntent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(miIntent);
                                finish();
                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });

                        builder.create().show();

                    }

                    if (miRandom < userGuess){

                        txtPistas.setText("Prueba con un número más bajo.");

                    }

                    if (miRandom > userGuess){

                        txtPistas.setText("Prueba con un número más alto.");

                    }

                    if (intentos == 0){

                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("¡Adivina el número!");
                        builder.setCancelable(false);
                        builder.setMessage("¡Qué pena, te has quedado sin intentos! "
                                +"\n\n Mi número era: "+miRandom
                                +"\n\n Tus números han sido: "+guessList
                                +"\n\n ¿Quieres jugar otra vez?");
                        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent miIntent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(miIntent);
                                finish();
                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });

                        builder.create().show();

                    }

                    etxtNumber.setText("");

                }
            }
        });


    }
}