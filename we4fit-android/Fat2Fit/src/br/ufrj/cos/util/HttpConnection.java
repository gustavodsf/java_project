package br.ufrj.cos.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.BitmapFactory;
import android.net.UrlQuerySanitizer;

public class HttpConnection {
	
	public String getResultString (String url){
		HttpClient client = new DefaultHttpClient();
	    HttpGet request = new HttpGet(url);
	    HttpResponse response;
	    String result = null;
		
	    try {
	        response = client.execute(request);         
	        HttpEntity entity = response.getEntity();

	        if (entity != null) {
	            InputStream instream = entity.getContent();
	            result = convertStreamToString(instream);
	            instream.close();
	        }
	        
	    } catch (ClientProtocolException e1) {
	        e1.printStackTrace();
	    } catch (IOException e1) {
	    	e1.printStackTrace();
	    }
	    return result;
	}
	
	public InputStream getResultInputStream (String url){
		try {
		   URL urlObject = new URL(url);
		   URLConnection conn = urlObject.openConnection();
		   HttpURLConnection httpConn = (HttpURLConnection) conn;
		   httpConn.setAllowUserInteraction(false);
		   httpConn.setInstanceFollowRedirects(true);
		   httpConn.setRequestMethod("GET");
		   httpConn.connect();
	       return httpConn.getInputStream();
		} catch (MalformedURLException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
	private static String convertStreamToString(InputStream is) {

	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
}
