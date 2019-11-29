package com.joyfulshark.clipleap.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.joyfulshark.clipleap.R;

import java.util.ArrayList;
import java.util.List;

public class SelectedVideosListAdapter extends BaseAdapter {

    Context _context;
    LayoutInflater _layoutInflater;
    List<String> _items;

    public SelectedVideosListAdapter(Context context) {
        _context = context;
        _layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _items = new ArrayList<>();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = _layoutInflater.inflate(R.layout.selected_videos_list_item, parent, false);
        TextView txvVideoFile = itemView.findViewById(R.id.txv_video_file);
        txvVideoFile.setText(_items.get(position));
        return itemView;
    }

    @Override
    public int getCount() {
        return _items.size();
    }

    @Override
    public String getItem(int position) {
        return _items.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public void addItem(String item) {
        _items.add(item);
    }
}