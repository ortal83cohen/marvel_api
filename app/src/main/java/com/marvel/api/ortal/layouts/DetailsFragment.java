package com.marvel.api.ortal.layouts;

import com.marvel.api.ortal.R;
import com.marvel.api.ortal.data.Character;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsFragment extends Fragment {

    private static final String EXTRA_DATA = "extra_data";

    private static final String IMAGE_SIZE = "/portrait_xlarge.jpg";

    @Bind(R.id.image)
    public ImageView mImageView;

    @Bind(R.id.name)
    public TextView mName;

    @Bind(R.id.description)
    public TextView mDescription;

    private Character mCharacter;

    public static DetailsFragment newInstance(Character character) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_DATA, character);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mCharacter = arguments.getParcelable(EXTRA_DATA);
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mCharacter != null) {
            Picasso.with(getActivity())
                    .load(mCharacter.thumbnail + IMAGE_SIZE)
                    .fit().centerCrop()

                    .into(mImageView);
            mName.setText(mCharacter.name);

            mDescription.setText(mCharacter.description);
        }
    }
}
