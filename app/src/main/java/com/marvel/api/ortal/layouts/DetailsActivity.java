package com.marvel.api.ortal.layouts;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.marvel.api.ortal.BuildConfig;
import com.marvel.api.ortal.R;
import com.marvel.api.ortal.client.Api;
import com.marvel.api.ortal.client.ApiConfig;
import com.marvel.api.ortal.client.DefaultHttpClient;
import com.marvel.api.ortal.client.RetrofitLogger;
import com.marvel.api.ortal.data.Movie;

import java.util.concurrent.TimeUnit;

public class DetailsActivity extends AppCompatActivity implements MovieViewHolder.Listener {
    private static final String FRAGMENT_LIST = "list";
    private static final String FRAGMENT_SEARCH = "search";
    private static final String FRAGMENT_DETAILS = "details";

    private static final String SELECTED_MOVIE = "selected search";

    private int mSelectedMovie;
    private Api mApi; // todo move to application

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initApi();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            mSelectedMovie = savedInstanceState.getInt(SELECTED_MOVIE);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_search, SearchFragment.newInstance(),
                        FRAGMENT_SEARCH)
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_list, ListFragment.newInstance(),
                        FRAGMENT_LIST)
                .commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMovieClick(Movie movie, int position) {

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        mSelectedMovie = search.id;
//        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            fragmentManager.beginTransaction()
//                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
//                    .replace(R.id.fragment_list, DetailsFragment.newInstance(search), FRAGMENT_DETAILS)
//                    .commit();
//        } else {
//            fragmentManager.beginTransaction()
//                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
//                    .replace(R.id.fragment_details, DetailsFragment.newInstance(search), FRAGMENT_DETAILS)
//                    .commit();
//        }
    }

    public Api getApi() {
        return mApi;
    }// todo move to application

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(SELECTED_MOVIE, mSelectedMovie);

    }

    private void initApi() {//todo move to application
        if (mApi == null) {
            ApiConfig cfg = new ApiConfig();
            cfg.setDebug(BuildConfig.DEBUG);
            cfg.setLogger(new RetrofitLogger());
            DefaultHttpClient httpClient = new DefaultHttpClient(this);
            httpClient.setConnectTimeout(30000, TimeUnit.MILLISECONDS);
            httpClient.setReadTimeout(30000, TimeUnit.MILLISECONDS);
            httpClient.interceptors().add(RetrofitLogger.create());
            mApi = new Api(cfg, httpClient);
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_DETAILS);
        if (fragment != null && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mSelectedMovie = 0;
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_list, new ListFragment(), FRAGMENT_LIST)
                    .commit();
        } else {
            super.onBackPressed();
        }
    }
}
