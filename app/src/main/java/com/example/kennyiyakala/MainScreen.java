package com.example.kennyiyakala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {
    TextView textView;
    TextView highScoreText;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        textView=findViewById(R.id.startText);
        highScoreText=findViewById(R.id.highScoreText);

        // Yüksek skoru al
        sharedPreferences = getSharedPreferences("com.example.kennyiyakala", MODE_PRIVATE);
        int highScore = sharedPreferences.getInt("stored", 0);
        highScoreText.setText("En Yüksek Puanın: " + highScore);

    }
    public void startGame(View view){
        Intent intent = new Intent(MainScreen.this, MainActivity.class);
        startActivity(intent);
    }
}