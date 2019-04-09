package com.example.ravil.miwok;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FAMILY extends AppCompatActivity {
    private MediaPlayer media;
    private AudioManager maudio;
    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                media.pause();
                media.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                media.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };
    private  MediaPlayer.OnCompletionListener mcompletion=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        maudio=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
     final ArrayList<WORD1> words =new ArrayList<WORD1>();

        words.add(new WORD1("MOM","mère",R.drawable.family_older_sister,R.raw.family_mother));
        words.add(new WORD1("DAD","père",R.drawable.family_father,R.raw.family_father));
        words.add(new WORD1("BROTHER","frère",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new WORD1("SISTER","sœur",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new WORD1("GRANDMOTHER","grand-mère",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new WORD1("GRANDFATHER","grand-père",R.drawable.family_grandfather,R.raw.family_grandfather));
        words.add(new WORD1("DAUGHTER","hija",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new WORD1("SON","hijo",R.drawable.family_son,R.raw.family_son));
        words.add(new WORD1("OLDER SISTER","sœur",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new WORD1("OLDER BROTHER","frère",R.drawable.family_older_brother,R.raw.family_older_brother));

        WORD1Adapter itemAdapter=new WORD1Adapter(this,words,R.color.category_family);
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(itemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                WORD1 word = words.get(i);
                releaseMediaPlayer();
                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = maudio.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {


                    media = MediaPlayer.create(FAMILY.this, word.getaudio());
                    media.start();
                    media.setOnCompletionListener(mcompletion);
                }
            }
        });

    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (media != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            media.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            media = null;
            maudio.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
