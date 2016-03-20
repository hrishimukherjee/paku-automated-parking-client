package com.example.hreeels.pakuclient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;


public class SplashActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 2500;

    private TextView iSplashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Typeface customFontBold = Typeface.createFromAsset(getAssets(), "bebas_neue_bold.ttf");

        iSplashText = (TextView) findViewById(R.id.splash_title);

        String lText = iSplashText.getText().toString();
        iSplashText.setText(lText.toUpperCase());

        iSplashText.setTypeface(customFontBold);

        /**
         * New Handler to start the Main Activity and
         * close this splash screen after a few seconds.
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
