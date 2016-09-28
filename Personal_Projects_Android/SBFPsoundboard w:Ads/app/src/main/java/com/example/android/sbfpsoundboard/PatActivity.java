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
public class PatActivity extends AppCompatActivity {

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
        setTitle("Pat");
        setContentView(R.layout.word_list);

        final ArrayList<SoundClip> clips = new ArrayList<SoundClip>();

        clips.add(new SoundClip("Pat's saying stupid shit again", R.raw.pat_stupid_shit));
        clips.add(new SoundClip("So Ariel just wanted the dick", R.raw.pat_ariel_dick));
        clips.add(new SoundClip("OMG Fucking Chomp chomps", R.raw.pat_chomps));
        clips.add(new SoundClip("Boy I'd like some dick", R.raw.pat_dick));
        clips.add(new SoundClip("Take my dick out", R.raw.pat_dick_out));
        clips.add(new SoundClip("Evil and a Racist", R.raw.pat_evil_racist));
        clips.add(new SoundClip("Fuck you", R.raw.pat_fuck_you));
        clips.add(new SoundClip("How shitty of a loser I am", R.raw.pat_loser));
        clips.add(new SoundClip("No dick for you", R.raw.pat_no_dick));
        clips.add(new SoundClip("Good shitty HHH hair", R.raw.pat_good_shitty));
        clips.add(new SoundClip("Take this little child", R.raw.pat_little_child));
        clips.add(new SoundClip("Aww my breadsticks", R.raw.pat_aww_my_breadsticks));
        clips.add(new SoundClip("Reptile, gathering of the Juggalos", R.raw.pat_reptile));
        clips.add(new SoundClip("I'm already a skeleton", R.raw.pat_skeleton));
        clips.add(new SoundClip("Yapapie Strap", R.raw.pat_yapapie_strap));


        SoundClipAdapter adapter = new SoundClipAdapter(this, clips, R.color.category_pat);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SoundClip clip = clips.get(position);

                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(PatActivity.this, clip.getmSoundClip());
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
