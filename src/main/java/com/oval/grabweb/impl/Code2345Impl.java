package com.oval.grabweb.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;

import com.oval.grabweb.component.Page;
import com.oval.grabweb.component.Paw;
import com.oval.grabweb.component.Request;
import com.oval.grabweb.component.Response;
import com.oval.grabweb.util.HttpUtils;

public class Code2345Impl implements Paw {

	@Override
	public Page stock(Page page) {
		System.out.println("--------stock2------------");
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
		System.out.println("--------sale2------------");
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> a = new ArrayList<String>();
		a.add("1");
		a.add("2");
		a.add("3");
		list.add(a);
		page.setSale(list);
		return page;
	}

	@Override
	public Page purchase(Page page) {
		System.out.println("--------purchase2------------");
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> a = new ArrayList<String>();
		a.add("1");
		a.add("2");
		a.add("3");
		list.add(a);
		page.setPurchase(list);
		return page;
	}

	@Override
	public boolean login(Page page) {
		System.out.println("--------login2------------");
		CloseableHttpClient client = page.getClient();
		System.out.println("client:"+client);
		try {
			Response s =HttpUtils.get(client,new Request("https://passport.shaphar.com/cas-server-2019/login?theme=yzs&service=https%3a%2f%2fsupplyapi.shaphar.com%2fapi%2fsph-supply%2fsso%2fredirect-call"));
			String res = s.getBody();
			
			
			String execution = StringUtils.substringBetween(res, "name=\"execution\" value=\"", "\" />");
			Map<String, String> loginParamsA = new HashMap<String, String>();
			Map<String,String> header= new HashMap<String, String>();
			loginParamsA.put("phoneNo", "13828773792");
			loginParamsA.put("rememberMe", "true");
			loginParamsA.put("execution", "34331d56-6a1c-4232-89e6-4f4bc78cca4a_ZXlKaGJHY2lPaUpJVXpVeE1pSjkuazFtaWYxaGlDdCt2MWl2ekxLbTdsWXZXT2tjbjJFRkFXVHFHL0tCMnl3UG5aRkZjQzlOMjBBSGQrbDVvNEdYelhrWmJFOEJlcENFRUE1YTRwMkczR2s5RHBNUEcwUHlZMlV0RWxLV1BzQWhKZ1hWM2dXT21BQ1NkWUp6Um42QXBrNURsOFZiUitrb3ZrSXZhemJseEFmM2JEbXpEbTFSQ3RVdU5VL2I5TjF5M1IxQkVJejcvYWYyOVg5SndsZUpFWnRjSGZPckZ5SlNvZHZQR1BhUkc2VUV2MzdoTUtlS3RHT2pqZ2xWVlVqYWdvQjlnWGV2a3JGTEhFc2phbU83dG80OGtwNWlvdFdPc1dHcjVpZUgrSksrM0NENld0a3JuRW9nclc3WmliVmttNkkydDRsVWU0aFZnTHRJT0ptcEtta0V1WUNpT1hQS1FmQnpSaGRTdjdoUmg0YkZSWnl0VzJPRmNwY2RqSThwbVhQUkRqQjgwd3dpbHdnT2JhZ0hmcVA1SHpvQjZkeHJqcS9CQzZuc0tjRXJrajRzUGtuOGc2dlNEMldtek53ZVhMK1BIVmUweVdmRThIT2FhZk1SSFFIS1NGbStkRUdudDNQSEFXbXpxQ1NqR0xMVjU1T1ZKN1lQOGhka3hzZ2hkT2xIUXhRUG9CQll3Vk5HSmxUMUt1dEE1aEtjc3d4SFZsdFl2N0doQzdjNlFubE12SGc4UVdiK29OMkRjT2dWbHJvMVZnc0FLSWhQRFlJUmdzcVBwa2tuZ3NnS3hqTDdFdG5vWmNEV3E2cE9Za2dCMDBNQXE4NUIxRmZ4VGhIQi9idDAxL1ZPT0RSY3Qvb01SZVNZZE9SbHAzY2pPNERUcXZQYk1BT0plUDdzdEhYVDZZK2hLQUxrNWVrSXlWMTNPQjlLenZlM1h2Y013SUl1NUhMNzhKaHBHQjFEVEpDR1JKUUpRKzRjYTRUbVNUWXhNTDh4MitLT0xiMnpuRVJBUi93eGhaQ2pjZUFGbEdFNzExVzkxQnRtQkMrMHJoWWRWTURVL0ZYQTI3S2ZFMzE4VFZrNk1xRHM3QS95NTlhcEZPLzgwT3YwYUROREdsTHVaQ2tQTDNZM0xnZVl3aDQwS2Y4SWx5enoyaktpeE9sK21SVXJ5eWlLOW5iOVA3NG13VEJJaWo3TU1OZHV3QVB4RFJtWmVza0JEbVVNNE5Edm1wSDZuQlNuWnBobHU1UkRIUmo3dmxReWdra3lFSnR0cVZEZUIwOCtmamRteU5ERE5CVmxUa1lpVVpXWmVPYkNMZ0NJWTNwb0ZSREt5VjRkcWhtVHkwaWlSem5pRWkvd3F5S25XaDFtRWFsb2o1SEV3MXZId2RSYWtJZkNFcXhqY2twdXFsS1BBdFc1ZHkrTURxMXdiRHhTYW5ML3p4dlBVM09BaHpOOWNFT1ZaZHZ1RWwxQU51eVVNVmZsR0RIYng4QkdXTXgzZk51Um9JMnRTY0IvVnRvWEhyN00vWjRDUGZiWkIwcGRVUHdLU1JhZHJneGhwcjVNbUIvZVZRaTR2SEdLZm1HNkdzWGI2ckowT0V4V2F0aEFEVmtSY09QNlpLK3JGTkFVVkNKTUhzWEJ3QkMxTFRMcFM5M1hqZlRocEZSU1g2UTNtWjJDOVVzN25iWEg2QjRMZEJqcGhUelB3amdDUjI1azNueStjelZGSjlIOTR0YW5XM085UmxPQ2pSQ2kyZzlSaXo4cUx1S0plek4rTGoyMndkQjFpY1JLcFczbGpzbDlxdDVoMkNyRW95ZnFkWDY2TTBMeFlweVduUjVITTJ3ekJrYmFzUVlRQ2hORlFRejJaTnVMU01DUUd1VnZCQXNkb0lFK09Oakk2bExsczA0OU9tUTJDNlNpb3hmRXBiOEt6U2RCd1RRWUZ4dHQ3cnkrM01ad2NZNFJQSHRVejdpSW5WRmJGZk9hSzRRSmkwc2paUkcrYitEMTMyNy9OYWhqMjF3b2ZUc01jb0Q0V21IT24xa29OWGtHU29JYkIzUlp0NHY0SGl5ZERTT0JZM0E0ZmFoNUo3S1FBR0hwa1owaVlMcjl0SGhrb3hlNXZNYkhRajRGdGJURE0xSlY1cTdnaEQ2bmhCL0hPSkJLT2JTbnNXRDFjRTU1QXNzYUYrdVVoQU5OUlY2eXZ0N3RzTkord25HdHltY21rT08zTEFWcmV3NGtnSDR5TXNITXVwRUtYK2NlRHY0TnI4ZVg1ZnRyTUpjZ3FjRUV3NE9Zbm94K1d5SDB3ZE1rTlQyT0ZQdW5MU3hPUWhwbC8xc0pSU3ZvWDNmVzlOV3hRKzZ2N0t1c2FwWmQrbDNmY1R1amF3TEo3MWdGOTQ0YVhpeWlMbVpwb05sbmVtbU9kUGlKbEZ6aURsNGx4TE9GS0hmNHk0QnJKY3ZORHpUckdvV09USFpxWFpDdUlGU25uZENJRXpQNkNpMHVSUjB0RzM3a3ZWaXNqZFhuTnZEQU5UT2JlSVZPUHpYc090QlVhRjhnRG1NL1AxQ0Rpc29oK0F4djZINDlqbG9JcmRvY3pwRG9ZdVBTV0lSWlJ5U3VBRlhOdG5GSjArUFNiVW5QVXQyMDNXWjN6S0JpenRXYlFPdm1xTHRoRURKb0ttVHZsakkwSm1XcU1SMS9yTVVrbXdJMzVyRnRjTWE2b0pGZ3hrR0VLTGhYM0JLOVZmbk9TOFk4OHdveENpK0hiaWE2S2FyVnRLNEZkb3BkS0lYS1V1OHF1a0prbnNzR1F3aG5EUWVaUXcwbnh6SGhKR2N5dTNUREhudGlKSENobTdYWkFXdkpqN1BTcERtcGUxeHV5eTdFeGFmektrVkUxQjArdWtVYWprZEFleXc5MEdDQ1JJTnB5UXA5Sm9pQllJTEVkYUxuTkQ5Snl1c21qbmZoSzRhYWNDaS9sL1hXK3R2U042cFRKWE9yeWxubVJCTGlZVFFUWllxbGo1OW1FdEhHcUI4bzFUMzJsaU9iN0N6Zld4UXB1RjdsMy9vVFF6MTFkNzNGYmx2dFAzYmFXcHRXWWJqUDBjcjJBL1Jla1BGL2RqM2JwVzJPaXduVWh4VmU4ZVBDQXZ0NFV4Wi9oNHFUa3g4ZEV3TE5wV1V0bUIrNDFhOFd6bXE2SXFEaHdDUXRQN2hHNHhuMkpDalNHZ2FTUnNNSS9zZGhlcXNXYXVuZFlHc3JvODJ3VkN5QnIwalYwK0U5bUo5RmtDeURGTEd0WFhWNVlRNDFnckFLK3YvU0tJc2VJc0hoMGkzbkgybWFmNGpXZlZVMktFKzJQTEJaWm1JaDBlMnY1TjgxcHhJZWNTU0dvNGUwdlpkdGc1b2c1c0ZwSnlqQUJEWlhQR1FvRkh5UGxwWTBPRGhkMm9CdXR5TzRqZGZpVVQ5KzVueUYvTmJTVGdRaVh4cHZsVW5jVUFrUkV6MkdYTS9wanZZRC85emRZSkpMZ1lxNC9WTDRSU3dDN010aloxSzBJeXBseWQvY0wyZjNIUVNrSW9WRnU3Qk5GdmNhaHdUWDVFTGtrbjNTWkg2cU5icHlOYVlqazV3TXdTSmUzdXB6eWVjT3lSQUQ3WmdjQjFub0JIWmdYUTVqSU9WWXdmOWVRMWdIQjdVeFEzdi9NK1FJYnVBRk9GVXNUVVdiTXJCZEhxdkVhR2VTQ0I3NXNYZnI5d2I0Y2N6ZmpsM3NhQXJhTWtuQkdmSThQT3B1TnZvZTdQQ1l1UTBLZDdEVFgwbUtvSEpRYmtEcG4vTDRmeU8zR2FYM2RrY09zckZHaklNMXZWTEtyQ0k5M3k1cGI1TUo5UjRCclNNOCtFNmVwR2loNzNTbUhrc3RhZmh5a1l4Nno5YSt4SGt0YXl4Ykc2V2N3YzdsUCtNMGJ0eHY3Z0hXY0pUMDM0a0sva3l6cDZLSzVjbm5JT3FRUlRER05Kcm82MndYUXd5V25qcW92UlVGQmtweTdiWFZyLzdob3JmZElzdERjMFEyQzVncDBTQU9FVEZBVHY3QlJDQnQ5bHMxb0tmTXh4QmpUTENhelc3VE94aW9TaVhDTmFEUWkvN09tbWkva3NKV2pUUkt6SWZBdnJpRUpXYXEvZktTRHd6NGFPUC9EOHZ4Y1JvdFJTQi8vbDlkN29ZQkg4ZUlNWEhNYnpJeGcreFNwYTQrRnhoZy9WV2tMRzJiZzJrbkJmSUZCeEJkT3hVeE83d01xUU1RRFA1RjhURE9vL21Ocmk5TDRMb1dMd0Z1V1FXYlhGd1VZZWJ0VkFvd3VQN09yb1dSYWZVYU9HZmNaQitSOTY0bmZjaUl2YTdpcHRNRGxrM3czMkprYzZ6cGRTYkV4dEUxalZZWlZZeXhYSzZubFVDSlVYa3kwZmIyRXpicVRhNGk2UzNIWGEycXd5V0w1ZW11L1dEVDM2RUJPMHhrN1hhc0lKL1lEZ0l5a0RTWCtWODZLbjh6RVd3eEZOQ3VWbzhZclhZRlp6OWlNK3o5WkVyWFhqTzZkVXFESGlzTjc1NThKbjZybVlRWGh5b3JjQzdEaExJYWhKOWtwUENrTTRZblZVSm1GSWZ4RmdhdHVIOGZjc3U4bEhWSUF6UDV6dnJ4dy9oKzkvQUVLYXozMytuRzE2OThlbEI5YVRkaDQrN2k3NGpkZkxsUXRZd2VqYlFhbnozUU4zemFGY0JEVmZFTmMxSlVvMnR2TVo1cURMZllPamdrL0lYZW9oaHZua3dkbHhHQ1VXcFZtRVd6L0wzaW14Q21mcGRyRzBrMytWS2k0Y3VXaFpRdDlMYlBxNTg1bjVYczJOM3J2c0l2WlhBPT0uVmJ1emF2NzlVWkNsVVJBYnBmR0o4dm1zNjJyVl9zSENBTEFqbkxvMUdYUGhoYU5Nb1FORG11ak5PeUd6SWJxSEp4MjZRWjAwNWxWSkZfUVlkdkRMakE=");
			loginParamsA.put("validateCode", "453605");
			loginParamsA.put("_eventId", "submit");
			loginParamsA.put("opType", "phone");
			loginParamsA.put("geolocation", "");
			header.put("Cookie", "JSESSIONID=D7C076B2F7259B023642E738FC5EBD3A.tomcat1; JSESSIONID=77s2fMmGThByV7JNpvTqpv1dvbvxnG1q1PZGXBTwtfr61plnzVtN!2042471102!2037577657");
			header.put("Cache-Control", "max-age=0");
			header.put("Sec-Fetch-Site", "same-origin");
			header.put("Sec-Fetch-Mode", "navigate");
			header.put("Upgrade-Insecure-Requests", "1");
			header.put("Content-Type", "application/x-www-form-urlencoded");
			header.put("Origin", "https://passport.shaphar.com");
			Request re =new Request("https://passport.shaphar.com/cas-server-2019/login?theme=yzs&service=https%3a%2f%2fsupplyapi.shaphar.com%2fapi%2fsph-supply%2fsso%2fredirect-call");
			re.setHeader(header);
			s=HttpUtils.postForm(client, re, loginParamsA);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<Integer> titleNo() {
		return Arrays.asList(0,0,0);
	}

}
