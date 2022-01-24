package com.example1234.webview;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import android.webkit.WebChromeClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appsflyer.AppsFlyerLib;
import com.facebook.FacebookSdk;
import com.facebook.applinks.AppLinkData;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

import example1234.webview.R;


public class MainActivity extends AppCompatActivity {
    public WebView webView;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String ONESIGNAL_APP_ID = "fd824f06-7942-4154-ad00-b13887c010d3";
    private static final String URL_DEF = "https://gist.githubusercontent.com/RKDev11/368f54a1427b0cce6481bd01a1cc804d/raw/test";
    public static final int FILECHOOSER_RESULTCODE = 1;
    private static final String AF_DEV_KEY = "w6jtJmYQzh6ucbKbYxfT2j";
    public static final String LOG = "MyLog";
    public static String BUF_READ;
    public String[] line;
    public String id;
    private String url;
    public ValueCallback<Uri[]> mFilePathCallback;
    public String mCameraPhotoPath;
    public ValueCallback<Uri> mUploadMessage;
    public Uri mCapturedImageURI = null;
    public String adId;
    public String lineGet = "Gp88Vp::gamblords::ИДЕНТИФИКАТОРКАМПАНИИ::sub2::sub3::sub4::sub5";
    public String[] lineArray;
    public String apps_id;
    public String params;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebClass webClass = new WebClass();
        ParsLine parsLine = new ParsLine();
        ConnectingToGP connectingToGP = new ConnectingToGP();


        int adb = Settings.Secure.getInt(this.getContentResolver(),
                Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);

        webClass.oneSignal(this);



        AppsFlyerLib.getInstance().init(AF_DEV_KEY, connectingToGP.conversionListener, MainActivity.this);
        AppsFlyerLib.getInstance().start(MainActivity.this);

        apps_id = AppsFlyerLib.getInstance().getAppsFlyerUID(this);
        Log.i(LOG, "apps_id: " + apps_id);

        if (adb == 1) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        adId = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext()).getId();
                        Log.i(LOG, "adId: " + adId);
                        webClass.HttpConnect(URL_DEF);
                        parsLine.ChangeLine(lineGet, adId, apps_id);

                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(URL_DEF).openConnection();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        BUF_READ = bufferedReader.readLine();
                        Log.i(LOG, "Line: " + BUF_READ);
                        line = BUF_READ.split("\\u007c");

                        SharedPreferences sp = getSharedPreferences("your_url", Activity.MODE_PRIVATE);
                        url = sp.getString("key_url", "https://dropmefiles.com");
                        line[0] = url;
                        Log.i(LOG, "line[0]: " + line[0]);

                        String keyLink = line[1];
                        Log.i(LOG, "keyLink: " + keyLink);

                        id = line[2];
                        Log.i(LOG, "line[2]: " + line[2]);

                        FacebookSdk.fullyInitialize();
                        FacebookSdk.setApplicationId(id);
                        FacebookSdk.setAutoInitEnabled(true);
                        AppLinkData.fetchDeferredAppLinkData(MainActivity.this,
                                new AppLinkData.CompletionHandler() {
                                    @Override
                                    public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                                        if(appLinkData != null) {
                                            String data = appLinkData.getTargetUri().getQuery();
                                            Log.i(LOG, "Data: " + data);
                                        }

                                    }
                                }
                        );

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                webView = findViewById(R.id.webView);
                                webClass.getWeb(webView, line[0]);


                                WebViewClient webViewClient = new WebViewClient() {
                                    @Override
                                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                        view.loadUrl(url);
                                        return true;
                                    }

                                    @TargetApi(Build.VERSION_CODES.N)
                                    @Override
                                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                        view.loadUrl(request.getUrl().toString());
                                        return true;
                                    }

                                    @Override
                                    public void onPageFinished(WebView view, String url) {
                                        super.onPageFinished(view, url);
                                        SharedPreferences sp = getSharedPreferences("your_url", Activity.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sp.edit();
                                        editor.putString("key_url", url);
                                        editor.commit();
                                    }
                                };

                                webView.setWebViewClient(webViewClient);

                                webView.setWebChromeClient(new WebChromeClient() {

                                    public boolean onShowFileChooser(
                                            WebView webView, ValueCallback<Uri[]> filePathCallback,
                                            FileChooserParams fileChooserParams) {
                                        if (mFilePathCallback != null) {
                                            mFilePathCallback.onReceiveValue(null);
                                        }
                                        mFilePathCallback = filePathCallback;

                                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

                                            File photoFile = null;
                                            try {
                                                photoFile = createImageFile();
                                                takePictureIntent.putExtra("PhotoPath", mCameraPhotoPath);
                                            } catch (IOException ex) {
                                                Log.e(TAG, "Unable to create Image File", ex);
                                            }

                                            if (photoFile != null) {
                                                mCameraPhotoPath = "file:" + photoFile.getAbsolutePath();
                                                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                                        Uri.fromFile(photoFile));
                                            } else {
                                                takePictureIntent = null;
                                            }
                                        }
                                        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
                                        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                                        contentSelectionIntent.setType("image/*");

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
                                        startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE);
                                        return true;
                                    }

                                    // creating image files (Lollipop only)
                                    private File createImageFile() throws IOException {

                                        File imageStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "DirectoryNameHere");

                                        if (!imageStorageDir.exists()) {
                                            imageStorageDir.mkdirs();
                                        }
                                        // create an image file name
                                        imageStorageDir = new File(imageStorageDir + File.separator + "IMG_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
                                        return imageStorageDir;
                                    }
                                });
                            }
                        });
                    } catch (Exception e) {
                    }
                }
            }).start();
        } else {
            onDestroy();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == FILECHOOSER_RESULTCODE) {
                if (null == this.mUploadMessage) {
                    return;
                }
                Uri result = null;
                try {
                    if (resultCode != RESULT_OK) {
                        result = null;
                    } else {
                        result = data == null ? mCapturedImageURI : data.getData();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "activity :" + e, Toast.LENGTH_LONG).show();
                }
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode != FILECHOOSER_RESULTCODE || mFilePathCallback == null) {
                super.onActivityResult(requestCode, resultCode, data);
                return;
            }
            Uri[] results = null;
            if (resultCode == Activity.RESULT_OK) {
                if (data == null || data.getData() == null) {
                    if (mCameraPhotoPath != null) {
                        results = new Uri[]{Uri.parse(mCameraPhotoPath)};
                    }
                } else {
                    String dataString = data.getDataString();
                    if (dataString != null) {
                        results = new Uri[]{Uri.parse(dataString)};
                    }
                }
            }
            mFilePathCallback.onReceiveValue(results);
            mFilePathCallback = null;
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.isFocused() && webView.canGoBack()) {
            webView.goBack();
        }
    }
}