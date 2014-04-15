package com.hongxu.ripple.gateway.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hongxu.ripple.gateway.vo.HttpResult;

public class HttpUtils {
	private Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	
	private BasicHttpParams httpParams = null;
	
	public HttpUtils() {
		httpParams = new BasicHttpParams();
		HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(httpParams, HTTP.UTF_8);
		HttpProtocolParams.setUserAgent(httpParams, "HXTM-Ripple-Gateway/1.1");
		httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30 * 1000);
		httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, 30 * 1000);
	}
	
	public HttpResult get(String url) throws ClientProtocolException, IOException{
		HttpResult result = new HttpResult();
		//https版本
		HttpClient client = HttpClientWrapper.wrapClient(new DefaultHttpClient(httpParams));
		//http版本
		//HttpClient client = new DefaultHttpClient(httpParams);
		HttpGet get = new HttpGet(url);
		HttpResponse response=client.execute(get);
		result.setStatusCode(response.getStatusLine().getStatusCode());
		result.setStatusLine(response.getStatusLine().toString());
		result.setReturnData(EntityUtils.toString(response.getEntity(),"utf-8"));
		//logger.debug(result.toString());
		return result;
	}
	
	public HttpResult postJson(String url,String jsonParams) throws IOException, UnsupportedEncodingException
	{
		HttpResult result = new HttpResult();
		//https版本
		HttpClient client = HttpClientWrapper.wrapClient(new DefaultHttpClient(httpParams));
		//http版本
		//HttpClient client = new DefaultHttpClient(httpParams);
		HttpPost post = new HttpPost(url);
		logger.debug(">>>>>>json params:" + jsonParams);
		StringEntity json =new StringEntity(jsonParams,"UTF-8");  
        post.addHeader("content-type", "application/json");  
        post.setEntity(json);  
		HttpResponse response=client.execute(post); 
		result.setStatusCode(response.getStatusLine().getStatusCode());
		result.setStatusLine(response.getStatusLine().toString());
		result.setReturnData(EntityUtils.toString(response.getEntity(),"utf-8"));
		return result;
	}
	
}
