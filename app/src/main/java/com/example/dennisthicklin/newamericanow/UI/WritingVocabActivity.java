package com.example.dennisthicklin.newamericanow.UI;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dennisthicklin.newamericanow.Model.Vocabulary;
import com.example.dennisthicklin.newamericanow.R;

import java.util.Locale;

public class WritingVocabActivity extends Activity {

    Vocabulary mVocabulary = new Vocabulary();
    String vocabWord;


    TextToSpeech ttsObject; // Create a variable for text to speech
    int result; // Create an int and name it result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_vocab);
        // Create a textView and give it the id of the object on the activity.
        Button switchWVChange = (Button) findViewById(R.id.changeWVText);

        Button checkButton = (Button) findViewById(R.id.checkButton);// Create a string variable and name it text this will contribute to the process of getting the text to be spoken.
        Button homeButton = (Button) findViewById(R.id.homeAmButton);

        final MediaPlayer right_sound = MediaPlayer.create(this, R.raw.right);
        final MediaPlayer wrong_sound = MediaPlayer.create(this, R.raw.wrong);
        final EditText userInput = (EditText) findViewById(R.id.userInput);
        vocabWord = mVocabulary.getWord();

        ttsObject = new TextToSpeech(WritingVocabActivity.this, new TextToSpeech.OnInitListener() {
            @Override // Implement override methods (onInit int status)
            public void onInit(int status) {


                if(status == TextToSpeech.SUCCESS){
                    result=  ttsObject.setLanguage(Locale.US);

                }else{

                    Toast.makeText(getApplicationContext(), "Text to Speech Not Supported on your Device", Toast.LENGTH_SHORT).show();

                }

            }
        });


        switchWVChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vocabWord = mVocabulary.getWord();
            }
        });




        checkButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(userInput.getText().toString().equalsIgnoreCase(vocabWord) ){

                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                    right_sound.start();

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);

                }else if(!userInput.getText().toString().equalsIgnoreCase(vocabWord)){

                    Toast.makeText(getApplicationContext(),"Incorrect: " + vocabWord , Toast.LENGTH_SHORT).show();
                    wrong_sound.start();

                }

                else{
                    Toast.makeText(getApplicationContext(), "The field is empty.", Toast.LENGTH_SHORT).show();
                }

            }
        });


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WritingVocabActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    //In order to generate the class below, you must give the object in the activity an onClick attribute. Give the attribute an apt name.
    public void readTheText(View view)
    {

        //This class allows the Text to be spoken by the device in English

        if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
            Toast.makeText(getApplicationContext(), "Text to Speech Not Supported on your Device", Toast.LENGTH_SHORT).show();

        }else{

            ttsObject.speak(vocabWord, TextToSpeech.QUEUE_FLUSH, null);

        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(ttsObject != null){
            ttsObject.stop();
            ttsObject.shutdown();
        }
    }




}


