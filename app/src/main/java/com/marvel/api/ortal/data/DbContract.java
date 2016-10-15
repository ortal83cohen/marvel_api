package com.marvel.api.ortal.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;


public class DbContract {

    public static final String CONTENT_AUTHORITY = "com.marvel.api.ortal.provider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String PATH_CHARACTER = "character";


    interface Tables {

        String TABLE_CHARACTER = "character";
    }

    public interface CharacterColumns extends BaseColumns {

        String NAME = "name";
        String TIME = "time";
        String THUMBNAIL = "thumbnail";
        String DESCRIPTION = "description";
    }


    public static class Character implements CharacterColumns, BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CHARACTER).build();

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/int";

        public static Uri buildCharacterUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

}
