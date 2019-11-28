package com.joyfulshark.clipleap.process;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionLabelDetector;
import com.joyfulshark.clipleap.common.ObjectsScores;
import com.joyfulshark.clipleap.common.SceneType;
import com.joyfulshark.clipleap.common.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class FillScoresImpl implements FillScoresIfc {


    @Override
    public void fillScores(Video video, FillScoreListener listener) {
        float threshold = 0.5f;

        List<Bitmap> bitmaps = video.getBitmapList();

        int frameJump = 50;
        for (int i = 0; i < bitmaps.size(); i = i + frameJump) {
            calcFrameScore(video, i, listener);
        }
    }


    private void calcFrameScore(Video video, final int i, final FillScoreListener listener) {

        final Map<String, Map<SceneType, Float>> objectsScore = ObjectsScores.getObjectsMap();
        final SortedMap<Float, List<Integer>> cityScores = video.getSortedScores(SceneType.CITY);
        final SortedMap<Float, List<Integer>> natureScores = video.getSortedScores(SceneType.NATURE);


        FirebaseVisionImage fbVisionImage = FirebaseVisionImage.fromBitmap(video.getBitmapList().get(i));
        FirebaseVisionLabelDetector detector = FirebaseVision.getInstance().getVisionLabelDetector();
        detector.detectInImage(fbVisionImage)
                .addOnSuccessListener(
                        new OnSuccessListener<List<FirebaseVisionLabel>>() {
                            float cityScore = 0;
                            float natureScore = 0;
                            @Override
                            public void onSuccess(List<FirebaseVisionLabel> labels) {
                                for(FirebaseVisionLabel labelObj : labels){
                                    String label = labelObj.getLabel().toLowerCase();
                                    Log.d("Label", label);
                                    if (objectsScore.containsKey(label)) {
                                        float labelConfidence = labelObj.getConfidence();
                                        cityScore += labelConfidence * objectsScore.get(label).get(SceneType.CITY);
                                        natureScore += labelConfidence * objectsScore.get(label).get(SceneType.NATURE);
                                    }
                                }
                                addScoreToMap(cityScores, i, cityScore);
                                addScoreToMap(natureScores, i, natureScore);
                                listener.onFillScoreSuccess();

                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                listener.onFillScoreFail();
                                Toast.makeText(null, "Classification failed!", Toast.LENGTH_SHORT).show();
                            }
                        });

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
