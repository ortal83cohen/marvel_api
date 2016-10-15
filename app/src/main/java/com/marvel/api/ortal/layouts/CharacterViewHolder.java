package com.marvel.api.ortal.layouts;


import com.marvel.api.ortal.R;
import com.marvel.api.ortal.data.Character;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CharacterViewHolder extends RecyclerView.ViewHolder {

    private static final String IMAGE_SIZE = "/portrait_xlarge.jpg";

    private final Context mContext;

    private final CharacterViewHolder.Listener mListener;

    @Bind(R.id.image)
    public ImageView mImageView;

    @Bind(android.R.id.title)
    public TextView mTitleView;

    private Character mItem;

    private int mPosition;

    public CharacterViewHolder(View v, Context context, CharacterViewHolder.Listener listener) {
        super(v);
        mListener = listener;
        ButterKnife.bind(this, v);

        mContext = context;
    }

    public void assignItem(final Character item, final int position) {
        mPosition = position;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(item, position);
            }
        });
        mItem = item;
        Picasso.with(mContext)
                .load(mItem.thumbnail + IMAGE_SIZE)
                .fit().centerCrop()

                .into(mImageView);

        mTitleView.setText(mItem.name);
    }

    public interface Listener {

        void onItemClick(Character acc, int position);
    }

}