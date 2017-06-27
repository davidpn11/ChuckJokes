package com.android.pena.david.builditbigger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;


import com.example.JokesAPI;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.adView) AdView mAdView;
    @BindView(R.id.joke_btn) Button jokeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        jokeBtn.setOnClickListener(v -> tellJoke());
    }

    private void tellJoke(){
        JokesAPI jokesAPI = new JokesAPI();
        Toast.makeText(this, jokesAPI.getJoke(), Toast.LENGTH_SHORT).show();
    }
}
