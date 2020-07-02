package com.oval.grabweb.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MapUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.oval.grabweb.component.Request;
import com.oval.grabweb.component.Response;
/**
 * 	HTTP工具类
 * @author eli
 *
 */
public class HttpUtils {

	public static Response get(CloseableHttpClient client, Request request) {
		Response response = new Response();
		HttpGet get = new HttpGet(request.getUrl());
		CloseableHttpResponse res = null;
		HttpEntity entity = null;
		try {
			if (request.getHeader() != null) {
				for (Entry<String, String> en : request.getHeader().entrySet()) {
					get.setHeader(en.getKey(), en.getValue());
				}
			}
			res = client.execute(get);
			entity = res.getEntity();
			response.setBody(EntityUtils.toString(entity));
			int statusCode = res.getStatusLine().getStatusCode();
			if (!(statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_MOVED_TEMPORARILY)) {
				response.setStatusCode(statusCode);
				response.setBody("服务器异常！！");
				return response;
			}
			response.setStatusCode(statusCode);
			Header[] headers = res.getAllHeaders();
			Map<String, String> h = response.getHeader();
			for (Header header : headers) {
				h.put(header.getName(), header.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				EntityUtils.consume(entity);
				if (res != null)
					res.close();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		return response;
	}

	public static Response post(CloseableHttpClient client, Request request) {
		Response response = new Response();
		HttpPost post = new HttpPost(request.getUrl());
		CloseableHttpResponse res = null;
		StringEntity entity = null;
		try {
			if (request.getHeader() != null) {
				for (Entry<String, String> en : request.getHeader().entrySet()) {
					post.setHeader(en.getKey(), en.getValue());
				}
			}
			entity = new StringEntity(request.getBody(), "UTF-8");
			entity.setContentType("Content-Type: application/x-www-form-urlencoded");
			post.setEntity(entity);
			res = client.execute(post);
			response.setBody(EntityUtils.toString(res.getEntity()));
			int statusCode = res.getStatusLine().getStatusCode();
			if (!(statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_MOVED_TEMPORARILY)) {
				response.setStatusCode(statusCode);
				response.setBody("服务器异常！！");
				return response;
			}
			response.setStatusCode(statusCode);
			Header[] headers = res.getAllHeaders();
			Map<String, String> h = response.getHeader();
			for (Header header : headers) {
				h.put(header.getName(), header.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				EntityUtils.consume(entity);
				if (res != null)
					res.close();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		return response;
	}

	public static Response postForm(CloseableHttpClient client, Request request, Map<String, String> params) {
		if (MapUtils.isEmpty(params)) {
			return post(client, request);
		}
		Response response = new Response();
		HttpPost post = new HttpPost(request.getUrl());
		CloseableHttpResponse res = null;
		UrlEncodedFormEntity entity = null;
		try {
			if (!MapUtils.isEmpty(request.getHeader())) {
				for (Entry<String, String> en : request.getHeader().entrySet()) {
					post.setHeader(en.getKey(), en.getValue());
				}
			}
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			for (String paramName : params.keySet()) {
				formparams.add(new BasicNameValuePair(paramName, params.get(paramName)));
			}
			entity = new UrlEncodedFormEntity(formparams, "utf-8");
			post.setEntity(entity);
			res = client.execute(post);
			response.setBody(EntityUtils.toString(res.getEntity()));
			int statusCode = res.getStatusLine().getStatusCode();
			if (!(statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_MOVED_TEMPORARILY)) {
				response.setStatusCode(statusCode);
				response.setBody("服务器异常！！");
				return response;
			}
			response.setStatusCode(statusCode);
			Header[] headers = res.getAllHeaders();
			Map<String, String> h = response.getHeader();
			for (Header header : headers) {
				h.put(header.getName(), header.getValue());
			}
			response.setHeader(h);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				EntityUtils.consume(entity);
				if (res != null)
					res.close();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		return response;
	}

}
