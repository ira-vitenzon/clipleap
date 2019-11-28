package com.joyfulshark.clipleap.process;

import android.graphics.Bitmap;

import com.joyfulshark.clipleap.common.Video;

import java.util.List;
import java.util.Map;

public class FillScoresImpl implements FillScoresIfc {

    @Override
    public void fillScores(Video video) {
        List<Bitmap> bitmaps = video.getBitmapList();

    }

    @Override
    public Map<String, Float> buildObjScore() {
        return null;
    }

    @Override
    public float calcFrameScore(Bitmap frame) {
        return 0;
    }
}
