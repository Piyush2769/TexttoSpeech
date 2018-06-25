package com.example.piyush.texttospeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private Button speak;
    private TextToSpeech engine;
    private SeekBar seekPitch,seekSpeed;
    private EditText editText;
    private float pitchRate=1f,speedRate=1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speak=findViewById(R.id.speak);
        editText=findViewById(R.id.sentence);
        seekPitch=findViewById(R.id.seekPitch);
        seekSpeed=findViewById(R.id.seekSpeed);

        engine=new TextToSpeech(this,this);

        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

        seekPitch.setThumbOffset(5);
        seekPitch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pitchRate=((float)progress)/100f;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekSpeed.setThumbOffset(5);
        seekSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                speedRate=((float)progress)/100f;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void speak() {
        engine.setPitch(pitchRate);
        engine.setSpeechRate(speedRate);

        engine.speak(editText.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,null);
        Toast.makeText(this, "Your Device is Speaking", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onInit(int status) {
        if(status== TextToSpeech.SUCCESS)
            engine.setLanguage(Locale.UK);
    }
}
