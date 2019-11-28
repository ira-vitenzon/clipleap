package com.joyfulshark.clipleap.process;

import android.graphics.Bitmap;

import com.joyfulshark.clipleap.common.SceneType;
import com.joyfulshark.clipleap.common.Video;

import java.util.ArrayList;
import java.util.List;

public class MainProcess {

    private static final MainProcess instance = new MainProcess();

    FillScoresIfc fillScores = new FillScoresImpl();
    ExtractSegmentsIfc extractSegments = new ExtractSegmentsImpl();

    public static MainProcess getInstance(){
        return instance;
    }

    public void startProcess(List<Video> videos, final SceneType type){

        for (final Video video : videos){
            fillScores.fillScores(video, new FillScoreListener() {
                @Override
                public void onFillScoreSuccess() {
                    List<Bitmap> resultBitmapList = new ArrayList<>();
                    Video clippedVideo = extractSegments.extractVideoSegments(video, type);
                    resultBitmapList.addAll(clippedVideo.getBitmapList());
                    Video resultVideo = new Video(resultBitmapList);
                }

                @Override
                public void onFillScoreFail() {

                }
            });

        }


    }


}
