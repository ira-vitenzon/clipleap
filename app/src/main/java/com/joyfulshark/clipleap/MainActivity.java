package com.joyfulshark.clipleap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.joyfulshark.clipleap.fragments.CreateClipWizardFragment;
import com.joyfulshark.clipleap.fragments.SplashFragment;
import com.joyfulshark.clipleap.fragments.SplashFragmentListener;

public class MainActivity extends AppCompatActivity implements SplashFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }

        setContentView(R.layout.activity_main);
        showSplash();
    }

    void showSplash() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_main, new SplashFragment())
                .commit();
    }


    @Override
    public void onSplashEnded() {
        CreateClipWizardFragment createClipWizardFragment = new CreateClipWizardFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.lay_main, createClipWizardFragment)
                .commit();
   }
}
