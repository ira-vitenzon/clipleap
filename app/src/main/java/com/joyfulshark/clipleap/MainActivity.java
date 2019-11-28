package com.joyfulshark.clipleap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.joyfulshark.clipleap.common.ObjectsScores;

import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            InputStreamReader is = new InputStreamReader(getAssets().open("objects.csv"));
            ObjectsScores.getInstance().build(is);
            System.out.println(ObjectsScores.getObjectsMap());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
