package com.example.android.sbfpsoundboard;

/**
 * Created by johnmoriarty on 9/13/16.
 */
public class SoundClip {

    private String mWords;
    private int mSoundClip;

    public SoundClip(String words, int soundClip){
        mWords = words;
        mSoundClip = soundClip;
    }

    public String getmWords() {
        return mWords;
    }

    public int getmSoundClip() {
        return mSoundClip;
    }


}
