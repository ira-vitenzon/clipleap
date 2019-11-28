package com.joyfulshark.clipleap;

import android.graphics.Bitmap;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class VideoReader {

    public static ArrayList<Bitmap> readFrames(String videoFilePath) {
        Mat matOrig = new Mat();
        VideoCapture capture = new VideoCapture(videoFilePath);
        Bitmap currFrame = null;
        ArrayList<Bitmap> frames = new ArrayList<>();
        if (capture.isOpened()) {
            while (true) {
                capture.read(matOrig);
                // get some meta data about frame
                double fps = capture.get(Videoio.CAP_PROP_FPS);
                double frameCount = capture.get(Videoio.CAP_PROP_FRAME_COUNT);
                double h = capture.get(Videoio.CAP_PROP_FRAME_HEIGHT);
                double w = capture.get(Videoio.CAP_PROP_FRAME_WIDTH);
                double posFrames = capture.get(Videoio.CAP_PROP_POS_FRAMES);
                double posMsec = capture.get(Videoio.CAP_PROP_POS_MSEC);
                double speed = capture.get(Videoio.CAP_PROP_SPEED);
                // read frame
                if (!matOrig.empty()) {
                    currFrame = ImageUtils.matToBitmap(matOrig);
                    if (currFrame != null) {
                        frames.add(currFrame);
                    }
                }
                break;
            }
        }
        return frames;
    }
}

