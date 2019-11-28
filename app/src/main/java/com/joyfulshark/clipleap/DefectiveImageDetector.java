package com.joyfulshark.clipleap;

import android.graphics.Bitmap;
import android.widget.Toast;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class DefectiveImageDetector {
    // determines if image is too bright or too dark

    private static final int HIGH_THRESH = 200;
    private static final int LOW_THRESH = 30;

    static boolean isDefective(Bitmap bmp) {
        Mat img = ImageUtils.bitmapToMat(bmp);
        if(img == null) {
            return true;
        }
        return isDarkOrBrightImage(img);
    }

    private static boolean isDarkOrBrightImage(Mat img) {
        double meanColor = computeMeanColor(img);
        if (meanColor > HIGH_THRESH || meanColor < LOW_THRESH) {
            return true;
        }
        return false;
    }

    private static double computeMeanColor(Mat img) {
        Mat grayImage = new Mat(img.rows(), img.cols(), CvType.CV_8U, new Scalar(1));
        Imgproc.cvtColor(img, grayImage, Imgproc.COLOR_RGB2GRAY);

        MatOfDouble meansrc = new MatOfDouble();
        MatOfDouble stdsrc= new MatOfDouble();

        Core.meanStdDev(grayImage, meansrc, stdsrc);

        return meansrc.get(0,0)[0];
    }

}
