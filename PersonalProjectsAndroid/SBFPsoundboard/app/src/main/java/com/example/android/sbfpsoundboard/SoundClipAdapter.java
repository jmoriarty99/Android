package com.example.android.sbfpsoundboard;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by johnmoriarty on 9/13/16.
 */
public class SoundClipAdapter extends ArrayAdapter<SoundClip> {

    private int mColorResourceId;

    public SoundClipAdapter(Activity context, ArrayList<SoundClip> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        SoundClip currentClip = getItem(position);
        TextView words = (TextView) listItemView.findViewById(R.id.phrase_text_view);
        words.setText(currentClip.getmWords());

        View textContainer = listItemView.findViewById(R.id.phrase_text_view);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    };
}
