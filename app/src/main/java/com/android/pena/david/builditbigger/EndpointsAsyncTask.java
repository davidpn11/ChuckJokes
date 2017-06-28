package com.android.pena.david.builditbigger;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.android.pena.david.jokehandler.JokeActivity;
import com.example.david.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import org.json.JSONObject;

import java.io.IOException;

import timber.log.Timber;

/**
 * Created by david on 27/06/17.
 */

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    public static String jokeText;
    private static final String JOKE_TAG = "joke_tag";
    private MyApi myApiService = null;
    private ProgressDialog progressDialog;
    private static final String BASE_URL = "http://10.0.3.2:8080/_ah/api/";
    private Context mContext;

    public EndpointsAsyncTask(Context context,Activity act){
        mContext = context;
        progressDialog = new ProgressDialog(act);
    }


    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        progressDialog.setMessage(mContext.getResources().getString(R.string.loading_joke_msg));
        progressDialog.show();

    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(BASE_URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        try {
            JSONObject json = new JSONObject(myApiService.getJokeFromAPI().execute().getData());
            String joke = json.getJSONObject("value").getString("joke");
            return joke;
        } catch (IOException e) {
            return mContext.getResources().getString(R.string.error_timeout);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        progressDialog.dismiss();
        jokeText = result;
        if(result != null && !result.isEmpty()){
            //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Timber.d(result);
            Intent it = new Intent(mContext, JokeActivity.class);
            it.putExtra(JOKE_TAG, result);
            mContext.startActivity(it);
        }
    }
}