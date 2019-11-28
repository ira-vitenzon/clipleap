package com.joyfulshark.clipleap.process;

import android.graphics.Bitmap;

import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceImageLabelerOptions;
import com.joyfulshark.clipleap.common.ObjectsScores;
import com.joyfulshark.clipleap.common.SceneType;
import com.joyfulshark.clipleap.common.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class FillScoresImpl implements FillScoresIfc {

    @Override
    public void fillScores(Video video) {

        float threshold = 0.5f;
        FirebaseVisionOnDeviceImageLabelerOptions options =
                new FirebaseVisionOnDeviceImageLabelerOptions.Builder()
                        .setConfidenceThreshold(threshold)
                        .build();


        List<Bitmap> bitmaps = video.getBitmapList();

        int frameJump = 50;
        for (int i = 0; i < bitmaps.size(); i = i + frameJump) {
            calcFrameScore(video, i);
        }

    }

    @Override
    public void calcFrameScore(Video video, int i) {

        Map<String, Map<SceneType, Float>> objectsScore = ObjectsScores.getObjectsMap();
        SortedMap<Float, List<Integer>> cityScores = video.getSortedScores(SceneType.CITY);
        SortedMap<Float, List<Integer>> natureScores = video.getSortedScores(SceneType.NATURE);

        float cityScore = 0;
        float natureScore = 0;

        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(video.getBitmapList().get(i));
        FirebaseVisionImageLabeler detector = FirebaseVision.getInstance()
                .getOnDeviceImageLabeler();
        final List<FirebaseVisionImageLabel> result = detector.processImage(image).getResult();

        for (FirebaseVisionImageLabel label : result) {
            if (objectsScore.containsKey(label)) {
                float labelConfidence = label.getConfidence();
                cityScore += labelConfidence * objectsScore.get(label).get(SceneType.CITY);
                natureScore += labelConfidence * objectsScore.get(label).get(SceneType.NATURE);
            }
        }

        addScoreToMap(cityScores, i, cityScore);
        addScoreToMap(natureScores, i, natureScore);
    }

    private void addScoreToMap(SortedMap<Float, List<Integer>> natureScores, int i, float natureScore) {
        if (natureScores.containsKey(natureScore)) {
            natureScores.get(natureScore).add(i);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            natureScores.put(natureScore, list);
        }
    }

}
