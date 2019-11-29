package com.joyfulshark.clipleap.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.joyfulshark.clipleap.R;
import com.joyfulshark.clipleap.VideoReader;
import com.joyfulshark.clipleap.WizardView.WizardPage;

import java.util.ArrayList;
import java.util.List;

import com.joyfulshark.clipleap.common.SceneType;
import com.joyfulshark.clipleap.common.Video;
import com.joyfulshark.clipleap.process.MainProcess;

public class EditProgressWizardPage extends WizardPage {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.edit_progress_wizard_page, container, false);
        return rootView;
    }

    @Override
    public void setData(Bundle params){
        boolean isCity = params.getBoolean("isCity");
        Toast.makeText(getContext(), "" + isCity, Toast.LENGTH_SHORT).show();

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/vids/street_1_short";
        ArrayList<Bitmap> bmps = VideoReader.readFrames(path);
        Video video = new Video(bmps);
        List<Video> videoList = new ArrayList<>();
        videoList.add(video);
        MainProcess process = MainProcess.getInstance();
        process.startProcess(videoList, SceneType.NATURE);
    }

}
