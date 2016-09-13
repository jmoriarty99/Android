package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {

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
        setTitle(R.string.category_family);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("one", "lutti", R.drawable.family_father, R.raw.kens_theme));
        words.add(new Word("two", "otiiko", R.drawable.family_mother, R.raw.kens_theme));
        words.add(new Word("three", "tolookosu", R.drawable.family_son, R.raw.kens_theme));
        words.add(new Word("four", "oyyisa", R.drawable.family_daughter, R.raw.kens_theme));
        words.add(new Word("five", "massokka", R.drawable.family_older_brother, R.raw.kens_theme));
        words.add(new Word("six", "temmokka", R.drawable.family_older_sister, R.raw.kens_theme));
        words.add(new Word("seven", "kenekaku", R.drawable.family_younger_brother, R.raw.kens_theme));
        words.add(new Word("eight", "kawinta", R.drawable.family_younger_sister, R.raw.kens_theme));
        words.add(new Word("nine", "wo'e", R.drawable.family_grandfather, R.raw.kens_theme));
        words.add(new Word("ten", "na'aacha", R.drawable.family_grandmother, R.raw.kens_theme));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word word = words.get(position);

                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(FamilyMembersActivity.this, word.getAudioResourceId());
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
