package com.example.fitathome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

public class WorkoutPage extends YouTubeBaseActivity {
    private TextView txtVwWorkout;
    private String level, type;
    private Integer frequency;
    private String[] easyWorkouts = {"gC_L9qAHVJ8", "MxLL9Scvmzo", "37PwEIjGhEY"};
    private String[] intermediateWorkouts = {"ZgPWjI-FG24", "aW5IYarq0fo", "50kH47ZztHs"};
    private String[] name = {"Easy Workout", "Workout in 15 min", "Quick Workout"};
    private String[] descriptions = {"In this video ...", "This quick and easy workout is ...", "Let's do our best!"};
    private String[] playlist;
    private String key;
    private ListView ltVwYoutube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_page);

        txtVwWorkout = (TextView) findViewById(R.id.txtVwWorkout);
        key = WorkoutPage.this.getResources().getString(R.string.google_api_key);
        Intent i = getIntent();
        type = i.getStringExtra("Workout");
        txtVwWorkout.setText(type);
        level = i.getStringExtra("Level");
        switch (level) {
            case "Beginner":
                playlist = easyWorkouts;
                break;
            case "Intermediate":
                playlist = intermediateWorkouts;
                break;
            case "Professional":
                break;
        }
        frequency = i.getIntExtra("Frequency", 0);
        WorkoutAdapter adapter = new WorkoutAdapter(WorkoutPage.this, name, descriptions, playlist, key);

        ltVwYoutube= (ListView) findViewById(R.id.ltVwYoutube);
        ltVwYoutube.setAdapter(adapter);
        ltVwYoutube.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i  = new Intent(WorkoutPage.this, YoutubeVideo.class);
                    i.putExtra("Name", name[position]);
                    i.putExtra("YoutubeID", playlist[position]);
                    i.putExtra("Level", level);
                    i.putExtra("Workout", type);
                    startActivity(i);
                    finish();
            }
        });

    }

}

class WorkoutAdapter extends BaseAdapter {
    private final Context context;
    private String[] description;
    private String[] name;
    private String[] playlist;
    private final Resources res;
    private String key;

    YouTubeThumbnailView.OnInitializedListener youTubeOnlistener;

    public WorkoutAdapter(Context context, String[] name, String[] description, String[] playlist, String key) {
        this.context = context;
        this.description = description;
        this.name = name;
        this.playlist = playlist;
        this.res = context.getResources();
        this.key = key;

    }

    @Override
    public int getCount() {
        return description.length;
    }

    @Override
    public String getItem(int pos) {
        return description[pos];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        //Get the instance of our chat
        View row;
        if (view == null) {  //indicates this is the first time we are creating this row.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  //Inflater's are awesome, they convert xml to Java Objects!
            row = inflater.inflate(R.layout.customworkout, parent, false);
        } else {
            row = view;
        }


        //Get UI objects
        YouTubeThumbnailView ytbPlaylist = (YouTubeThumbnailView ) row.findViewById(R.id.ytbPlaylist);
        TextView nameView = (TextView) row.findViewById(R.id.txtVwName);
        TextView messageView = (TextView) row.findViewById(R.id.txtVwDescription);
        youTubeOnlistener = new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                    }
                });
                youTubeThumbnailLoader.setVideo(playlist[position]);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }

        };

        ytbPlaylist.initialize(key, youTubeOnlistener);
        //Set text into TextViews
        nameView.setText(name[position]);
        messageView.setText(description[position]);

        return row;
    }
}