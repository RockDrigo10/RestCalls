package com.example.admin.restcalls;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by Admin on 8/16/2017.
 */

public class HttpIntentService extends IntentService{
    public static final String BASE_URL = "http://www.mocky.io/v2/599495951100009403723127";
    private static final String TAG = "HttpIntentService";
    URLConnection urlConnection = null;
    public HttpIntentService() {
        super("HttpIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            URL url = new URL(BASE_URL);
            urlConnection = url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner scanner =  new Scanner(in);
            while (scanner.hasNext()){
                Log.d(TAG, "onHandleIntent: " + scanner.nextLine());
            }
        }catch (MalformedURLException e){}
        catch (java.io.IOException e) {e.printStackTrace();}
    }
}
