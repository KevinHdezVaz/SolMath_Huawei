package com.vazk.calculator.menus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.InterstitialAd;
import com.vazk.calculator.R;
import com.vazk.calculator.adaptadores.CustomAdapter;
import com.vazk.calculator.adaptadores.GridView_adapter;
import com.vazk.calculator.symja.activities.DerivativeActivity;
import com.vazk.calculator.symja.activities.IntegrateActivity;
import com.vazk.calculator.symja.activities.LimitActivity;
import com.vazk.calculator.symja.activities.PermutationActivity;
import com.vazk.calculator.symja.activities.PrimitiveActivity;

import java.util.ArrayList;

public class MenuEstadistica extends AppCompatActivity {
    protected final Handler handler = new Handler();
    private InterstitialAd interstitialAd;
    private CustomAdapter adaptador;
    private GridView grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_estadistica);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        toolbar.setNavigationOnClickListener(v -> {

            finish();
        });


        interstitialAd = new InterstitialAd(this);
        // "testb4znbuh3n2" is a dedicated test ad unit ID. Before releasing your app, replace the test ad unit ID with the formal one.
        interstitialAd.setAdId("c2efjnhe28");
        loadInterstitialAd();

        interstitialAd.setAdListener(adListener);



        grid = (GridView)  findViewById(R.id.item);

        final ArrayList<GridView_adapter> listItems = new ArrayList<>();
        listItems.add(new GridView_adapter(R.drawable.ceta,getString(R.string.combination)));
        listItems.add(new GridView_adapter((R.drawable.pags), getString(R.string.permutation)));






        adaptador = new CustomAdapter(this, listItems);
        grid.setAdapter(adaptador);

        grid.setOnItemClickListener((adapterView, view, i, l) -> {

            if(i == 0 ){
               Intent  intent = new Intent(getApplicationContext(), PermutationActivity.class);
                intent.putExtra(PermutationActivity.TYPE_NUMBER, PermutationActivity.TYPE_COMBINATION);
                postStartActivity(intent);
            }
            if(i == 1 ){
                Intent intent = new Intent(getApplicationContext(), PermutationActivity.class);
                intent.putExtra(PermutationActivity.TYPE_NUMBER, PermutationActivity.TYPE_PERMUTATION);
                postStartActivity(intent);
            }


        });


    }   private void postStartActivity(final Intent intent) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, 100);
    }private void loadInterstitialAd() {

        // Load an interstitial ad.
        AdParam adParam = new AdParam.Builder().build();
        interstitialAd.loadAd(adParam);

    }private void showInterstitialAd() {
        // Display the ad.
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show(this);
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }private AdListener adListener = new AdListener() {
        @Override
        public void onAdLoaded() {
            // Called when an ad is loaded successfully.

            showInterstitialAd();
        }
        @Override
        public void onAdFailed(int errorCode) {
            // Called when an ad fails to be loaded.

        }
        @Override
        public void onAdClosed() {
            // Called when an ad is closed.

        }
        @Override
        public void onAdClicked() {
            // Called when an ad is clicked.

        }
        @Override
        public void onAdLeave() {
            // Called when an ad leaves an app.

        }
        @Override
        public void onAdOpened() {
            // Called when an ad is opened.

        }
        @Override
        public void onAdImpression() {
            // Called when an ad impression occurs.
        }
    };

}