package com.joyfulshark.clipleap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.joyfulshark.clipleap.R;
import com.joyfulshark.clipleap.WizardView.WizardPage;

public class SelectCategoryWizardPage extends WizardPage {

    ImageView _imvCity;
    ImageView _imvCityHighlight;
    ImageView _imvNature;
    ImageView _imvNatureHighlight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.select_category_wizard_page, container, false);
        _imvCity = rootView.findViewById(R.id.imv_city);
        _imvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _imvCityHighlight.setVisibility(View.VISIBLE);
                _imvNatureHighlight.setVisibility(View.INVISIBLE);
            }
        });
        _imvCityHighlight = rootView.findViewById(R.id.imv_city_highlight);
        _imvNature = rootView.findViewById(R.id.imv_nature);
        _imvNature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _imvCityHighlight.setVisibility(View.INVISIBLE);
                _imvNatureHighlight.setVisibility(View.VISIBLE);
            }
        });
        _imvNatureHighlight = rootView.findViewById(R.id.imv_nature_highlight);
        return rootView;
    }

    @Override
    public void setData(Bundle params){}

}
