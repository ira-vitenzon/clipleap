package com.joyfulshark.clipleap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.joyfulshark.clipleap.R;
import com.joyfulshark.clipleap.WizardView.WizardPage;
import com.joyfulshark.clipleap.WizardView.WizardView;

import java.util.ArrayList;
import java.util.List;

public class CreateClipWizardFragment extends Fragment {

    WizardView _wizardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_create_clip_wizard, container, false);
        _wizardView = rootView.findViewById(R.id.wizard_view);
        List<WizardPage> pages = new ArrayList<>();
        pages.add(new SelectVideosWizardPage());
        pages.add(new SelectCategoryWizardPage());
        pages.add(new EditProgressWizardPage());
        pages.add(new ClipReadyWizardPage());
        _wizardView.setPages(pages);
        return rootView;
    }

}
