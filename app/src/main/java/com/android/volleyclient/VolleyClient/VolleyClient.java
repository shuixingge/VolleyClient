package com.android.volleyclient.VolleyClient;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volleyclient.MyApplication;
import com.android.volleyclient.Request.GsonRequest;
import com.android.volleyclient.Request.XMLRequest;

import java.util.Map;

/**
 * Created by zhaoruolei1992 on 2016/5/1.
 */
public class VolleyClient {
    /**
     * 静态变量，内部类实现单例模式
     */
    private static VolleyClient mVolleyClient = null;
    private RequestQueue mRequestQueue;
    /**
     * 私有的构造方法
     */

    private VolleyClient(Context context) {
        mRequestQueue = Volley.newRequestQueue(context,null);
    }
    /**
     * 静态内部类
     */
    private static class VolleyManagerHolder {
        private static final VolleyClient INSTANCE = new VolleyClient(MyApplication.getContext());
    }
    /**
     * 获取单例
     */
    public static VolleyClient getInstance() {
        return VolleyManagerHolder.INSTANCE;

    }
    /**
     * 添加String GET请求
     */
    public StringRequest AddStringRequest(Object tag, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(url, listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }
    /**
     * 添加String POST请求
     */
    public StringRequest AddStringRequest(Object tag, int method, String url, Response.Listener<String> listener,
                                    Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(method, url, listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }
    /**
     * 添加Gson GET请求
     */
    public <T> GsonRequest<T> AddGsonGetRequest(Object tag, String url, Class<T> clazz, Response.Listener<T> listener,
                                             Response.ErrorListener errorListener) {
        GsonRequest<T> request = new GsonRequest<T>(url, clazz, listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }
    /**
     * 添加Gson POST请求
     */
    public <T> GsonRequest<T> AddGsonPostRequest(Object tag, Map<String, String> params, String url,
                                              Class<T> clazz, Response.Listener<T> listener,
                                              Response.ErrorListener errorListener) {
        GsonRequest<T> request = new GsonRequest<T>(Request.Method.POST, params, url, clazz, listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }

    /**
     * 添加XML GET请求
     */
    public  XMLRequest AddXMLGetRequest(Object tag, String url,  Response.Listener listener,
                                                Response.ErrorListener errorListener) {
        XMLRequest request = new XMLRequest(url,listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }
    /**
     * 添加XML POST请求
     */
    public  XMLRequest AddXMLPostRequest(Object tag, String url,  Response.Listener listener,
                                        Response.ErrorListener errorListener) {
        XMLRequest request = new XMLRequest(url,listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }

    /**
     * 添加请求到队列
     */
    private <T> Request<T> add(Request<T> request) {
        return mRequestQueue.add(request);
    }
    /**
     * 取消TAG对应的请求
     */
    public void cancel(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
}
