package com.joyfulshark.clipleap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Environment;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.core.Core;
import org.opencv.android.OpenCVLoader;

import java.util.ArrayList;
import java.io.File;

import android.media.MediaMetadataRetriever;

public class MainActivity extends AppCompatActivity {

    ImageView _imvTestedImage;
    Bitmap _imgBitmap;
    Button _btnSelectImage;
    Button _btnRunTest;
    String _testedImageFilePath;

    VideoReader _videoReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load opencv lib
        if (!OpenCVLoader.initDebug()) {
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, null);
        }

        setContentView(R.layout.activity_main);
        _imvTestedImage = findViewById(R.id.imv_tested_image);
        _btnSelectImage = findViewById(R.id.btn_select_image);
        _btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 0);
            }
        });
        _btnRunTest = findViewById(R.id.btn_run_test);
        _btnRunTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boolean isDefective = DefectiveImageDetector.isDefective(_testedImageFilePath);
                boolean isDefective = DefectiveImageDetector.isDefective(_imgBitmap);
                if(isDefective) {
                    Toast.makeText(MainActivity.this, "Image is defective!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Image is OK", Toast.LENGTH_SHORT).show();
                }
            }
        });

        File videoFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "street_2.mp4");
//        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test_vid);
//        ArrayList<Bitmap> frames = VideoReader.readFrames(uri.toString());
//        _imvTestedImage.setImageBitmap(frames.get(0));
        ArrayList<Bitmap> frames = VideoReader.readFrames(videoFile.getPath());
        _imvTestedImage.setImageBitmap(frames.get(0));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            try {
                Uri testedImageUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), testedImageUri);
                _imvTestedImage.setImageBitmap(bitmap);
                _imgBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
//                _testedImageFilePath = testedImageUri.getPath();
//                _testedImageFilePath = "tmp";
            } catch (Exception e) {
                Toast.makeText(this, "Failed to open file!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
