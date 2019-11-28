package com.joyfulshark.clipleap;

import android.graphics.Bitmap;
import org.opencv.android.Utils;
import org.opencv.core.Mat;

public class ImageUtils {

    public static Mat bitmapToMat(Bitmap bmp) {
        Mat mat = new Mat();
        Bitmap bmp32 = bmp.copy(Bitmap.Config.ARGB_8888, true);
        Utils.bitmapToMat(bmp32, mat);
        return mat;
    }

    public static Bitmap matToBitmap(Mat mat) {
        Bitmap bmp32 = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat, bmp32);
        return bmp32;
    }

}
