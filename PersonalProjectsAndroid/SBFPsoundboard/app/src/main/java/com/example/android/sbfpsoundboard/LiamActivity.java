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
public class LiamActivity extends AppCompatActivity {

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
        setTitle("Liam");
        setContentView(R.layout.word_list);

        final ArrayList<SoundClip> clips = new ArrayList<SoundClip>();

        clips.add(new SoundClip("Bring back Benoit", R.raw.liam_benoit));
        clips.add(new SoundClip("Kellogsbooberry", R.raw.liam_blueberry));
        clips.add(new SoundClip("Minted in Lego Land", R.raw.liam_lego));
        clips.add(new SoundClip("Simorilan", R.raw.liam_sim));
        clips.add(new SoundClip("Taking your shit", R.raw.liam_taking_shit));
        clips.add(new SoundClip("Liam...9/11", R.raw.liam_bum));
        clips.add(new SoundClip("Woolie stole everything", R.raw.liam_moie));
        clips.add(new SoundClip("Your resistance only makes me harder", R.raw.liam_harder));


        SoundClipAdapter adapter = new SoundClipAdapter(this, clips, R.color.category_liam);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SoundClip clip = clips.get(position);

                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(LiamActivity.this, clip.getmSoundClip());
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
