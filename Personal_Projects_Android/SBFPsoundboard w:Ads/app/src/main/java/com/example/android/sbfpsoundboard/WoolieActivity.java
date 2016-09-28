package com.example.android.sbfpsoundboard;

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
public class WoolieActivity extends AppCompatActivity {

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
        setTitle("Woolie");
        setContentView(R.layout.word_list);

        final ArrayList<SoundClip> clips = new ArrayList<SoundClip>();

        clips.add(new SoundClip("Who trashed my baby's grave", R.raw.woolie_baby_grave));
        clips.add(new SoundClip("Thats not a baby", R.raw.woolie_baby_pile));
        clips.add(new SoundClip("Fucking Bullshit", R.raw.woolie_fucking_bullshit));
        clips.add(new SoundClip("Skeleton Bow", R.raw.woolie_skeleton));
        clips.add(new SoundClip("So many Asses", R.raw.woolie_ass));
        clips.add(new SoundClip("This one's for your morph", R.raw.woolie_morph));
        clips.add(new SoundClip("Suck the Ass man", R.raw.woolie_suck_ass));

        SoundClipAdapter adapter = new SoundClipAdapter(this, clips, R.color.category_woolie);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SoundClip clip = clips.get(position);

                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(WoolieActivity.this, clip.getmSoundClip());
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
