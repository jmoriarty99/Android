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
public class GroupActivity extends AppCompatActivity {

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
        setTitle("Group");
        setContentView(R.layout.word_list);

        final ArrayList<SoundClip> clips = new ArrayList<SoundClip>();

        clips.add(new SoundClip("Time to replace Jason", R.raw.matt_pat_replace_jason));
        clips.add(new SoundClip("Matt and Pat kissing", R.raw.matt_patt_kissing));
        clips.add(new SoundClip("You Motherfuckers", R.raw.group_motherfuckers));
        clips.add(new SoundClip("My Native Dance", R.raw.matt_native_dance));
        clips.add(new SoundClip("Hufflepuff", R.raw.matt_pat_hufflpuff));
        clips.add(new SoundClip("Leviosaaa", R.raw.matt_pat_leviosa));
        clips.add(new SoundClip("Smaller then you'd hope", R.raw.matt_patt_dick));
        clips.add(new SoundClip("Girlfriends dead", R.raw.woolie_liam_girlfriend_dead));
        clips.add(new SoundClip("Be my Stand", R.raw.woolie_pat_stand));
        clips.add(new SoundClip("Skeleton singing", R.raw.woolie_matt_skeleton));
        clips.add(new SoundClip("Triple Triad", R.raw.woolie_liam_triple));
        clips.add(new SoundClip("Thats his achilles ass", R.raw.group_achillies_ass));
        clips.add(new SoundClip("The gang goes nuts", R.raw.group_loses_shit));
        clips.add(new SoundClip("Undertaker and Woolie's date", R.raw.group_undertaker));
        clips.add(new SoundClip("Mortal Kombat", R.raw.group_mortal_kombat));
        clips.add(new SoundClip("Brussels accent", R.raw.group_vandam));
        clips.add(new SoundClip("How did he find that book you made", R.raw.matt_pat_woolie_page));
        clips.add(new SoundClip("Pre-Anal Post-China X-Pac", R.raw.matt_pat_woolie_xpac));
        clips.add(new SoundClip("Woolie really loves Aria", R.raw.group_woolie));


        SoundClipAdapter adapter = new SoundClipAdapter(this, clips, R.color.category_group);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SoundClip clip = clips.get(position);

                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(GroupActivity.this, clip.getmSoundClip());
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
