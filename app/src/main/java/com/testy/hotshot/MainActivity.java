package com.testy.hotshot;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    SlideShowAdapater adapater;
    CircleIndicator indicator;
    ProgressBar progressBar;
    int i;
//    VideoView videoView;
    MediaController mediaController;
    String parts[];
    View view;
    int numberOfVideos,videosToDisplay=10;
    TextView nextBtn;
    int deviceWidth,deviceHeight;
    LinearLayout activityMain;
    int numberOfClicks=0,counter=0,count=1;
    String MyDay;
    int  no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        indicator = (CircleIndicator) findViewById(R.id.circle_id);
        setSupportActionBar(toolbar);
        view = findViewById(R.id.relative_id);
        activityMain = (LinearLayout) findViewById(R.id.relative_id);
        nextBtn = (TextView) findViewById(R.id.nextBtn);
        mediaController = new MediaController(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapater = new SlideShowAdapater(this);
        viewPager.setAdapter(adapater);
        indicator.setViewPager(viewPager);
        new MyTask().execute();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        deviceHeight = displayMetrics.widthPixels;
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //printing the date month and year ek saath
//        System.out.println(" "+String.valueOf(date)+" "+String.valueOf(m)+" "+" , "+String.valueOf(y));
//        MyDay = String.valueOf(date)+" "+String.valueOf(m)+" "+" , "+String.valueOf(y);

    }
    public String getDay(){
        //the date
        Random a = new Random();
        int date = a.nextInt((30 - 1) + 1) + 1;
        //the month
        Random b = new Random();
        int month = b.nextInt((12 - 1) + 1) + 1;
        String m = "";
        switch(month){
            case 1:
                m = "January";
                break;
            case 2:
                m = "Feburay";
                break;
            case 3:
                m = "March";
                break;
            case 4:
                m = "April";
                break;
            case 5:
                m = "May";
                break;
            case 6:
                m = "June";
                break;
            case 7:
                m = "July";
                break;
            case 8:
                m = "August";
                break;
            case 9:
                m = "Setptember";
                break;
            case 10:
                m = "October";
                break;
            case 11:
                m = "November";
                break;
            case 12:
                m = "December";
                break;
        }
        //the year
        Random year = new Random();
        int y = year.nextInt((3 - 1) + 1) + 1;
        switch (y){
            case 1:
                y = 2016;
                break;
            case 2:
                y = 2017;
                break;
            case 3:
                y = 2018;
                break;
        }
        MyDay = String.valueOf(date)+" "+String.valueOf(m)+" "+" , "+String.valueOf(y);
        return MyDay;
    }
    public class MyTask extends AsyncTask<String,Void,Void>{
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try{
                if (count == 1 ) {
                    no = parts.length / 8;
                }
                System.out.println(no);
                System.out.println(parts.length);
                System.out.println("first");
                for(i=count; i<=videosToDisplay; i++){
                    if(no == 0){
                        nextBtn.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    System.out.println("Second");
                    if(i==videosToDisplay) {
                        System.out.println("thrird");
                        nextBtn.setText("Next");
                        progressBar.setVisibility(View.INVISIBLE);
                        nextBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                videosToDisplay += 10;
                                count += 10;
                                no -=2;
                                nextBtn.setText
                                        (R.string.loading);
                                progressBar.setVisibility(View.VISIBLE);
                                new MyTask().execute();
                            }
                        });
                    }
                    System.out.println("fourth");
                    LinearLayout ll = new LinearLayout(MainActivity.this);
                    ll.setBackgroundColor(Color.parseColor("#222222"));
                    ll.setPadding(35,35,35,35);
                    ll.setOrientation(LinearLayout.VERTICAL);
                    LinearLayout.LayoutParams llp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, deviceHeight-deviceHeight/3);
                    llp2.topMargin = deviceHeight/8;
                    ll.setLayoutParams(llp2);
                    ImageView imageView = new ImageView(MainActivity.this);
                    TextView textView = new TextView(MainActivity.this);
                    Typeface openSans = Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf");
                    Typeface muli1 = Typeface.createFromAsset(getAssets(),"fonts/Muli-Bold.ttf");
                    Typeface muli2 = Typeface.createFromAsset(getAssets(),"fonts/Muli-Regular.ttf");
                    Typeface muli3 = Typeface.createFromAsset(getAssets(),"fonts/Muli-SemiBold.ttf");
                    textView.setTypeface(muli3);
                    textView.setId(R.id.reservedNamedId);
                    textView.setId(100+1);
                    System.out.println("fifth");
                    String myUrl = "https://teamhotshot.000webhostapp.com/images/"+parts[i];
                    System.out.println("this is your url "+myUrl);
                    LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, deviceHeight/2);
                    LinearLayout.LayoutParams llp3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    String YourDay = getDay();
                    llp3.topMargin = 10;
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    textView.setText(YourDay);
                    textView.setTextSize(25);
                    textView.setTextColor(Color.WHITE);
                    textView.setLayoutParams(llp3);
                    imageView.setId(i+1-1);
                    imageView.setLayoutParams(llp);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String s1 = String.valueOf(view.getId());
                            Intent intent  = new Intent(MainActivity.this,VideoActivity.class);
                            intent.putExtra("value",s1);
                            startActivity(intent);
                        }
                    });

                    Glide.with(getApplicationContext())
                            .load(myUrl)
                            .apply(new RequestOptions().placeholder(R.drawable.a))
                            .into(imageView);
//                    RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//                    //params.addRule(RelativeLayout.ABOVE, R.id.reservedNamedId);
//                    imageView.setLayoutParams(params);
                    ll.addView(imageView);
                    ll.addView(textView);
                    activityMain.addView(ll);

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        protected Void doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection = null;
            try{
                url = new URL("https://teamhotshot.000webhostapp.com/imagefile.php");
                System.out.println("After Url");
                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                String videoname = "";
                while(data != -1){
                    char current = (char) data;
                    data = reader.read();
                    String currentstr = Character.toString(current);
                    videoname += currentstr;
                }
                System.out.println("Video=> "+videoname);
                parts = videoname.split(",");
                System.out.println("Parts=> "+parts);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (urlConnection != null){
                    urlConnection.disconnect();
                }
            }
            return null;
        }
    }
}