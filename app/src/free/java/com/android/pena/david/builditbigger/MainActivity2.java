package com.android.pena.david.builditbigger;


import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity2 extends AppCompatActivity {

    @BindView(R.id.adView) AdView mAdView;
    @BindView(R.id.joke_btn) Button jokeBtn;
    @BindView(R.id.imageView) ImageView gitView;
    private final String gifUrl= "http://cdn.smosh.com/sites/default/files/legacy.images/smosh-pit/022011/chuck-norris-approves.gif";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle(getResources().getString(R.string.title));
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        ButterKnife.bind(this);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        Glide.with( getApplicationContext() )
                .load( gifUrl )
                .into( gitView );

        jokeBtn.setOnClickListener(v -> new EndpointsAsyncTask(getApplicationContext(),MainActivity2.this).execute());
    }
}
