package com.ronak.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button single;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        single=(Button) findViewById(R.id.single);
        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();
            }
        });

    }
    public void openNewActivity()
    {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
}