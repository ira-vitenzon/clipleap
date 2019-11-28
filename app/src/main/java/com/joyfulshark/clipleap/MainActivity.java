package com.joyfulshark.clipleap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
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

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.joyfulshark.clipleap.common.ObjectsScores;
import com.joyfulshark.clipleap.common.SceneType;
import com.joyfulshark.clipleap.common.Video;
import com.joyfulshark.clipleap.process.MainProcess;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView _imvTestedImage;
    Bitmap _imgBitmap;
    Button _btnSelectImage;
    Button _btnRunTest;
    String _testedImageFilePath;

    VideoReader _videoReader;
    ImageView _imvLabeledPicture;
    Button _btnSelectPicture;

    final int REQUEST_PICK_FILE = 0;
    TextView _txvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load opencv lib
        if (!OpenCVLoader.initDebug()) {
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, null);
        }

        setContentView(R.layout.activity_main);

        try {
            InputStreamReader is = new InputStreamReader(getAssets().open("objects.csv"));
            ObjectsScores.getInstance().build(is);
            _btnSelectPicture = findViewById(R.id.btn_select_pic);
            _imvLabeledPicture = findViewById(R.id.imv_labeled_pic);
            _btnSelectPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, REQUEST_PICK_FILE);
                }
            });
            _txvResults = findViewById(R.id.txv_results);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_FILE && resultCode == RESULT_OK && data != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());

                List<Bitmap> bitmapList = new ArrayList<>();
                bitmapList.add(bitmap);
                Video video = new Video(bitmapList);
                List<Video> videoList = new ArrayList<>();
                videoList.add(video);
                MainProcess process = MainProcess.getInstance();
                process.startProcess(videoList, SceneType.NATURE);

            } catch (Exception e) {
                Toast.makeText(this, "Failed to open file!", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
