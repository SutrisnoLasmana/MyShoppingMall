package com.example.multimatics.myshoppingmall;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;

/**
 * Created by Multimatics on 22/07/2016.
 */
public class CustomOnItemClickListener implements View.OnClickListener {
    private OnItemClickCallback onItemClickCallback;
    private int position;

    public CustomOnItemClickListener(int position, OnItemClickCallback onItemClickCallback) {
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onClick(View v) {
        onItemClickCallback.onItemClicked(v,position);
    }

    public interface OnItemClickCallback{
        void onItemClicked(View view, int position);
    }
}
