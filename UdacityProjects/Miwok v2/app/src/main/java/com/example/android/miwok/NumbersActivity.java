package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RemoteController;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    //Handles audio focus when playing a sound file
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
        }
    };

    //This listener gets triggered whe the (@link MeidaPlayer} has completed playing the audio file
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTitle(R.string.category_numbers);
        setContentView(R.layout.word_list);

        //Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.kens_theme));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.power_rangers_area_one));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.power_rangers_boss));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.kens_theme));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.power_rangers_area_one));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.power_rangers_boss));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.kens_theme));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.power_rangers_area_one));
        words.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.power_rangers_boss));
        words.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.kens_theme));


//        LinearLayout rootView =(LinearLayout)findViewById(R.id.rootView);

//        for ( int index = 0 ; index< words.size(); index++) {
//            TextView wordView = new TextView(this);
//            wordView.setText(words.get(index));
//            rootView.addView(wordView);




        //Create an {@link ArrayAdapter}, whose data source is a list of Strings. The adapter knows how to create layouts for each item in the list, using the simple_list_item_1.xml
        //layout resource defined in the Android framework. This list item layout contains a single {@link TextView}, which the adapter will set to display a single word.

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        //Find the {@link ListView} object in the view hierarchy of the {@link Activity}. There should be a {@link ListView} with the view ID called list, which is declared in the
        //activity_numbers.xml layout file

        ListView listView = (ListView) findViewById(R.id.list);

        //Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the {@link ListView} will display list items for each word in the list words.
        //Do this by calling the setAdapter method on the {@link ListView} object and pass in 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Get the {@link Word} object at the given position the user clicked on
                Word word = words.get(position);

                /**
                 * Release the media player if it currently exists because we are about to play a different sound file.
                 */
                releaseMediaPlayer();

                //Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        //Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        //Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        //When the activity is stopped, release the media player resources because we won't be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        if(mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;

            //Regardless of whether or not we were granted audio focus, abandon it. This also
            //unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
