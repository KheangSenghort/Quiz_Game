package com.best.Quiz_App;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.Locale;


public class addPoint extends AppCompatActivity implements RewardedVideoAdListener{

    int point = 5 , rate = 0, share = 0 ;

    Button rateBtn, addsBtn, faceBtn, appsBtn, shareBtn,test,tweet,whatsup;
    boolean AddsDone = true, FaceDone = true , AppsDone = true;

    TextView mLog;
    MediaPlayer player;
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_point);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black_85));
        }

        ImageView imageView = (ImageView)findViewById(R.id.imageView3);

        rateBtn = (Button)findViewById(R.id.rateApp);
        addsBtn = (Button)findViewById(R.id.you);
        faceBtn = (Button)findViewById(R.id.fac);
        appsBtn = (Button)findViewById(R.id.apps);
        shareBtn = (Button)findViewById(R.id.share);
        mLog = (TextView)findViewById(R.id.textView2);
        test = (Button)findViewById(R.id.button2);
        tweet = (Button)findViewById(R.id.tweet);
        whatsup = (Button)findViewById(R.id.whatsup);




        Glide.with(this).load(R.drawable.giphy).into(imageView);


        LoadSating();
        // Set only  times

        if (rate >= 5){
            rateBtn.setEnabled(false);
        }
        if (share >= 5){
            shareBtn.setEnabled(false);
        }

        // reward add

        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        // for test
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",new AdRequest.Builder().build());

        // Real id
//        mRewardedVideoAd.loadAd("ca-app-pub-1974564504308943/1850612222",new AdRequest.Builder().build());



        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//
            }
        });



    }





    public void rate(View view){
        player = MediaPlayer.create(addPoint.this,R.raw.sound_click);
        player.start();
        Uri uri2 = Uri.parse("https://play.google.com/store/apps/com.best.Quiz_App");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri2);
        startActivity(intent);

        // press only 5 times
        rate++;
        if (rate >= 5){
            rateBtn.setEnabled(false);
        }

        rateBtn.setEnabled(false);
        point = point+5;
        SaveSating();

    }
    public void adds(View view) {
        player = MediaPlayer.create(addPoint.this,R.raw.sound_click);
        player.start();

        if (mRewardedVideoAd.isLoaded()){
            mRewardedVideoAd.show();
        }
//        mLog.append(String.format(Locale.getDefault(),"You Received 10 points"));

        point = point+10;
        // for use once
        AddsDone = false;
        addsBtn.setEnabled(true);
        SaveSating();
    }

//    https://goo.gl/uVbgsf


    public void fac(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String myApp = "https://goo.gl/uVbgsf";
        intent.putExtra(Intent.EXTRA_TEXT,myApp);
        intent.setPackage("com.facebook.katana");
        startActivity(intent);

        point = point+5;
        FaceDone = true;
        faceBtn.setEnabled(true);
        SaveSating();
    }

    public void apps(View view) {
        player = MediaPlayer.create(this,R.raw.sound_click);
        player.start();

        Uri uri = Uri.parse("https://goo.gl/uVbgsf");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

        point = point+3;
        AppsDone =false;
        appsBtn.setEnabled(false);
        SaveSating();
    }

    public void tweet(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String myApp2 = "https://goo.gl/uVbgsf";
            intent.putExtra(Intent.EXTRA_TEXT,myApp2);
            intent.setPackage("com.twitter.android");
            startActivity(intent);

        }catch (Exception e){

        }

    }

    public void whats(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String myApp3 = "https://goo.gl/uVbgsf";
        intent.putExtra(Intent.EXTRA_TEXT,myApp3);
        intent.setPackage("com.whatsapp");
        startActivity(intent);

    }


    public void share(View view) {
        player = MediaPlayer.create(this,R.raw.sound_click);
        player.start();

//        share++;
//        if (share >= 5){
//            shareBtn.setEnabled(false);
//        }
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String body = "شارك البرنامج مع أصدقائك للحصول على 1 نقطة وابدأ اللعبة : \n" + "\n" +
                    "https://play.google.com/store/apps/com.best.Quiz_App";
            String sub = "1 نقطة مقابل كل مشاركة للتطبيق \n";
            intent.putExtra(Intent.EXTRA_SUBJECT, sub);
            intent.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(intent, "مشاركة البرنامج"));
            point = point+1 ;
            SaveSating();

        Toast.makeText(this, " شكرا لك  " + " لديك  " + point + " ", Toast.LENGTH_SHORT).show();

    }

      //=================================== Save Setting ==========================================================

    public void SaveSating() {
        SharedPreferences savechange = this.getSharedPreferences("savechange", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = savechange.edit();

        editor.putInt("Point",point);
        editor.putInt("rate", rate);
        editor.putInt("share",share);
        editor.putBoolean("adds" , AddsDone);
        editor.putBoolean("fac" , FaceDone);
        editor.putBoolean("apps" , AppsDone);

        editor.apply();
    }

      //=================================== Load Setting ==========================================================

    public void LoadSating() {
        SharedPreferences savechange = this.getSharedPreferences("savechange", Context.MODE_PRIVATE);

        int Point = savechange.getInt("Point",point);
        point = Point;
        //todo
//        rate = savechange.getInt("rate", rate);
//        share = savechange.getInt("share",share);
//
//        AddsDone = savechange.getBoolean("adds" , AddsDone);
//        addsBtn.setEnabled(AddsDone);
//
//        FaceDone = savechange.getBoolean("fac" , FaceDone);
//        faceBtn.setEnabled(FaceDone);
//
//        AppsDone = savechange.getBoolean("apps" , AppsDone);
//        appsBtn.setEnabled(AppsDone);

    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(this, "Add Load", Toast.LENGTH_SHORT).show();
        mLog.append(String.format(Locale.getDefault(),"You Received 10 points"));

    }

    @Override
    public void  onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {


    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

        mLog.setText("u have the coints noww");

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

}


//============================= Helpful Methods ===================================

// ======================= Intent ======================
// try {
//         Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
//         String[] recipients = new String[]{"e-mail address"};
//         emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
//         emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "E-mail subject");
//         emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "E-mail text");
//         emailIntent.setType("plain/text"); // This is incorrect MIME, but Gmail is one of the only apps that responds to it - this might need to be replaced with text/plain for Facebook
//final PackageManager pm = getPackageManager();
//final List<ResolveInfo> matches = pm.queryIntentActivities(emailIntent, 0);
//        ResolveInfo best = null;
//        for (final ResolveInfo info : matches)
//        if (info.activityInfo.packageName.endsWith(".gm") ||
//        info.activityInfo.name.toLowerCase().contains("gmail")) best = info;
//        if (best != null)
//        emailIntent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
//        startActivity(emailIntent);
//        } catch (Exception e) {
//        Toast.makeText(this, "Application not found", Toast.LENGTH_SHORT).show();
//        }
