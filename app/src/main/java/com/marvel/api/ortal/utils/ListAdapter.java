package com.marvel.api.ortal.utils;


import com.marvel.api.ortal.R;
import com.marvel.api.ortal.data.Character;
import com.marvel.api.ortal.layouts.CharacterViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    protected final CharacterViewHolder.Listener mListener;

    protected Context mContext;

    protected List<Character> mCharacters = new ArrayList<>();

    public ListAdapter(Context activity, CharacterViewHolder.Listener listener) {
        super();
        mContext = activity;
        mListener = listener;
    }


    @Override
    public int getItemCount() {
        return items().size();
    }

    protected List<Character> items() {
        return mCharacters;
    }


    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CharacterViewHolder(inflater.inflate(R.layout.list_item, parent, false), mContext, mListener);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        holder.assignItem(mCharacters.get(position), position);
    }


    public void addItems(List<Character> items) {
        mCharacters.clear();
        if (items != null) {
            mCharacters.addAll(items);
        }
        notifyDataSetChanged();
    }


}
