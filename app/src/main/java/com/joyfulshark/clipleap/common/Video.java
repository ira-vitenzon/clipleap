package com.joyfulshark.clipleap.common;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Video {

    List<Bitmap> bitmapList;
    Map<SceneType,SortedMap<Float, List<Integer>>> scores;

    public Video(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
        this.scores = new HashMap<>();
    }

    public List<Bitmap> getBitmapList() {
        return bitmapList;
    }

    public SortedMap<Float, List<Integer>> getSortedScores(SceneType type) {
        return scores.get(type);
    }

    public Bitmap getFrame(int idx) {
        return bitmapList.get(idx);
    }

    //Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
    public Video getSubVideo(int begin, int end) {
        return new Video(bitmapList.subList(begin, end));
    }

    public void addScore(int frameId, float score, SceneType type) {
        if (!scores.containsKey(type)){
            scores.put(type, new TreeMap<Float, List<Integer>>());
        }
        SortedMap<Float, List<Integer>> scoresForType = scores.get(type);
        if (scoresForType.containsKey(score)) {
            scoresForType.get(score).add(frameId);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(frameId);
            scoresForType.put(score, list);
        }
    }

}
