package com.android.pena.david.jokehandler;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import android.widget.TextView;

import com.example.JokeAPI;

import org.json.JSONObject;

public class JokeActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String JOKE_TAG = "joke_tag";
    private String mJoke;
    private TextView jokeText;
    private FloatingActionButton nextJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        setTitle(getResources().getString(R.string.title));
        if(getIntent().getStringExtra(JOKE_TAG) != null){
            mJoke = getIntent().getStringExtra(JOKE_TAG);
            jokeText = (TextView) findViewById(R.id.joke_text);
            nextJoke = (FloatingActionButton) findViewById(R.id.nextJokeBtn);
            jokeText.setText(Html.fromHtml(mJoke));
        }

        nextJoke.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        tellJoke();
    }

    private void tellJoke() {
        new AsyncTask<Void,Void,String>(){

            ProgressDialog progressDialog = new ProgressDialog(JokeActivity.this);
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage(getResources().getString(R.string.loading_joke_msg));
                progressDialog.show();
            }

            @Override
            protected String doInBackground(Void... params) {

                try {
                    JokeAPI jokeAPI = new JokeAPI();
                    JSONObject json = new JSONObject(jokeAPI.getJoke());
                    String joke = json.getJSONObject("value").getString("joke");
                    return joke;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                progressDialog.dismiss();

                if (s != null ) {
                    jokeText.setText(Html.fromHtml(s));
                } else {
                    jokeText.setText(getResources().getString(R.string.error_string));
                }
            }
        }.execute();
    }

}
