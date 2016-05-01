package com.android.volleyclient.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volleyclient.Bean.Person;
import com.android.volleyclient.Constant.Urls;
import com.android.volleyclient.R;
import com.android.volleyclient.VolleyClient.VolleyClient;

import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VolleyClient.getInstance().AddGsonGetRequest(TAG, Urls.mJsonUrl, Person.class,
                new Response.Listener<Person>() {
                    @Override
                    public void onResponse(Person person) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        VolleyClient.getInstance().AddXMLGetRequest(TAG, Urls.mXMLUrl, new Response.Listener<XmlPullParser>() {
            @Override
            public void onResponse(XmlPullParser parser) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        /**
         * 取消请求
         */
        if (VolleyClient.getInstance() != null) {
            VolleyClient.getInstance().cancel(TAG);
        }

    }
}
