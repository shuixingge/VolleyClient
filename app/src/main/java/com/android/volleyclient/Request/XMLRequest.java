package com.android.volleyclient.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by zhaoruolei1992 on 2016/5/1.
 */
public class XMLRequest extends Request<XmlPullParser> {

    private final Response.Listener<XmlPullParser> mListener;
    private Map<String, String> mParams;//post Params
    public XMLRequest (int method, Map<String, String> params, String url,  Response.Listener<XmlPullParser> listener,
                       Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
        mParams = params;
        setMyRetryPolicy();
    }
    public XMLRequest(String url, Response.Listener<XmlPullParser> listener, Response.ErrorListener errorListener) {
        this(Method.GET, null,url, listener, errorListener);
    }
    private void setMyRetryPolicy() {
        setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }
    @Override
    protected Response<XmlPullParser> parseNetworkResponse(NetworkResponse response) {
        try {
            String xmlString = new String(response.data,
                   HttpHeaderParser.parseCharset(response.headers));
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlString));
            return Response.success(xmlPullParser, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (XmlPullParserException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(XmlPullParser response) {
        mListener.onResponse(response);
    }

}
