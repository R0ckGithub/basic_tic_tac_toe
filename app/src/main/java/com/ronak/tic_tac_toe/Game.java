package com.ronak.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends AppCompatActivity {

    private static final String TAG = "Game";
    private ImageButton[] img;
    private Button undo_btn,clr_btn,newgame;
    private TextView zero,cross;
    private int cnt=0;
    private int[] array;
    private TextView winner_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        array = new int[10];
        //Intialsiing Array
        for(int i=0;i<10;++i)
        {
            array[i]=-1;
        }


        img=new ImageButton[10];
        img[0]=(ImageButton)findViewById(R.id.imageButton1);
        int temp=img[0].getId();
        undo_btn=(Button) findViewById(R.id.undo);
        clr_btn=(Button) findViewById(R.id.clear);
        zero = findViewById(R.id.zero_txt);
        cross = findViewById(R.id.cross_txt);
        winner_txt= findViewById(R.id.winner_txt);
        newgame=findViewById(R.id.newgame_btn);


        winner_txt.setVisibility(View.INVISIBLE);
        //clr_btn.setText("Restart");

        //Intial Turn
        zero.setBackgroundColor(3);


        //1 based index
        for(int i=0;i<=8;++i)
        {
            img[i+1]=findViewById(temp+i);
        }

        //clr btn functiom
        clr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnt=0;
                for(int i=0;i<=9;++i)
                {
                    img[i].setImageDrawable(getDrawable(R.drawable.grid));
                    array[i]=-1;
                    img[i].setClickable(true);
                }
                undo_btn.setClickable(true);
                winner_txt.setText("");
                winner_txt.setVisibility(View.INVISIBLE);
            }
        });


        //0 zero
        //1 cross
        for(int i=1;i<=9;++i) {

            final int finalI = i;
            img[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(array[finalI]!=-1)
                    {
                        Log.d(TAG, "onClick: "+finalI);
                         Toast.makeText(getBaseContext(), "INVALID MOVE", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        img[0] = img[finalI];
                        array[0]=finalI;
                        if(cnt%2==0)
                        {
                            img[finalI].setImageDrawable(getDrawable(R.drawable.zero));
                            array[finalI]=0;
                        }
                        else
                        {
                            img[finalI].setImageDrawable(getDrawable(R.drawable.cross));
                            array[finalI]=1;
                        }
                        ++cnt;
                        if(cnt>2)
                        {
                            int winner=-1;


                            //checking winning condition
                            for(int j=1;j<10;j+=3) {
                                if (array[j] != (-1) && array[j] == array[j + 1] && array[j] == array[j + 2]) {
                                    Log.d(TAG, "onClick: winner1");
                                    winner = array[j];
                                    break;
                                }
                            }
                            if(winner==-1)
                                for(int j=1;j<4;++j)
                                    if( array[j]!=(-1) && array[j]==array[j+3]&& array[j]==array[j+6] )
                                    {
                                        Log.d(TAG, "onClick: winner2");
                                        winner=array[j];
                                        break;
                                    }

                            if(winner==-1) {
                                if (array[1] != (-1) && array[1] == array[5] && array[1] == array[9]) {
                                    Log.d(TAG, "onClick: winner3");
                                    winner = array[1];
                                }

                                if (array[3] != (-1) && array[3] == array[5] && array[3] == array[7]) {
                                    Log.d(TAG, "onClick: winner4");
                                    winner = array[3];
                                }
                            }
                            
                            if(winner!=-1)
                            {
                                String str;
                                if(winner==1)str="CROSS";
                                else str="ZERO";
                                winner_txt.setText(str+" Wins");
                                winner_txt.setVisibility(View.VISIBLE);
                                for(int i=1;i<10;++i)
                                {
                                    img[i].setClickable(false);
                                }
                                undo_btn.setClickable(false);
                                //clr_btn.setText("Play Again");
                            }
                            else
                            {
                                if(cnt==9)
                                {
                                    winner_txt.setText("DRAW");
                                    winner_txt.setVisibility(View.VISIBLE);
                                   // clr_btn.setText("Play Again");
                                    for(int i=1;i<10;++i)
                                    {
                                        img[i].setClickable(false);
                                    }
                                    undo_btn.setClickable(false);

                                }
                            }
                        }
                    }


                }
            });
            
        }

        undo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(array[0]==-1)
                {

                }
                else {
                    img[0].setImageDrawable(getDrawable(R.drawable.grid));
                    array[array[0]]=-1;
                    array[0]=-1;
                    --cnt;
                }
                }
        });

        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();
            }
        });




    }


    public void openNewActivity()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}