package com.example.android.sbfpsoundboard;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by johnmoriarty on 9/13/16.
 */
public class MattActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Matt");
        setContentView(R.layout.word_list);

        final ArrayList<SoundClip> clips = new ArrayList<SoundClip>();

        clips.add(new SoundClip("A festival of sweaty men", R.raw.matt_sweaty_men));
        clips.add(new SoundClip("Dreamcast", R.raw.matt_dreamcast));
        clips.add(new SoundClip("Hermione", R.raw.matt_hermine));
        clips.add(new SoundClip("Uncle Ben, kill them all", R.raw.matt_kill_them_all));
        clips.add(new SoundClip("Please don't hammer nails into my penis", R.raw.matt_nails_penis));
        clips.add(new SoundClip("The puckering asshole of sauron", R.raw.matt_pucker));
        clips.add(new SoundClip("Talking shit about the Dreamcast", R.raw.matt_shit_dreamcast));
        clips.add(new SoundClip("Snape smash talk", R.raw.matt_snape));
        clips.add(new SoundClip("Well stop trying to front", R.raw.matt_stop_trying_to_front));
        clips.add(new SoundClip("The rock's sexual energy", R.raw.matt_rock_sexual));


        SoundClipAdapter adapter = new SoundClipAdapter(this, clips, R.color.category_matt);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SoundClip clip = clips.get(position);

                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(MattActivity.this, clip.getmSoundClip());
                mMediaPlayer.start();

                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        //When the activity is stopped, release the media player resources because we won't be playing any more sounds.
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if(mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

}
