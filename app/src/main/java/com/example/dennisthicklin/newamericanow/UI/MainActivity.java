package com.example.dennisthicklin.newamericanow.UI;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dennisthicklin.newamericanow.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer tap_effect = MediaPlayer.create(this, R.raw.click);
        Button writingVocabButton = (Button) findViewById(R.id.writingVocabButton);
        Button governmentTestButton = (Button) findViewById(R.id.governmentTestButton);
        Button readingVocabButton = (Button) findViewById(R.id.readingVocabButton);
        Button historyTestButton = (Button) findViewById(R.id.historyTestButton);

        Toast.makeText(getApplicationContext(), "Categories Page", Toast.LENGTH_LONG).show();


        writingVocabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WritingVocabActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushin, R.anim.pushout);
                tap_effect.start();

            }
        });
        governmentTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AmGovActivity.class);
                startActivity(intent);
            }
        });


    }

}

