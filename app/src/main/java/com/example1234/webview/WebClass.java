package com.example1234.webview;

import static android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;

import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebClass extends MainActivity{

    public void getWeb(WebView webView, String line){



        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setSaveFormData(true);
        webView.getSettings().setMixedContentMode(0);
        webView.getSettings().setSavePassword(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setEnableSmoothTransition(true);
        webView.loadUrl(line);
    }
    public void oneSignal(MainActivity mainActivity) {
        com.onesignal.OneSignal.setLogLevel(com.onesignal.OneSignal.LOG_LEVEL.VERBOSE, com.onesignal.OneSignal.LOG_LEVEL.NONE);
        com.onesignal.OneSignal.initWithContext(mainActivity);
        com.onesignal.OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
    public String HttpConnect(String URL_DEF){
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(URL_DEF).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BUF_READ = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BUF_READ;
    }

}
