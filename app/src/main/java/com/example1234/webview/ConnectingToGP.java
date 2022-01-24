package com.example1234.webview;

import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;

import java.util.Map;
import java.util.Objects;

public class ConnectingToGP extends MainActivity{

    AppsFlyerConversionListener conversionListener =  new AppsFlyerConversionListener() {
        @Override
        public void onConversionDataSuccess(Map<String, Object> conversionDataMap) {
            for (String attrName : conversionDataMap.keySet())
                Log.d(LOG, "Conversion attribute: " + attrName + " = " + conversionDataMap.get(attrName));
            String status = Objects.requireNonNull(conversionDataMap.get("af_status")).toString();
            if(status.equals("Organic")){
                // Business logic for Organic conversion goes here.
            }
            else {
                // Business logic for Non-organic conversion goes here.
            }
        }

        @Override
        public void onConversionDataFail(String errorMessage) {
            Log.d(LOG, "error getting conversion data: " + errorMessage);
        }

        @Override
        public void onAppOpenAttribution(Map<String, String> attributionData) {
            // Must be overriden to satisfy the AppsFlyerConversionListener interface.
            // Business logic goes here when UDL is not implemented.
        }

        @Override
        public void onAttributionFailure(String errorMessage) {
            // Must be overriden to satisfy the AppsFlyerConversionListener interface.
            // Business logic goes here when UDL is not implemented.
            Log.d(LOG, "error onAttributionFailure : " + errorMessage);
        }

    };
}
