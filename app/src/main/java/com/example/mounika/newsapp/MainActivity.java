package com.example.mounika.newsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "mainactivty";
    private ProgressBar progress;
    private EditText search;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = (ProgressBar) findViewById(R.id.progressBar);
        search = (EditText) findViewById(R.id.searchQuery);
        textView = (TextView) findViewById(R.id.displayJSON);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemNumber = item.getItemId();

        if (itemNumber == R.id.search) {
//            Toast.makeText(this, "A toast to you!", Toast.LENGTH_LONG).show();
            String s = search.getText().toString();
            NetworkTask task = new NetworkTask(s);
            task.execute();
        }

        return true;
    }

    class NetworkTask extends AsyncTask<URL, Void, String> {
        String query;

        NetworkTask(String s)
        {
            query = s;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params)
        {
            String result = null;
            URL url = NetworkUtils.makeURL(query, "stars");
            Log.d(TAG,"url:" + url.toString());
            try
            {
                result = NetworkUtils.getResponseFromHttpUrl(url);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String val) {
            super.onPostExecute(val);
            progress.setVisibility(View.GONE);
            if (val!= null)
            {
                textView.setText(val);
            }
            else
            {


                textView.setText("Sorry, no text was received");
            }
        }
    }
 }
