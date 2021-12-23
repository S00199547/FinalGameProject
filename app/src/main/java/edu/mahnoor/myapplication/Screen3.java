package edu.mahnoor.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.jar.Attributes;

public class Screen3 extends AppCompatActivity {

    int score;
    TextView textScore;
    EditText edt;
    Button btns , button4,button5;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        Intent mIntent = getIntent();
        score = mIntent.getIntExtra("score",0);
        score=score*2;

        btns = findViewById(R.id.btns);
       // edt=findViewById(R.id.edt);
        textScore=findViewById(R.id.textScore);//text score will show
        textScore.setText(" Your score was :" + score);

//database
        int dbScore;
        db = new DatabaseHandler(this);
       List<HiScore> top5HiScores = db.getTopFiveScores();
        for (HiScore hs : top5HiScores){
            dbScore = hs.getScore();
            if(score > dbScore){
                btns.setVisibility(View.VISIBLE);
                //edt.setVisibility(View.VISIBLE);

            }
        }

    }

    public void play(View view) {
        int dbCheck=1;
        Intent myIntent = new Intent(Screen3.this,MainActivity.class);//go back to fist page
        myIntent.putExtra("dbCheck", dbCheck);
        startActivity(myIntent);
    }

    public void firstfiveplayers(View view) {

        Intent myIntent = new Intent(Screen3.this,Screen4.class);
        startActivity(myIntent);

    }
//Submitt button
    public void submmitt(View view) {
        //String Name = edt.getText().toString();

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyy", Locale.getDefault());
        String formattedDate = df.format(c);
        db.addHiScore(new HiScore(formattedDate , "score",+score));
       Intent myIntent = new Intent(Screen3.this,Screen4.class);
        startActivity(myIntent);


    }
}