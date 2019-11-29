package com.joyfulshark.clipleap.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.joyfulshark.clipleap.R;

public class SplashFragment extends Fragment {

    static final int SPLASH_DURATION = 2000;
    SplashFragmentListener _fragmentListener; // listened by MainActivity

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_splash, container, false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                _fragmentListener.onSplashEnded();
            }
        }, SPLASH_DURATION);
        return rootView;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof SplashFragmentListener){
            _fragmentListener = (SplashFragmentListener) context;
        }
    }

}