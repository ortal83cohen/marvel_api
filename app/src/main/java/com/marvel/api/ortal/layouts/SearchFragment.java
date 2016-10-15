package com.marvel.api.ortal.layouts;

import com.marvel.api.ortal.R;
import com.marvel.api.ortal.client.MarvelResponseBody;
import com.marvel.api.ortal.client.MarvelResults;
import com.marvel.api.ortal.client.RetrofitCallback;
import com.marvel.api.ortal.data.Character;
import com.marvel.api.ortal.data.DbContract;
import com.marvel.api.ortal.utils.Alerts;
import com.squareup.okhttp.ResponseBody;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Response;


public class SearchFragment extends Fragment {

    private static final int RESPONSE_OK = 200;

    @Bind(R.id.edit_query)
    EditText mEditText;

    @Bind(R.id.search_button)
    Button mSearchButton;


    private RetrofitCallback<MarvelResponseBody> mResultsCallback = new RetrofitCallback<MarvelResponseBody>() {
        @Override
        protected void failure(ResponseBody response, boolean isOffline) {
            Alerts.alertError(getActivity());
        }

        @Override
        protected void success(MarvelResponseBody marvelResponseBody, Response<MarvelResponseBody> response) {
            if (marvelResponseBody.code == RESPONSE_OK) {
                if (marvelResponseBody.data.count > 0) {
                    MarvelResults marvelResults = marvelResponseBody.data.results.get(0);
                    getActivity().startActivity(DetailsActivity.createIntent(new Character(marvelResults.name, marvelResults.thumbnail.path, marvelResults.description, 0), getActivity()));
                    insertToDb(marvelResults);
                } else {
                    Alerts.alertEmpty(getActivity());
                }
            } else {
                Alerts.alertError(getActivity());
            }
        }

    };

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    private void insertToDb(MarvelResults marvelResults) {
        ContentValues values = new ContentValues();

        values.put(DbContract.CharacterColumns._ID, marvelResults.id);
        values.put(DbContract.CharacterColumns.NAME, marvelResults.name);
        values.put(DbContract.CharacterColumns.TIME, System.currentTimeMillis() / 1000);
        values.put(DbContract.CharacterColumns.THUMBNAIL, marvelResults.thumbnail.path);
        values.put(DbContract.CharacterColumns.DESCRIPTION, marvelResults.description);

        getActivity().getBaseContext().getContentResolver().insert(DbContract.Character.CONTENT_URI, values);
        getActivity().getBaseContext().getContentResolver().notifyChange(DbContract.Character.CONTENT_URI, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEditText.getText().toString().equals("")) {
                    ((MainActivity) getActivity()).getApi().search(mEditText.getText().toString()).enqueue(mResultsCallback);
                    mEditText.setText("");
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
