package com.example.fitathome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeVideo extends YouTubeBaseActivity {
    private TextView txtVwName;
    private YouTubePlayerView ytPlayer;
    private YouTubePlayer.OnInitializedListener youTubeOnlistener;
    private String videoID, key, type, level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_video);

        Intent i = getIntent();
        level = i.getStringExtra("Level");
        type = i.getStringExtra("Workout");

        key = YoutubeVideo.this.getResources().getString(R.string.google_api_key);

        txtVwName = (TextView) findViewById(R.id.txtVwName);
        txtVwName.setText(i.getStringExtra("Name"));
        videoID= i.getStringExtra("YoutubeID");

        ytPlayer = (YouTubePlayerView) findViewById(R.id.ytbPlaylist);
        youTubeOnlistener= new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b){
                    youTubePlayer.loadVideo(videoID);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        ytPlayer.initialize(key, youTubeOnlistener);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(YoutubeVideo.this, WorkoutPage.class);
            intent.putExtra("Level", level);
            intent.putExtra("Workout", type);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}