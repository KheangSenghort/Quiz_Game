package com.best.Quiz_App;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private AdView mAdView;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.green));
        }

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ImageView imageView = (ImageView)findViewById(R.id.imageView2);

        // reward add
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");

//https://stackoverflow.com/questions/3660209/display-animated-gif
        Glide.with(this)
                .load(R.drawable.qq).into(imageView);
    }

    public void start(View view) {
        player = MediaPlayer.create(this,R.raw.sound_click);
        player.start();

        Intent windows_asila = new Intent(this, Game_Activity.class);
        windows_asila.putExtra("rtn",false);
        startActivity(windows_asila);
    }

    public void restart(View view) {
        player= MediaPlayer.create(this,R.raw.sound_click);
        player.start();

        Intent windows_asila = new Intent(this, Game_Activity.class);
        windows_asila.putExtra("rtn",true);
        startActivity(windows_asila);
    }

    public void addpoint(View view) {
        player = MediaPlayer.create(this,R.raw.sound_click);
        player.start();

        Intent addPoin = new Intent(this, addPoint.class);
        startActivity(addPoin);
    }

    public void Share(View view) {
        player= MediaPlayer.create(this,R.raw.sound_click);
        player.start();

        Intent myintent = new Intent(Intent.ACTION_SEND);
        myintent.setType("text/plain");
        String body = "تطبيق  أسئلة وأجوبة جميل للأذكياء  \n" + "\n" +
                "https://play.google.com/store/apps/com.best.Quiz_App";
        String sub = "تطبيق أسئلة ثقافية للأذكياء \n";
        myintent.putExtra(Intent.EXTRA_SUBJECT, sub);
        myintent.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(myintent, "شارك البرنامج"));
    }

    public void about(View view) {
        player = MediaPlayer.create(this,R.raw.sound_click);
        player.start();

        AlertDialog.Builder builder=new AlertDialog.Builder(this) ;
        builder.setTitle("حول البرنامج");
        builder.setMessage("إصدار البرنامج v2.0   \n  التطبيق معاد تصميمه , وبرمجته بالكامل من قبل يمان الخطيب    \n " +
                "للإقتراحات والتواصل  عن طريق البريد الالكتروني: \n " +
                "manoo.sar@gmail.com");
        builder.setPositiveButton("موافق", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        builder.show();
    }

    public void rateus(View view) {

    }
}
