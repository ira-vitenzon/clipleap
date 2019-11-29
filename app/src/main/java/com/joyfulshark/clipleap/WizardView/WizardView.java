package com.joyfulshark.clipleap.WizardView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

import com.joyfulshark.clipleap.R;

import java.util.List;

public class WizardView extends LinearLayout{

    WizardViewPager _pager;
    ImageView _imvPrev;
    ImageView _imvNext;
    WizardViewPagerAdapter _pagerAdapter;

    public WizardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.wizard_view, this);
        initViews(context);
        updateNavigationButtons();
    }

    public void setPages(List<WizardPage> pages){
        _pagerAdapter.setItems(pages);
        _pagerAdapter.notifyDataSetChanged();
        updateNavigationButtons();
    }

    public void prevPage(){
        int currentItem = _pager.getCurrentItem();
        assert(currentItem != 0);
        _pager.setCurrentItem(currentItem - 1);
        updateNavigationButtons();
    }

    public void nextPage(){
        int currPageIndex = _pager.getCurrentItem();
        assert(currPageIndex != _pagerAdapter.getCount() - 1);
        WizardPage currPage = (WizardPage) _pagerAdapter.getItem(currPageIndex);
        WizardPage nextPage = (WizardPage) _pagerAdapter.getItem(currPageIndex + 1);
        nextPage.setData(currPage.getData());
        _pager.setCurrentItem(currPageIndex + 1);
        updateNavigationButtons();
    }

    void updateNavigationButtons(){
        _imvPrev.setVisibility(_pager.getCurrentItem() != 0 ? VISIBLE : GONE);
    }

    void initViews(Context context){
        _pager = findViewById(R.id.pager);
        _imvPrev = findViewById(R.id.btn_prev);
        _imvPrev.setOnClickListener(new OnPrevButtonClickListener());
        _imvNext = findViewById(R.id.btn_next);
        _imvNext.setOnClickListener(new OnNextButtonClickListener());
        _pagerAdapter = new WizardViewPagerAdapter(((FragmentActivity) context).getSupportFragmentManager());
        _pager.setAdapter(_pagerAdapter);
    }

    class OnPrevButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view){
            prevPage();
        }
    }

    class OnNextButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view){
            if(_pager.getCurrentItem() < _pagerAdapter.getCount() - 1){
                nextPage();
            }
            else{
                // clicked "FINISH"
            }
        }
    }

}

