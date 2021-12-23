package edu.mahnoor.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int DeepPink=1;
    private final int Red=2;
    private final int Yellow=3;
    private final int SkyBlue=4;



    Button bDeepPink,bRed,bYellow,bSkyBlue ,btnplay;
    View Option1,Option2,Option3,Option4;
    String Answer="";
    int count,score=0 ,dbCheck=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent mIntent=getIntent();
        score=mIntent.getIntExtra("score",0);
        dbCheck=mIntent.getIntExtra("dbCheck",0);

        /*bDeepPink=findViewById(R.id.Pink);
        bRed=findViewById(R.id.Red);
        bYellow=findViewById(R.id.Yellow);
        bSkyBlue=findViewById(R.id.SkyBlue);*/
        //btnplay=findViewById(R.id.btndoplay);

        Option1=findViewById(R.id.btnSelectedPink);
        Option2=findViewById(R.id.btnSelectedRed);
        Option3=findViewById(R.id.btnSelectedSkyBlue);
        Option4=findViewById(R.id.btnSelectedYellow);

        count =4;
        int play= 2 * score;
        count = count + play;



        DatabaseHandler db = new DatabaseHandler(this);
        if(dbCheck == 0){
            db.emptyHiScores();
            db.addHiScore(new HiScore("21 November 2021 ","Jack",2));
            db.addHiScore(new HiScore("22 November 2021", "James", 2));
            db.addHiScore(new HiScore("23 November 2021", "Noah", 2));
            db.addHiScore(new HiScore("24 November 2021", "Daniel", 22));
            db.addHiScore(new HiScore("12 December 2021", "Owen", 30));
            db.addHiScore(new HiScore("14 December 2021", "Connor", 22));
            db.addHiScore(new HiScore("16 December 2021", "Nolan", 132));
            db.addHiScore(new HiScore("17 December 2021", "leah", 1222));
            db.addHiScore(new HiScore("18 December 2021", "Zoe", 1322));
        }
    }


    public void check(){
        if(Option1.getVisibility()==View.VISIBLE || Option2.getVisibility() ==
                View.VISIBLE || Option3.getVisibility() == View.VISIBLE || Option4.getVisibility() ==View.VISIBLE){
            Option1.setVisibility(View.INVISIBLE);
            Option2.setVisibility(View.INVISIBLE);
            Option3.setVisibility(View.INVISIBLE);
            Option4.setVisibility(View.INVISIBLE);
        }

    }

    public  void  selection(View selection,int random){
        selection.setVisibility(View.VISIBLE);
        Answer=Answer + random;
    }
    public void doPlay(View view) {
        if(count ==0){
            Intent intent = new Intent(MainActivity.this,Screen2.class);//Screen 2 Game Screen where squence show
            intent.putExtra("score",score);
            intent.putExtra("answer",Answer);
            startActivity(intent);
        }

    }
//move which will create a randon sequence
    public void domove(View view) {

        if(count !=0){
            check();
            int random = new Random().nextInt(4)+1;
            View selectionColor=null;
            if(random == 1){
                selectionColor=Option1;
            }
            else  if (random == 2){
                selectionColor=Option2;
            }
            else  if(random == 3){
                selectionColor=Option3;
            }
            else if(random == 4){
                selectionColor=Option4;
            }
            selection(selectionColor,random);
            count--;
        }

    }




}