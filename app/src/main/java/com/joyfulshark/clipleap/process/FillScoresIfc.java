package com.joyfulshark.clipleap.process;

import android.graphics.Bitmap;

import com.joyfulshark.clipleap.common.Video;

import java.util.Map;

public interface FillScoresIfc {

    void fillScores(Video video);

    Map<String, Float> buildObjScore();

    float calcFrameScore(Bitmap frame);


}
