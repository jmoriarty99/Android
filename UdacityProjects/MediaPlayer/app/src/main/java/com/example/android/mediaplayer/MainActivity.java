package com.example.android.mediaplayer;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.kens_theme);

        Button playbutton = (Button)findViewById(R.id.play_button);
        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                    /**
                     * send a toast message once the mp3 has stopped playing
                     * @param mediaPlayer
                     */
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer){
                        Toast.makeText(MainActivity.this, "I'm done", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button pausebutton = (Button)findViewById(R.id.pause_button);
        pausebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });
    }

}
