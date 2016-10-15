package com.marvel.api.ortal.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.marvel.api.ortal.R;


public class HistoryRecyclerView extends RecyclerView {


    private boolean mHasMoreData;
    private OnLoadMoreListener mListener;

    public HistoryRecyclerView(Context context) {
        this(context, null);
    }

    public HistoryRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HistoryRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void init(LayoutManager layout, Adapter adapter, int scrollLimit) {
        super.setLayoutManager(layout);

        EndlessAdapter endlessAdapter = new EndlessAdapter(adapter, getContext(), R.layout.list_item_loadmore);
        super.setAdapter(endlessAdapter);

        mScrollListener = new EndlessOnScrollListener(layout, scrollLimit) {

            @Override
            public void onLoadMore() {
                if (mListener != null) {
                    mListener.onLoadMore();
                }
            }
        };
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mListener = listener;
    }

    public void setHasMoreData(boolean hasMoreData) {
        mScrollListener.reset();
        if (mHasMoreData == hasMoreData) {
            return;
        }
        mHasMoreData = hasMoreData;
        if (hasMoreData) {
            addOnScrollListener(mScrollListener);
            ((EndlessAdapter) getAdapter()).setKeepOnAppending(true);
        } else {
            removeOnScrollListener(mScrollListener);
            ((EndlessAdapter) getAdapter()).setKeepOnAppending(false);
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

}
