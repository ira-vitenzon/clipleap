package com.joyfulshark.clipleap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joyfulshark.clipleap.R;
import com.joyfulshark.clipleap.WizardView.WizardPage;

public class EditProgressWizardPage extends WizardPage {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.edit_progress_wizard_page, container, false);
        return rootView;
    }

    @Override
    public void setData(Bundle params){}

}
