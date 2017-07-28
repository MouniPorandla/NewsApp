package com.example.mounika.newsapp.data;

import android.provider.BaseColumns;

/**
 * Created by Mounika on 7/26/2017.
 */

public class Contract {
    //3. Create a contract, subclass SQLiteOpenHelper, modify your app so that your network call
    // stores the data for your news stories in the database (you decide column and table names
//Intializing the table with it column names
    public static class TABLE_ARTICLES implements BaseColumns {
        public static final String TABLE_NAME = "articles";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PUBLISHED_DATE = "published_At";
        public static final String COLUMN_NAME_ABSTRACT = "description";
        public static final String COLUMN_NAME_THUMBURL = "urlToImage";
        public static final String COLUMN_NAME_URL = "url";
    }
}