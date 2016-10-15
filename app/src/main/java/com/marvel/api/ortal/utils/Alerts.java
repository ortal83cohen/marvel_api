package com.marvel.api.ortal.utils;

import com.marvel.api.ortal.R;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by ortal on 10/15/2016.
 */

public class Alerts {

    public static void alertEmpty(Context context) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.name_wrong)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static void alertError(Context context) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.error_happened)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
