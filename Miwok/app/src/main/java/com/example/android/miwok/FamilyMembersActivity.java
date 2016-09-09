package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.category_family);
        setContentView(R.layout.activity_family_members);

        ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("one", "lutti", R.drawable.family_father));
        words.add(new Word("two", "otiiko", R.drawable.family_mother));
        words.add(new Word("three", "tolookosu", R.drawable.family_son));
        words.add(new Word("four", "oyyisa", R.drawable.family_daughter));
        words.add(new Word("five", "massokka", R.drawable.family_older_brother));
        words.add(new Word("six", "temmokka", R.drawable.family_older_sister));
        words.add(new Word("seven", "kenekaku", R.drawable.family_younger_brother));
        words.add(new Word("eight", "kawinta", R.drawable.family_younger_sister));
        words.add(new Word("nine", "wo'e", R.drawable.family_grandfather));
        words.add(new Word("ten", "na'aacha", R.drawable.family_grandmother));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
