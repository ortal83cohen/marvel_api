package com.marvel.api.ortal.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


public class HistoryRecyclerView extends RecyclerView {


    public HistoryRecyclerView(Context context) {
        this(context, null);
    }

    public HistoryRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HistoryRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void init(LayoutManager layout, Adapter adapter) {
        super.setLayoutManager(layout);
        super.setAdapter(adapter);

    }

}
