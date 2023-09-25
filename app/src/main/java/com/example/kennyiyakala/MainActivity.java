package com.example.kennyiyakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.function.IntConsumer;

public class MainActivity extends AppCompatActivity {
    TextView scoreText;
    TextView timeText;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    int score;
    ImageView[] imageArray;

    Handler handler;
    Runnable runnable;
    SharedPreferences sharedPreferences;
    int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText=findViewById(R.id.timeText);
        scoreText=findViewById(R.id.scoreText);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        score=0;
        sharedPreferences=this.getSharedPreferences("com.example.kennyiyakala",MODE_PRIVATE);
        y=sharedPreferences.getInt("stored",0);

        imageArray= new ImageView[]{
                imageView,
                imageView2,
                imageView3,
                imageView4,
                imageView5,
                imageView6,
                imageView7,
                imageView8,
                imageView9
        };
        visibilityKenny();
        downTimer();
        kennyGoing();
    }

    private void downTimer() {
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Kalan Süre: "+millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {
                int x=y;
                if(x<saveScore()) {
                    sharedPreferences.edit().putInt("stored", saveScore()).apply();
                }

                Toast.makeText(MainActivity.this,"Süre Bitti !",Toast.LENGTH_LONG).show();
                visibilityKenny();
                handler.removeCallbacks(runnable);
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Süre Bitti !");
                alert.setMessage("Skorunuz: "+score);
                alert.setMessage("Tekrar oynamak istermisiniz? ");

                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent= getIntent();
                        finish();

                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, MainScreen.class);

                        startActivity(intent);

                    }
                });
                alert.show();

            }
        }.start();//downtimer
    }

    private void kennyGoing() {
        Random random = new Random();


        handler= new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                visibilityKenny();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }

    public void calculateScore(View view){
        score++;
        scoreText.setText("Puan: "+score);
    }

    public void visibilityKenny(){
        for (int i=0;i<imageArray.length;i++){
            imageArray[i].setVisibility(View.INVISIBLE);
        }
    }

    int saveScore(){
        int saveScore;
        saveScore= score;
        return  saveScore;
    }

}