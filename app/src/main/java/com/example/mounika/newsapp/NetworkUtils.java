package com.example.mounika.newsapp;

/**
 * Created by Mounika on 6/19/2017.
 */
/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.net.Uri;
import android.util.Log;

import com.example.mounika.newsapp.data.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;



import static android.content.ContentValues.TAG;

/**
 * These utilities will be used to communicate with the weather servers.
 */
public class NetworkUtils {
    public static final String TAG = "NetworkUtils";

    public static final String GITHUB_BASE_URL = "https://newsapi.org/v1/articles?";
    public static final String PARAM_QUERY = "source";
    public static final String PARAM_Q = "sortBy";
    public static final String PARAM_API_KEY= "apikey";

    public static final String PA = "the-next-web";
    public static final String PAR = "latest";
    public static final String api = "c51e779ea31f43b6accb7ccb61bf2766";

    public static URL makeURL() {
        Uri uri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY,PA)
                .appendQueryParameter(PARAM_Q,PAR)
                .appendQueryParameter(PARAM_API_KEY,api)
                .build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Url: " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner input = new Scanner(in);

            input.useDelimiter("\\A");

            String result = (input.hasNext()) ? input.next() : null;
            return result;

        }catch (IOException e){
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;
    }

    public static ArrayList<Article> parseJSON(String json) throws JSONException {
        ArrayList<Article> result = new ArrayList<>();
        JSONObject main = new JSONObject(json);
        JSONArray items = main.getJSONArray("articles");

        for(int i = 0; i < items.length(); i++){
            JSONObject item = items.getJSONObject(i);
            String title = item.getString("title");
            String publishedDate = item.getString("publishedAt");
            String abstr = item.getString("description");
            String url = item.getString("url");
            String img = item.getString("urlToImage");

            result.add(new Article(title, publishedDate, abstr, img, url));

        }
        return result;
    }


}
