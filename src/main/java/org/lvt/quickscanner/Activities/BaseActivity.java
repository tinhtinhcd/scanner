package org.lvt.quickscanner.Activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.lvt.quickscanner.R;

/**
 * Created by ylt1hc on 6/14/2017.
 */
public class BaseActivity extends AppCompatActivity{

    protected AdView mAdView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        MobileAds.initialize(this, "ca-app-pub-8377756166766379/3512747648");
    }

    protected void loadAdv(){
        mAdView = (AdView) findViewById(R.id.adView);
        if(mAdView!=null){
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
