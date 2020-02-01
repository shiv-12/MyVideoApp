package com.example.myvideoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class Audio extends AppCompatActivity {

    Button btn1,btn2;
    SeekBar seekBar,timeline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        timeline = (SeekBar)findViewById(R.id.timeline);

//TODO ADD AUDIO
       final MediaPlayer mp =  MediaPlayer.create(this,R.raw.audio);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
            }
        });
//TODO ADD SEEK BAR OF AUDIO

        final AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        final int progres = am.getStreamVolume(AudioManager.STREAM_MUSIC);

        seekBar.setMax(max);
        seekBar.setProgress(progres);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
             am.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //TODO ADD SEEK BAR OF TIME LINE


        timeline.setMax(mp.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeline.setProgress(mp.getCurrentPosition());

            }
        },0,1000);


     timeline.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
         @Override
         public void onProgressChanged(SeekBar seekBar, int i, boolean fromuser) {

             if (fromuser)
             {
                 mp.seekTo(i);
             }

         }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {

         }

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {

         }
     });

    }
}
