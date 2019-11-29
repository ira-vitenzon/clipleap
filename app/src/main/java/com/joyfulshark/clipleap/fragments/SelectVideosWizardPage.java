package com.joyfulshark.clipleap.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.joyfulshark.clipleap.R;
import com.joyfulshark.clipleap.WizardView.WizardPage;

import static android.app.Activity.RESULT_OK;

public class SelectVideosWizardPage extends WizardPage {

    ListView _lsvSelectedVideos;
    SelectedVideosListAdapter _selectedVideosListAdapter;
    ImageView _imvAddVideo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.select_videos_wizard_page, container, false);
        _lsvSelectedVideos = rootView.findViewById(R.id.lsv_selected_videos);
        _selectedVideosListAdapter = new SelectedVideosListAdapter(getContext());
        _lsvSelectedVideos.setAdapter(_selectedVideosListAdapter);
        _imvAddVideo = rootView.findViewById(R.id.btn_add_video);
        _imvAddVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 0);
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            try {
                _selectedVideosListAdapter.addItem(data.getData().toString());
                _selectedVideosListAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                Toast.makeText(getContext(), "Failed to open file!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void setData(Bundle params){

    }

}
