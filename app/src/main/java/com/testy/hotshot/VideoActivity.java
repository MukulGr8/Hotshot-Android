package com.testy.hotshot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Mukul Ahlawat on 3/16/2018.
 */

public class VideoActivity extends Activity {

    String s1;
    RelativeLayout videoAct;
    MediaController mediaController;
    ProgressBar videoprogressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_layout);
        Intent i = getIntent();
        s1 = i.getStringExtra("value");
        System.out.println("this is video no "+ s1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mediaController = new MediaController(this);
        videoprogressBar = findViewById(R.id.videoProgressBar_id);
        videoAct = findViewById(R.id.linear_id);
        new MyTask().execute();
    }
    public class MyTask extends AsyncTask<String,Void,Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            final VideoView videoView = findViewById(R.id.myvideoview);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    videoprogressBar.setVisibility(videoView.INVISIBLE);
                }
            });
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            String myUrl = "https://teamhotshot.000webhostapp.com/videos/clip"+s1+".mp4";
            Uri uri = Uri.parse(myUrl);
            videoView.setVideoURI(uri);
            videoView.start();


        }

        @Override
        protected Void doInBackground(String... strings) {
            return null;
        }
    }
}
