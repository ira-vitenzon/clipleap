package com.joyfulshark.clipleap.common;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Video {

    List<Bitmap> bitmapList;
    TreeMap<Float, List<Integer>> scores;

    public Video(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
        this.scores = new TreeMap<>();
    }

    public List<Bitmap> getBitmapList() {
        return bitmapList;
    }

    public TreeMap<Float, List<Integer>> getScores() {
        return scores;
    }

    public Bitmap getFrame(int idx) {
        return bitmapList.get(idx);
    }

    //Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
    public List<Bitmap> getSubVideo(int begin, int end) {
        return bitmapList.subList(begin, end);
    }

    public void addScore(int frameId, float score) {
        if (scores.containsKey(score)) {
            scores.get(score).add(frameId);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(frameId);
            scores.put(score, list);
        }
    }

}
