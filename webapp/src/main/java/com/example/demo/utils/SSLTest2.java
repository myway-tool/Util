package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SSLTest2 {
	// GitHubのREST API v3を使ってGitHubのリポジトリにファイルを追加する
	// REST API v3の形式は
	// /repos/:owner/:repo/contents/:path
	// となる
//	private static final String ACCESS_KEY = "＊＊＊＊＊＊＊＊＊";
//	private static final String GITHUB_RESTAPI_PATH = "https://api.github.com/repos/triple4649/images/contents/img/mypicture.png?access_token=%s";
	private static final String GITHUB_RESTAPI_PATH = "https://api.github.com/repos/triple4649/MLLearning_Java/contents/src";
//	private static final String GITHUB_RESTAPI_PATH = "https://google.com?access_token=%s";
//	private static final String GITHUB_RESTAPI_PATH = "https://google.com";

	public static void main(String[] args) throws Exception {

//		HttpsURLConnection con = createHttpsURLConnection(String.format(GITHUB_RESTAPI_PATH, ACCESS_KEY), "GET");
		HttpsURLConnection con = createHttpsURLConnection(String.format(GITHUB_RESTAPI_PATH), "GET");

		con.connect();

		int status = con.getResponseCode();

        switch (status) {
            case 200:
            case 201:
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line+"\n");
                }
                br.close();
//                return sb.toString();
                System.out.print(sb.toString());
        }



//		// RestAPIでイメージファイルをＧｉｔHubにコミットする
//		putContents(con, createJson());
//
//		// RestAPIのレスポンスのヘッダー情報を出力する
//		printHeaderFields(con);
//		// RestAPIのレスポンスの結果を出力する
//		printBody(con);

		con.disconnect();
	}

	// StreamにJSONを書きだす
	private static void putContents(HttpsURLConnection con, byte[] b) throws Exception {
		con.setDoOutput(true);
		OutputStream o = con.getOutputStream();
		o.write(b);
		o.flush();
	}

	// 指定したパスのファイルをBase64形式に変換する
	public static String converToBase64(String path) throws Exception {
		return Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(path)));
	}

	// HTTPの送信メソッドを指定してHttpsURLConnectionを生成する
	private static HttpsURLConnection createHttpsURLConnection(String path, String method) throws Exception {
		HttpsURLConnection con = createHttpsURLConnection(path);
		con.setRequestMethod(method);
		return con;
	}

	// GitHubにコミットするデータを生成する
	private static byte[] createJson() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, String> nestmap = new LinkedHashMap<String, String>();
		nestmap.put("name", "triple");
		nestmap.put("email", "triple_55@yahoo.co.jp");
		map.put("committer", nestmap);
		map.put("message", "GitHub API");
//		map.put("content", converToBase64("./CA390046.JPG"));
		return new ObjectMapper().writeValueAsBytes(map);
	}

	// TLS接続用のURLConnectionを生成する(認証の検証なし版)
	// リクエストメソッドはデフォルト(Get)
	private static HttpsURLConnection createHttpsURLConnection(String path) throws Exception {
		SSLSocketFactory factory = null;
//		SSLContext ctx = SSLContext.getInstance("TLS");
		SSLContext ctx = SSLContext.getInstance("SSL");

		ctx.init(null, new NonAuthentication[] { new NonAuthentication() }, null);
		factory = ctx.getSocketFactory();

		URL url = new URL(path);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setSSLSocketFactory(factory);
		return con;
	}

	// header情報を出力する
	private static void printHeaderFields(HttpsURLConnection con) {
		con.getHeaderFields().entrySet().stream().map(e -> String.format("key:%s value:%s", e.getKey(), e.getValue()))
				.forEach(System.out::println);
	}

	// Body情報を出力する
	private static void printBody(HttpsURLConnection con) throws Exception {
		new BufferedReader(new InputStreamReader(con.getInputStream())).lines().forEach(System.out::println);

	}


//	public String getJSON(String url, int timeout) {
//	    HttpURLConnection c = null;
//	    try {
//	        URL u = new URL(url);
//	        c = (HttpURLConnection) u.openConnection();
//	        c.setRequestMethod("GET");
//	        c.setRequestProperty("Content-length", "0");
//	        c.setUseCaches(false);
//	        c.setAllowUserInteraction(false);
//	        c.setConnectTimeout(timeout);
//	        c.setReadTimeout(timeout);
//	        c.connect();
//	        int status = c.getResponseCode();
//
//	        switch (status) {
//	            case 200:
//	            case 201:
//	                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
//	                StringBuilder sb = new StringBuilder();
//	                String line;
//	                while ((line = br.readLine()) != null) {
//	                    sb.append(line+"\n");
//	                }
//	                br.close();
//	                return sb.toString();
//	        }
//
//	    } catch (MalformedURLException ex) {
//	        Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
//	    } catch (IOException ex) {
//	        Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
//	    } finally {
//	       if (c != null) {
//	          try {
//	              c.disconnect();
//	          } catch (Exception ex) {
//	             Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
//	          }
//	       }
//	    }
//	    return null;
//	}

}

//class NonAuthentication implements X509TrustManager {
//    @Override
//    public void checkClientTrusted(X509Certificate[] chain, String authType)
//            throws CertificateException {
//    }
//
//    @Override
//    public void checkServerTrusted(X509Certificate[] chain, String authType)
//            throws CertificateException {
//    }
//
//    @Override
//    public X509Certificate[] getAcceptedIssuers() {
//        return null;
//    }
//}