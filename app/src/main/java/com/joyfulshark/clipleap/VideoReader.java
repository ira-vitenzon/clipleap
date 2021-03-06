package com.joyfulshark.clipleap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.util.ArrayList;

public class VideoReader {

    // must sample to avoid memory overload
    private static final int SAMPLE_RATE = 20;

    public static ArrayList<Bitmap> readFrames(String videoFolder) {
        ArrayList<Bitmap> bmps = new ArrayList<Bitmap>();

        File folder = new File(videoFolder);
        if (folder == null) {
            return bmps;
        }
        File[] listOfFiles = folder.listFiles();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        for (int i = 0; i < listOfFiles.length; i+=SAMPLE_RATE) {
            File image = listOfFiles[i];
            try {
                Bitmap bmp = BitmapFactory.decodeFile(image.getAbsolutePath());
                bmps.add(bmp);
            } catch (Exception e) {
            }
        }
        return bmps;
    }
}

