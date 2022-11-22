package com.example.tp04imposetasoiree;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WSConnexion extends AsyncTask<String, Integer, String> {
    private final String base_url = "https://sio.jbdelasalle.com/~amedassi/suivistages/index.php?" ;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static OkHttpClient client = null;

    public WSConnexion() {
        if (client == null) {

            try {
                TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                            }

                            @Override
                            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                            }

                            @Override
                            public X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };


                SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                OkHttpClient.Builder newBuilder = new OkHttpClient.Builder();
                newBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
                newBuilder.hostnameVerifier((hostname, session) -> true);


                newBuilder.cookieJar(new CookieJar() {
                    List<Cookie> cookies;


                    @Override
                    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                        this.cookies = list;
                    }


                    @Override
                    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                        if (cookies != null) {
                            return cookies;
                        } else {
                            return new ArrayList<>();
                        }
                    }
                });
                client = newBuilder.build();
            } catch (Exception ex) {
                Log.e("WSHTTPS", ex.getMessage());
            }
        }

    }

f
    @Override
    protected String doInBackground(String... strings) {
        Request request = new Request.Builder()
                .url(base_url + strings[0])
                .build();
        Log.d("REQUESTSTAGE", base_url + strings[0]);
        try (Response response = client.newCall(request).execute()) {
            String resp = response.body().string();
            Log.d("RESPONSESTAGE", resp);
            return resp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


