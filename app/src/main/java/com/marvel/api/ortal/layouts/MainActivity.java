package com.marvel.api.ortal.layouts;

import com.marvel.api.ortal.BuildConfig;
import com.marvel.api.ortal.R;
import com.marvel.api.ortal.client.Api;
import com.marvel.api.ortal.client.ApiConfig;
import com.marvel.api.ortal.client.DefaultHttpClient;
import com.marvel.api.ortal.client.RetrofitLogger;
import com.marvel.api.ortal.data.Character;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements CharacterViewHolder.Listener {

    private static final String FRAGMENT_LIST = "list";

    private static final String FRAGMENT_SEARCH = "search";

    private Api mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initApi();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_search, SearchFragment.newInstance(),
                        FRAGMENT_SEARCH)
                .replace(R.id.fragment_list, HistoryFragment.newInstance(),
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
    public void onItemClick(Character character, int position) {
        startActivity(DetailsActivity.createIntent(character, this));
    }

    public Api getApi() {
        return mApi;
    }

    private void initApi() {
        if (mApi == null) {
            ApiConfig cfg = null;
            try {
                cfg = new ApiConfig(getBaseContext());
            } catch (IOException e) {
                e.printStackTrace();
            }
            cfg.setDebug(BuildConfig.DEBUG);
            cfg.setLogger(new RetrofitLogger());
            DefaultHttpClient httpClient = new DefaultHttpClient(this);
            httpClient.setConnectTimeout(30000, TimeUnit.MILLISECONDS);
            httpClient.setReadTimeout(30000, TimeUnit.MILLISECONDS);
            httpClient.interceptors().add(RetrofitLogger.create());
            mApi = new Api(cfg, httpClient);
        }
    }

}
