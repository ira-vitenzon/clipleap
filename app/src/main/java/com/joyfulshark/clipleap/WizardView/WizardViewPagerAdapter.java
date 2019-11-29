package com.joyfulshark.clipleap.WizardView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

class WizardViewPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> _items;

    public WizardViewPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        _items = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return _items.get(position);
    }

    @Override
    public int getCount() {
        return _items.size();
    }

    public void setItems(List<WizardPage> pages) {
        for(WizardPage page : pages){
            _items.add(page);
        }
    }
}
