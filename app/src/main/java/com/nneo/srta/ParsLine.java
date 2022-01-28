package com.nneo.srta;

import android.util.Log;

import com.onesignal.OneSignal;

import java.util.Arrays;

import com.nneo.srta.BuildConfig;

public class ParsLine extends NS {

    String key;
    String sub6;
    String sub7;
    String sub2;
    String sub3;
    String sub4;
    String sub5;
    public String[] lineArrayNnneoSrta;

    public String ChangeLine(String lineGet) {

        lineArrayNnneoSrta = lineGet.split("::");

        try {
            key = lineArrayNnneoSrta[0];
        } catch (NullPointerException e) {
        }
        try {
            sub6 = lineArrayNnneoSrta[1];
        } catch (NullPointerException e) {
        }
        try {
            sub7 = lineArrayNnneoSrta[2];
        } catch (NullPointerException e) {
        }
        try {
            sub2 = lineArrayNnneoSrta[3];
        } catch (NullPointerException e) {
        }
        try {
            sub3 = lineArrayNnneoSrta[4];
        } catch (NullPointerException e) {
        }
        try {
            sub4 = lineArrayNnneoSrta[5];
        } catch (NullPointerException e) {
        }
        try {
            sub5 = lineArrayNnneoSrta[6];
        } catch (NullPointerException e) {
        }

        OneSignal.sendTag(Decoder11("c3ViX2FwcA=="), sub6);
        paramsNnneoSrta = (key + Decoder11("P2J1bmRsZT0=") + (BuildConfig.APPLICATION_ID)
                + Decoder11("JmFkX2lkPQ==") + NS.adIdNnneoSrta + Decoder11("JmFwcHNfaWQ9") + NS.apps_idNnneoSrta +
                Decoder11("JmRldl9rZXk9") + AF_DEV_KEY_NNEO_SRTA +
                Decoder11("JnN1YjY9") + sub6 +
                Decoder11("JnN1Yjc9") + sub7 +
                Decoder11("JnN1YjI9") + sub2 +
                Decoder11("JnN1YjM9") + sub3 +
                Decoder11("JnN1YjQ9") + sub4 +
                Decoder11("JnN1YjU9") + sub5);
        Log.i(LOG, "ChangeLineParams: " + paramsNnneoSrta);
        return paramsNnneoSrta;
    }
}
