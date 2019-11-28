package com.joyfulshark.clipleap.process;

import android.graphics.Bitmap;

import com.joyfulshark.clipleap.common.SceneType;
import com.joyfulshark.clipleap.common.Video;

import java.util.ArrayList;
import java.util.List;

public class MainProcess {

    FillScoresIfc fillScores = new FillScoresImpl();
    ExtractSegmentsIfc extractSegments = new ExtractSegmentsImpl();


    public void startProcess(List<Video> videos, SceneType type){

        List<Bitmap> resultBitmapList = new ArrayList<>();
        for (Video video : videos){
            fillScores.fillScores(video);
            Video clippedVideo = extractSegments.extractVideoSegments(video, type);
            resultBitmapList.addAll(clippedVideo.getBitmapList());
        }

        Video resultVideo = new Video(resultBitmapList);
    }


}
