package edu.mahnoor.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class Screen4 extends AppCompatActivity {

    TextView Player1,Player2,Player3,Player4,Player5;
    TextView[] textViews = new TextView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);

        Player1=findViewById(R.id.Player1);
        Player2= findViewById(R.id.Player2);
        Player3= findViewById(R.id.player3);
        Player4= findViewById(R.id.Player4);
        Player5= findViewById(R.id.Player5);
        textViews[0] = Player1;
        textViews[1] = Player2;
        textViews[2] = Player3;
        textViews[3] = Player4;
        textViews[4] = Player5;
        String log = "";
        int counter = 0;

//database
        DatabaseHandler db = new DatabaseHandler(this);
       List<HiScore> top5HiScores = db.getTopFiveScores();
        for (HiScore hs : top5HiScores){
            log=
                    "Id:" + hs.getScore_id()+
                            ",Date:" +hs.getGame_date()+
                            ",Player:" +hs.getPlayer_name()+
                            ",Score:"+hs.getScore();

            textViews[counter].setText(log);
            counter++;

        }



    }
    //play again button
    public void play(View view) {


        int dbCheck =1;
        Intent myIntent=new Intent(Screen4.this,MainActivity.class);//go back to start of the game
        myIntent.putExtra("dbCheck",dbCheck);
        startActivity(myIntent);
    }
}