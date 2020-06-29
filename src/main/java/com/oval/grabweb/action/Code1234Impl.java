package com.oval.grabweb.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import com.oval.grabweb.util.HttpUtils;

public class Code1234Impl implements Paw {

	@Override
	public Page stock(Page page) {
		System.out.println("--------stock------------");
		CloseableHttpClient client = page.getClient();
		System.out.println("client:"+client);
		Response s =HttpUtils.get(client,new Request("http://jsjtyy.com:701/GYSKC.aspx"));
		String res = s.getBody();
		String s1 = StringUtils.substringBetween(res, "id=\"__VIEWSTATE\" value=\"", "\" />");
		String s2 = StringUtils.substringBetween(res,
				"name=\"__EVENTVALIDATION\" id=\"__EVENTVALIDATION\" value=\"", "\" />");
		Map<String, String> params = new HashMap<String, String>();
		params.put("__VIEWSTATE",s1);
		params.put("__EVENTVALIDATION", s2);
		params.put("txtkey", "");
		params.put("btnsearch.x", "24");
		params.put("btnsearch.y","18");
		s=HttpUtils.postForm(client, new Request("http://jsjtyy.com:701/GYSKC.aspx"), params);
		String result = s.getBody();
		int index=result.lastIndexOf("<th>");
		result=result.substring(index);
		String[] rows = result.split("<tr>");
		System.out.println(rows.length);
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> a = new ArrayList<String>();
		a.add("1");
		a.add("2");
		a.add("3");
		list.add(a);
		page.setStock(list);
		return page;
	}

	@Override
	public Page sale(Page page) {
		System.out.println("--------sale------------");
		List<String> list = new ArrayList<String>();
		list.add("2");
		list.add("3");
		list.add("4");
		page.setSale(list);
		return page;
	}

	@Override
	public Page purchase(Page page) {
		System.out.println("--------purchase------------");
		List<String> list = new ArrayList<String>();
		list.add("5");
		list.add("6");
		list.add("7");
		page.setPurchase(list);
		return page;
	}

	@Override
	public void login(Page page) {
		System.out.println("--------login------------");
		CloseableHttpClient client = page.getClient();
		System.out.println("client:"+client);
		try {
			Response s =HttpUtils.get(client,new Request("http://jsjtyy.com:701/default.aspx"));
			String res = s.getBody();
			String s1 = StringUtils.substringBetween(res, "id=\"__VIEWSTATE\" value=\"", "\" />");
			String s2 = StringUtils.substringBetween(res,
					"name=\"__EVENTVALIDATION\" id=\"__EVENTVALIDATION\" value=\"", "\" />");
//			Map<String, String> loginParamsA = new HashMap<String, String>();
//			loginParamsA.put("__VIEWSTATE", s1);
//			loginParamsA.put("__EVENTVALIDATION", s2);
//			loginParamsA.put("uname", "xays");
//			loginParamsA.put("upassword", "515151");
//			loginParamsA.put("butSumbit.x", "28");
//			loginParamsA.put("butSumbit.y", "15");
			String p = "__VIEWSTATE="+s1+"&__EVENTVALIDATION="+s2+"&uname=xays&upassword=515151&butSumbit.x=28&butSumbit.y=15";
			Request re =new Request("http://jsjtyy.com:701/default.aspx");
			re.setBody(p);
			s=HttpUtils.post(client, re);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		page.setLogin(true);
	}

	@Override
	public List<Integer> titleNo() {
		return Arrays.asList(1,1,1);
	}

}
