package com.marvel.api.ortal.layouts;

import com.marvel.api.ortal.R;
import com.marvel.api.ortal.data.Character;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class DetailsActivity extends AppCompatActivity {

    private static final String FRAGMENT_DETAILS = "details";

    private static final String EXTRA_DATA = "data";

    private Character mCharacter;

    public static Intent createIntent(Character character, Context context) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_DATA, character);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent().hasExtra(EXTRA_DATA)) {
            mCharacter = getIntent().getParcelableExtra(EXTRA_DATA);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_details, DetailsFragment.newInstance(mCharacter),
                        FRAGMENT_DETAILS)
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
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }

}
