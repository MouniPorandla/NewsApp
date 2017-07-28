package com.example.mounika.newsapp.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static com.example.mounika.newsapp.data.Contract.TABLE_ARTICLES.COLUMN_NAME_ABSTRACT;
import static com.example.mounika.newsapp.data.Contract.TABLE_ARTICLES.COLUMN_NAME_PUBLISHED_DATE;
import static com.example.mounika.newsapp.data.Contract.TABLE_ARTICLES.COLUMN_NAME_THUMBURL;
import static com.example.mounika.newsapp.data.Contract.TABLE_ARTICLES.COLUMN_NAME_TITLE;
import static com.example.mounika.newsapp.data.Contract.TABLE_ARTICLES.COLUMN_NAME_URL;
import static com.example.mounika.newsapp.data.Contract.TABLE_ARTICLES.TABLE_NAME;

/**
 * Created by mark on 7/27/17.
 */

public class DatabaseUtils {

    public static Cursor getAll(SQLiteDatabase db) {
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                COLUMN_NAME_PUBLISHED_DATE + " DESC"
        );
        return cursor;
    }

    public static void bulkInsert(SQLiteDatabase db, ArrayList<Article> articles) {

        db.beginTransaction();
        try {
            for (Article a : articles) {
                ContentValues cv = new ContentValues();
                cv.put(COLUMN_NAME_TITLE, a.getTitle());
                cv.put(COLUMN_NAME_PUBLISHED_DATE, a.getPublished_date());
                cv.put(COLUMN_NAME_ABSTRACT, a.getAbstr());
                cv.put(COLUMN_NAME_THUMBURL, a.getThumbUrl());
                cv.put(COLUMN_NAME_URL, a.getUrl());
                db.insert(TABLE_NAME, null, cv);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public static void deleteAll(SQLiteDatabase db) {
        db.delete(TABLE_NAME, null, null);
    }

}
