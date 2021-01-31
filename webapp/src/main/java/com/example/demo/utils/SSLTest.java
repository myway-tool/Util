package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

public class SSLTest {
    //GitHubのREST API v3を使ってGitHubのリポジトリ情報を取得する
    //REST API v3の形式は
    // /repos/:owner/:repo/contents/:path
    //となる
    private static final String GITHUB_RESTAPI_PATH = "https://api.github.com/repos/triple4649/MLLearning_Java/contents/src";
    public static void main(String[] args) throws Exception {

        HttpsURLConnection con = createHttpsURLConnection(GITHUB_RESTAPI_PATH);

        //ヘッダ情報を出力する
        System.out.println("----- Headers -----");
        printHeaderFields(con);

        //Body情報を出力する
        System.out.println("----- Body -----");
        printBody(con);


        con.disconnect();
    }

    //TLS接続用のURLConnectionを生成する(認証の検証なし版)
    private static HttpsURLConnection  createHttpsURLConnection (String path) throws Exception{
        SSLSocketFactory factory = null;
//        SSLContext ctx = SSLContext.getInstance("TLS");
        SSLContext ctx = SSLContext.getInstance("SSL");
        ctx.init(null, new NonAuthentication[] { new NonAuthentication() },
                null);
        factory = ctx.getSocketFactory();

        URL url = new URL(path);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setSSLSocketFactory(factory);

        return con;
    }

    //header情報を出力する
    private static void printHeaderFields(HttpsURLConnection con){
        con.getHeaderFields()
        .entrySet()
        .stream()
        .map(e->String.format("key:%s value:%s", e.getKey(),e.getValue()))
        .forEach(System.out::println);
    }

    //Body情報を出力する
    private static void printBody(HttpsURLConnection con) throws Exception{
        new BufferedReader(new InputStreamReader(
                con.getInputStream()))
        .lines()
        .forEach(System.out::println);

    }
}

class NonAuthentication implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}