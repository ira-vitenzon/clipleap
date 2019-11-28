package com.joyfulshark.clipleap.process;

import android.graphics.Bitmap;

import com.joyfulshark.clipleap.common.SceneType;
import com.joyfulshark.clipleap.common.Video;

import java.util.List;
import java.util.SortedMap;

public class ExtractSegmentsImpl implements  ExtractSegmentsIfc{

    int halfClipLength = 60 * 4; // frames per second * half clip length in seconds

    public Video extractVideoSegments(Video video, SceneType type){

        SortedMap<Float, List<Integer>> scores = video.getSortedScores(type);
        Float maxKey = scores.lastKey();
        List<Integer> frameIdxs = scores.get(maxKey);
        Integer maxIdx = frameIdxs.get(0);

        int startIdx = -1;
        for (int i = maxIdx; i > maxIdx - halfClipLength - 1; i--) {
            Bitmap frame = video.getFrame(i);
            if (frame == null){
                startIdx = i + 1;
            }
        }
        if (startIdx == -1){
            startIdx = maxIdx - halfClipLength;
        }

        int endIdx = -1;
        for (int i = maxIdx; i < maxIdx + halfClipLength + 1; i++) {
            Bitmap frame = video.getFrame(i);
            if (frame == null){
                endIdx = i - 1;
            }
        }
        if (endIdx == -1){
            endIdx = maxIdx + halfClipLength;
        }

        return video.getSubVideo(startIdx, endIdx + 1);
    }
}
