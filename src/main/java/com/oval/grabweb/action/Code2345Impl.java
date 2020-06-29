package com.oval.grabweb.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;

import com.oval.grabweb.util.HttpUtils;

public class Code2345Impl implements Paw {

	@Override
	public Page stock(Page page) {
		System.out.println("--------stock2------------");
		return null;
	}

	@Override
	public Page sale(Page page) {
		System.out.println("--------sale2------------");
		return null;
	}

	@Override
	public Page purchase(Page page) {
		System.out.println("--------purchase2------------");
		return null;
	}

	@Override
	public void login(Page page) {
		System.out.println("--------login2------------");
		CloseableHttpClient client = page.getClient();
		System.out.println("client:"+client);
		try {
			Response s =HttpUtils.get(client,new Request("http://jsjtyy.com:701/default.aspx"));
			String res = s.getBody();
			String s1 = StringUtils.substringBetween(res, "id=\"__VIEWSTATE\" value=\"", "\" />");
			String s2 = StringUtils.substringBetween(res,
					"name=\"__EVENTVALIDATION\" id=\"__EVENTVALIDATION\" value=\"", "\" />");
			Map<String, String> loginParamsA = new HashMap<String, String>();
			loginParamsA.put("__VIEWSTATE", s1);
			loginParamsA.put("__EVENTVALIDATION", s2);
			loginParamsA.put("uname", "xays");
			loginParamsA.put("upassword", "515151");
			loginParamsA.put("butSumbit.x", "28");
			loginParamsA.put("butSumbit.y", "15");
			s=HttpUtils.postForm(client, new Request("http://jsjtyy.com:701/default.aspx"), loginParamsA);
		} catch (Exception e) {
			e.printStackTrace();
		}
		page.setLogin(true);
	}

	@Override
	public List<Integer> titleNo() {
		return Arrays.asList(0,0,0);
	}

}