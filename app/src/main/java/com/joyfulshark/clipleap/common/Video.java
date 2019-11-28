package com.joyfulshark.clipleap.common;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Video {

    List<Bitmap> bitmapList;
    SortedMap<Float, List<Integer>> scores;

    public Video(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
        // Creating a TreeMap with a Custom comparator (Descending order)
        this.scores = new TreeMap<>(new Comparator<Float>() {
            @Override
            public int compare(Float o1, Float o2) {
                return o2.compareTo(o1);
            }
        });
    }

    public List<Bitmap> getBitmapList() {
        return bitmapList;
    }

    public SortedMap<Float, List<Integer>> getSortedScores() {
        return scores;
    }

    public Bitmap getFrame(int idx) {
        return bitmapList.get(idx);
    }

    //Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
    public Video getSubVideo(int begin, int end) {
        return new Video(bitmapList.subList(begin, end));
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
