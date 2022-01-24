package com.example1234.webview;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import example1234.webview.BuildConfig;

public class ParsLine extends MainActivity {


    public String defaultCompany;
    public String nameTeam;
    public String keyCompany;
    public String sub2;
    public String sub3;
    public String sub4;
    public String sub5;




    public String ChangeLine(String lineGet, String adId, String apps_id) {

        lineArray = lineGet.split("::");

        for (int i = 0; i < lineArray.length; i++) {
            Log.i(LOG, "LineArray" + (i + 1) + ": " + lineArray[i]);
        }

        try {
            defaultCompany = lineArray[0];
        } catch (NullPointerException e) {
            Log.i(LOG, "Line: " + "Error");
        } try {
            nameTeam = lineArray[1];
        } catch (NullPointerException e) {
            Log.i(LOG, "Line: " + "Error");
        } try {
            keyCompany = lineArray[2];
        } catch (NullPointerException e) {
            Log.i(LOG, "Line: " + "Error");
        } try {
            sub2 = lineArray[3];
        } catch (NullPointerException e) {
            Log.i(LOG, "Line: " + "Error");
        } try {
            sub3 = lineArray[4];
        } catch (NullPointerException e) {
            Log.i(LOG, "Line: " + "Error");
        } try {
            sub4 = lineArray[5];
        } catch (NullPointerException e) {
            Log.i(LOG, "Line: " + "Error");
        } try {
            sub5 = lineArray[6];
        } catch (NullPointerException e) {
            Log.i(LOG, "Line: " + "Error");
        }
        params = (defaultCompany+"?bundle="+(BuildConfig.APPLICATION_ID)+"&ad_id="+adId+"&apps_id="+apps_id+
                "&sub6="+nameTeam+
                "&sub7="+keyCompany+
                "&sub2="+sub2+
                "&sub3="+sub3+
                "&sub4="+sub4+
                "&sub5="+sub5);
        Log.i(LOG, "params: " + params);
        return params;
    }
}
