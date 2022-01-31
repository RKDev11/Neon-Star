package com.nneo.srta;

import android.util.Log;

import com.onesignal.OneSignal;

public class ParsLine extends NS {

    String keyNnneoSrta;
    String sub6NnneoSrta;
    String sub7NnneoSrta;
    String sub2NnneoSrta;
    String sub3NnneoSrta;
    String sub4NnneoSrta;
    String sub5NnneoSrta;
    public String[] lineArrayNnneoSrta;

    public String ChangeLine(String lineGetNnneoSrta) {

        lineArrayNnneoSrta = lineGetNnneoSrta.split("::");

        try {
            keyNnneoSrta = lineArrayNnneoSrta[0];
        } catch (NullPointerException e) {
            keyNnneoSrta = "";
        }
        try {
            sub6NnneoSrta = lineArrayNnneoSrta[1];
        } catch (NullPointerException e) {
            sub6NnneoSrta = "";
        }
        try {
            sub7NnneoSrta = lineArrayNnneoSrta[2];
        } catch (NullPointerException e) {
            sub7NnneoSrta = "";
        }
        try {
            sub2NnneoSrta = lineArrayNnneoSrta[3];
        } catch (NullPointerException e) {
            sub2NnneoSrta = "";
        }
        try {
            sub3NnneoSrta = lineArrayNnneoSrta[4];
        } catch (NullPointerException e) {
            sub3NnneoSrta = "";
        }
        try {
            sub4NnneoSrta = lineArrayNnneoSrta[5];
        } catch (NullPointerException e) {
            sub4NnneoSrta = "";
        }
        try {
            sub5NnneoSrta = lineArrayNnneoSrta[6];
        } catch (NullPointerException e) {
            sub5NnneoSrta = "";
        }

        OneSignal.sendTag(Decoder11("c3ViX2FwcA=="), sub6NnneoSrta);
        paramsNnneoSrta = (keyNnneoSrta + Decoder11("P2J1bmRsZT0=") + (BuildConfig.APPLICATION_ID)
                + Decoder11("JmFkX2lkPQ==") + NS.adIdNnneoSrta + Decoder11("JmFwcHNfaWQ9") + NS.apps_idNnneoSrta +
                Decoder11("JmRldl9rZXk9") + Decoder11(AF_DEV_KEY_NNEO_SRTA) +
                Decoder11("JnN1YjY9") + sub6NnneoSrta +
                Decoder11("JnN1Yjc9") + sub7NnneoSrta +
                Decoder11("JnN1YjI9") + sub2NnneoSrta +
                Decoder11("JnN1YjM9") + sub3NnneoSrta +
                Decoder11("JnN1YjQ9") + sub4NnneoSrta +
                Decoder11("JnN1YjU9") + sub5NnneoSrta);
        return paramsNnneoSrta;
    }
}
