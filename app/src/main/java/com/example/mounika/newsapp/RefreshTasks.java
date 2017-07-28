
package com.example.mounika.newsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.mounika.newsapp.NetworkUtils;
import com.example.mounika.newsapp.data.Article;
import com.example.mounika.newsapp.data.DBHelper;
import com.example.mounika.newsapp.data.DatabaseUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class RefreshTasks {

    public static final String ACTION_REFRESH = "refresh";


    public static void refreshArticles(Context context) {
        ArrayList<Article> result = null;

        URL url = NetworkUtils.makeURL();

        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();

        try {
            DatabaseUtils.deleteAll(db);
            String json = NetworkUtils.getResponseFromHttpUrl(url);
            result = NetworkUtils.parseJSON(json);
            DatabaseUtils.bulkInsert(db, result);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        db.close();
    }
}