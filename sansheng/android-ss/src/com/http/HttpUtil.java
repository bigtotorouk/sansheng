package com.http;

import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpUtil {

	// response state is sucess
	public final static int CODE_SUCESS = 200;
	// response statie is fail
	public final static int CODE_FAIL = 400;
	public final static int CODE_NULL = 204;

	public static String UsreId = null;

	public static String Token = null;

	static HttpClient client;

	// public static HttpCommonResponse get(String url, JSONObject params) {
	// String body = null;
	// HttpCommonResponse comResponse = null;
	// try {
	// HttpGet httpGet = null;
	// String paramsStr = "";
	// if (params != null) {
	// httpGet = new HttpGet(url + jsonToUrl(params));
	// httpGet.addHeader("Content-Type", "text/html; charset=utf-8");
	// }
	//
	// System.out.println("url:" + httpGet.getURI());
	// HttpResponse httpResponse = client.execute(httpGet);
	// comResponse = new HttpCommonResponse();
	// int state = httpResponse.getStatusLine().getStatusCode();
	// comResponse.setStateCode(state);
	// if (state != CODE_NULL) {
	// String response = EntityUtils
	// .toString(httpResponse.getEntity());
	// comResponse.setResponse(response);
	// HttpEntity httpEntity = httpResponse.getEntity();
	// System.err.println("body:" + response);
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return comResponse;
	// }

	public static String jsonToUrl(JSONObject json) {
		String url = "?";
		Iterator<String> nameStr = json.keys();
		while (nameStr.hasNext()) {
			String p = nameStr.next().toString();
			System.out.print(p);
			try {
				url += p + "=" + json.getString(p) + "&";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		url = url.substring(0, url.length() - 1);
		return url;
	}

	// public static HttpCommonResponse post(String url, JSONObject params) {
	// String response = null;
	// HttpCommonResponse comResponse = new HttpCommonResponse();
	// System.out.println("post url:" + url);
	// System.out.println("params:" + params);
	// try {
	// HttpPost post = new HttpPost(url);
	// if (params != null) {
	// StringEntity strEntity = new StringEntity(params.toString(),
	// "utf-8");
	// post.setEntity(strEntity);
	// }
	// HttpResponse httpResponse = client.execute(post);
	// int stateCode = httpResponse.getStatusLine().getStatusCode();
	// if (httpResponse.getEntity() != null) {
	// response = EntityUtils.toString(httpResponse.getEntity());
	// comResponse.setStateCode(stateCode);
	// comResponse.setResponse(response);
	// System.err.println("" + comResponse);
	// }
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return comResponse;
	// }

	public static HttpCommonResponse request(String url,
			List<NameValuePair> params) {
		HttpCommonResponse resp = new HttpCommonResponse();

		String body = null;
		try {
			HttpClient client = HttpUtil.getHttpClient();
			if (params != null) {
				String paramsStr = EntityUtils
						.toString(new UrlEncodedFormEntity(params));
				url += paramsStr;
			}
			System.out.println("request:" + url);

			HttpGet get = new HttpGet(url);
			HttpResponse response = client.execute(get);
			body = EntityUtils.toString(response.getEntity());
			resp.setResponse(body);
			System.out.println("body:" + body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	public static HttpCommonResponse post(String url,Map<String, String> params) {
		HttpCommonResponse resp = new HttpCommonResponse();
		List<NameValuePair>nvParams=toNameValueParams(params);
		String body = null;
		try {
			HttpClient client = HttpUtil.getHttpClient();
			HttpPost post = new HttpPost(url);
			if (params != null) {
				post.setEntity(new UrlEncodedFormEntity(nvParams, "UTF-8"));
			}
			HttpResponse response = client.execute(post);
			body = EntityUtils.toString(response.getEntity(), "utf-8");
			resp.setResponse(body);
			resp.setStateCode(response.getStatusLine().getStatusCode());

			System.out.println("body:" + body);
		} catch (SocketException e) {
			e.printStackTrace();
			resp.setStateCode(545);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStateCode(544);
		}
		return resp;

	}
	
	public  static  List<NameValuePair>   toNameValueParams(Map<String, String>  params){
		List<NameValuePair>  nvs=new  ArrayList<NameValuePair>();
		for (String key : params.keySet()) {
			NameValuePair  nv=new  BasicNameValuePair(key, params.get(key));
			nvs.add(nv);
		}
		return  nvs;
	}

	public static HttpCommonResponse get(String url, List<NameValuePair> params) {
		HttpCommonResponse resp = new HttpCommonResponse();
		String body = null;
		HttpClient client = HttpUtil.getHttpClient();
		try {
			HttpGet get;
			if (params == null) {
				get = new HttpGet(url);
			} else {
				String paramsStr = EntityUtils
						.toString(new UrlEncodedFormEntity(params));
				get = new HttpGet(url + paramsStr);
			}
			BaseNetService.httpRequestBases.put(url, get);
			HttpResponse response = client.execute(get);
			body = EntityUtils.toString(response.getEntity(), "utf-8");
			resp.setResponse(body);
			resp.setStateCode(response.getStatusLine().getStatusCode());
			System.out.println("body:" + body);
			BaseNetService.httpRequestBases.remove(url);
		} catch (SocketException e) {
			e.printStackTrace();
			resp.setStateCode(545);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStateCode(544);
		}
		return resp;
	}

	/**
	 * send a HTTP get request.
	 * 
	 * @param url
	 * @param params
	 *            parameters put in request header(AppCode,SecretKey)
	 * @return
	 */
	public static HttpCommonResponse doGet(String url,
			Map<String, String> params) {
		HttpCommonResponse resp = new HttpCommonResponse();
		String body = null;
		HttpClient client = HttpUtil.getHttpClient();
		try {
			String u = getUrlWithParams(url, params);
			System.out.println("url:" + u);
			HttpGet get = new HttpGet(u);

			BaseNetService.httpRequestBases.put(url, get);
			HttpResponse response = client.execute(get);
			body = EntityUtils.toString(response.getEntity(), "utf-8");
			resp.setResponse(body);
			resp.setStateCode(response.getStatusLine().getStatusCode());
			// Log.i("doGet", "statue:" + resp.getStateCode());
			System.out.println("body:" + body);
			BaseNetService.httpRequestBases.remove(url);
		} catch (SocketException e) {
			resp.setStateCode(545);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStateCode(544);
		}

		return resp;
	}

	public static String getUrlWithParams(String url, Map<String, String> params) {
		String u = url;
		for (String key : params.keySet()) {
			url += "&" + key + "=" + params.get(key);
		}
		return url;
	}

	/**
	 * send a HTTP post request.
	 * 
	 * @param url
	 * @param params
	 *            parameters needed and will be formated as json
	 * @return
	 */
	public static HttpCommonResponse doPost(String url,
			Map<String, String> params) {
		HttpCommonResponse resp = new HttpCommonResponse();
		String body = null;
		HttpClient client = HttpUtil.getHttpClient();
		try {
			HttpPost post = new HttpPost(url);
			post.setHeader("Content-Type", "application/json");
			JSONObject json = new JSONObject();
			if (null != params) {
				for (String key : params.keySet()) {
					json.put(key, params.get(key));
				}
				post.setEntity(new StringEntity(json.toString(), "UTF-8"));
			}
			BaseNetService.httpRequestBases.put(url, post);
			HttpResponse response = client.execute(post);
			body = EntityUtils.toString(response.getEntity(), "utf-8");
			resp.setResponse(body);
			resp.setStateCode(response.getStatusLine().getStatusCode());
			BaseNetService.httpRequestBases.remove(url);
		} catch (SocketException e) {
			e.printStackTrace();
			resp.setStateCode(545);
		} catch (Exception e) {
			resp.setStateCode(544);
		}
		return resp;
	}

	/**
	 * send a HTTP put request.
	 * 
	 * @param url
	 * @param params
	 *            parameters needed
	 * @return
	 */
	public static HttpCommonResponse doPut(String url,
			Map<String, String> params) {
		HttpCommonResponse resp = new HttpCommonResponse();
		String body = null;
		HttpClient client = HttpUtil.getHttpClient();
		try {
			HttpPut put = new HttpPut(url);
			if (null != params) {
				put.setEntity(new UrlEncodedFormEntity(HttpUtil
						.map2List(params), "UTF-8"));
			}
			BaseNetService.httpRequestBases.put(url, put);
			HttpResponse response = client.execute(put);
			body = EntityUtils.toString(response.getEntity(), "utf-8");
			resp.setResponse(body);
			resp.setStateCode(response.getStatusLine().getStatusCode());
			BaseNetService.httpRequestBases.remove(url);
		} catch (SocketException e) {
			e.printStackTrace();
			resp.setStateCode(545);
		} catch (Exception e) {
			resp.setStateCode(544);
		}
		return resp;
	}

	/**
	 * send a HTTP put request.
	 * 
	 * @param url
	 * @param params
	 *            parameters needed and will be formated as json
	 * @return
	 */
	public static HttpCommonResponse doPutWithJsonBody(String url,
			Map<String, String> params) {
		HttpCommonResponse resp = new HttpCommonResponse();
		String body = null;
		HttpClient client = HttpUtil.getHttpClient();
		try {
			HttpPut put = new HttpPut(url);
			put.setHeader("Content-Type", "application/json");
			JSONObject json = new JSONObject();
			if (null != params) {
				for (String key : params.keySet()) {
					json.put(key, params.get(key));
				}
				put.setEntity(new StringEntity(json.toString(), "UTF-8"));
			}
			BaseNetService.httpRequestBases.put(url, put);
			HttpResponse response = client.execute(put);
			body = EntityUtils.toString(response.getEntity(), "utf-8");
			resp.setResponse(body);
			resp.setStateCode(response.getStatusLine().getStatusCode());
			BaseNetService.httpRequestBases.remove(url);
		} catch (SocketException e) {
			e.printStackTrace();
			resp.setStateCode(545);
		} catch (Exception e) {
			resp.setStateCode(544);
		}
		return resp;
	}

	/**
	 * send a HTTP delete request.
	 * 
	 * @param url
	 * @param params
	 *            parameters put in request header(AppCode,SecretKey)
	 * @return
	 */
	public static HttpCommonResponse doDelete(String url,
			Map<String, String> params) {
		HttpCommonResponse resp = new HttpCommonResponse();
		String body = null;
		HttpClient client = HttpUtil.getHttpClient();
		try {
			HttpDelete delete = new HttpDelete(url);
			for (String key : params.keySet()) {
				delete.setHeader(key, params.get(key));
			}
			BaseNetService.httpRequestBases.put(url, delete);
			HttpResponse response = client.execute(delete);
			body = EntityUtils.toString(response.getEntity(), "utf-8");
			resp.setResponse(body);
			resp.setStateCode(response.getStatusLine().getStatusCode());
			BaseNetService.httpRequestBases.remove(url);
		} catch (SocketException e) {
			e.printStackTrace();
			resp.setStateCode(545);
		} catch (Exception e) {
			resp.setStateCode(544);
		}
		return resp;
	}

	public static String get(HttpClient httpClient, String url,
			List<NameValuePair> params) {
		String body = null;
		try {
			String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(
					params));

			HttpGet httpGet = new HttpGet(url + paramsStr);

			System.out.println("paramsStr:" + paramsStr);
			System.out.println("url:" + httpGet.getURI());
			HttpResponse httpResponse = client.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			body = EntityUtils.toString(httpEntity);
			System.out.println("body:" + body);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}

	public static HttpClient createHttpClient() {
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
		HttpProtocolParams.setUseExpectContinue(params, true);
		HttpConnectionParams.setConnectionTimeout(params, 30000);
		HttpConnectionParams.setSoTimeout(params, 30000);

		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schReg.register(new Scheme("https",
				SSLSocketFactory.getSocketFactory(), 443));

		ThreadSafeClientConnManager connMgr = new ThreadSafeClientConnManager(
				params, schReg);
		return new DefaultHttpClient(connMgr, params);
	}

	private String iso88592utf8(String content) {
		String uContent = "";
		try {
			uContent = new String(content.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return uContent;
	}

	public static List<NameValuePair> map2List(Map<String, String> params) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : params.keySet()) {
			NameValuePair nameValuePair = new BasicNameValuePair(key,
					params.get(key));
			nameValuePairs.add(nameValuePair);
		}

		return nameValuePairs;
	}

	public static void shutdownclient() {
		if (client != null && client.getConnectionManager() != null) {
			client.getConnectionManager().shutdown();
			client = null;
		}
	}

	public static HttpClient getHttpClient() {
		if (client == null) {
			client = createHttpClient();
		}
		return client;
	}

	public static String getUsreId() {
		return UsreId;
	}

	public static void setUsreId(String usreId) {
		UsreId = usreId;
	}

	public static String getToken() {
		return Token;
	}

	public static void setToken(String token) {
		Token = token;
	}

}
