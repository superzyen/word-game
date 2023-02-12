package com.superzyen.util;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpUtils {

        private static final OkHttpClient client;
        private static final int HTTP_SUCCESS_CODE = 200;

        static {
            client = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .build();
        }

        public static String get(String url) throws IOException {
            Request request = new Request.Builder()
                    .get()
                    .url(url)
                    .build();
            return execute(request);
        }

        public static String post(String url, RequestBody requestBody) throws IOException {
            Request request = new Request.Builder()
                    .post(requestBody)
                    .url(url)
                    .build();
            return execute(request);
        }

        private static String execute(Request request) throws IOException {
            try (Response response = client.newCall(request).execute()) {
                int responseCode = response.code();
                if (responseCode == HTTP_SUCCESS_CODE) {
                    ResponseBody body = response.body();
                    if (body == null) {
                        return null;
                    }else {
                        return body.string();
                    }
                }
                throw new Exception(String.format("http request failed,code=%d", responseCode));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

}
