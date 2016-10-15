package com.marvel.api.ortal.layouts;

import com.marvel.api.ortal.R;
import com.marvel.api.ortal.data.DbContract;
import com.marvel.api.ortal.utils.HistoryRecyclerView;
import com.marvel.api.ortal.utils.ListAdapter;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class HistoryFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_ID = 1;

    @Bind(android.R.id.list)
    HistoryRecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;

    private ListAdapter mAdapter;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getSupportLoaderManager().getLoader(LOADER_ID).forceLoad();
    }

    private void initializeView() {

        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new ListAdapter(getActivity(), (CharacterViewHolder.Listener) getActivity());
        mRecyclerView.init(mLayoutManager, mAdapter);
        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        Uri CONTENT_URI = DbContract.Character.CONTENT_URI;
        return new CursorLoader(getActivity(), CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
        if (cursor != null) {
            cursor.moveToFirst();
            List<com.marvel.api.ortal.data.Character> results = new ArrayList<>();
            while (!cursor.isAfterLast()) {
                results.add(new com.marvel.api.ortal.data.Character(
                        cursor.getString(cursor.getColumnIndex(DbContract.CharacterColumns.NAME)),
                        cursor.getString(cursor.getColumnIndex(DbContract.CharacterColumns.THUMBNAIL)),
                        cursor.getString(cursor.getColumnIndex(DbContract.CharacterColumns.DESCRIPTION)),
                        cursor.getInt(cursor.getColumnIndex(DbContract.CharacterColumns._ID))
                ));
                cursor.moveToNext();
            }
            mAdapter.addItems(results);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // If the Cursor is being placed in a CursorAdapter, you should use the
        // swapCursor(null) method to remove any references it has to the
        // Loader's data.
    }
}
