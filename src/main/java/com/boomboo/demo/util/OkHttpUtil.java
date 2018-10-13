package com.boomboo.demo.util;

import com.boomboo.demo.dto.OkHttpResp;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * Created by HaiboWang on 16/11/23.
 */
public class OkHttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);
    private volatile static OkHttpUtil mInstance;
    private OkHttpClient mOkHttpClient;
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

    public OkHttpUtil(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            mOkHttpClient = getUnsafeOkHttpClient();//new OkHttpClient();
        } else {
            mOkHttpClient = okHttpClient;
        }
    }

    public static OkHttpUtil initClient(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (OkHttpUtil.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtil(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public static OkHttpUtil getInstance() {
        return initClient(null);
    }


    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static OkHttpResp post(String url, Map<String, String> body) throws IOException {
        return post(url, null, body);
    }

    public static OkHttpResp post(String url, Map<String, String> header, Map<String, String> body) throws IOException {
        Headers.Builder headerBuilder = createHeader(header);

        FormBody.Builder b = new FormBody.Builder();
        body.entrySet().stream()
                .forEach(entry -> b.addEncoded(entry.getKey(), entry.getValue()));

        Request request = new Request.Builder()
                .url(url)
                .headers(headerBuilder.build())
                .post(b.build())
                .build();
        return responseHandler(request, JsonUtil.toStringV2(body));
    }

    public static OkHttpResp postFormData(String url, File file, Map<String, String> header, Map<String, String> body) throws IOException {
        Headers.Builder headerBuilder = createHeader(header);

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/png"), file));

        body.entrySet().stream()
                .forEach(entry -> builder.addFormDataPart(entry.getKey(), entry.getValue()));

        Request request = new Request.Builder()
                .url(url)
                .headers(headerBuilder.build())
                .post(builder.build())
                .build();
        return responseHandler(request, JsonUtil.toStringV2(body));
    }

    public static OkHttpResp postJson(String url, String body) throws IOException {
        return postJson(url, null, body);
    }

    public static OkHttpResp postJson(String url, Map<String, String> header, String body) throws IOException {
        RequestBody jsonRequestBody = RequestBody.create(
                MEDIA_TYPE_JSON, body);
        Headers.Builder headerBuilder = createHeader(header);

        Request request = new Request.Builder()
                .url(url)
                .headers(headerBuilder.build())
                .post(jsonRequestBody)
                .build();
        return responseHandler(request, body);
    }

    public static OkHttpResp get(String url) throws IOException {
        return get(url, null, null);
    }


    public static OkHttpResp formPost(String url, String content) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, content);//"platform=ios&network=4g"
        Request okRequest = new Request.Builder()
                .url(url)//"http://ttc.botbrain.ai/v3/config/Y2X9KTQANB"
                .post(body)
                .build();
        return responseHandler(okRequest, null);
    }

    public static OkHttpResp get(String url, Map<String, String> header, Map<String, String> body) throws IOException {
        Headers.Builder headerBuilder = createHeader(header);
        String link = appendParams(url, body);
        Request request = new Request.Builder()
                .headers(headerBuilder.build())
                .url(link)
                .build();

        return responseHandler(request, "");
    }

    //    private static String responseHandler(Request request, String requestParams) throws IOException {
//        String result = null;
//        try (Response response = OkHttpUtil.getInstance().getOkHttpClient().newCall(request).execute()) {
//            if (!response.isSuccessful()) {
//                logger.error(response.body().string());
//                throw new IOException("Unexpected code " + response);
//            }
//            result = response.body().string();
//            logger.info("\nurl: {}\nparams: {}\nresult: {}",request.url(),requestParams,result);
//            return result;
//        }
//    }
    private static OkHttpResp responseHandler(Request request, String requestParams) throws IOException {
        OkHttpResp resp = new OkHttpResp();
        String body = null;
        String header = null;
        try (Response response = OkHttpUtil.getInstance().getOkHttpClient().newCall(request).execute()) {
            resp.setCode(response.code());
            header = response.headers().toString();
            body = response.body().string();
            resp.setHeader(header);
            resp.setBody(body);
            if (!response.isSuccessful()) {
                resp.setMessage(response.message());
                logger.error("Unexpected code: " + response);
                logger.info("\nurl: {}\nparams: {}\nheader: {}\nresponse: {}", request.url(), requestParams, header, body);
            }
            return resp;
        }
    }

    private static Headers.Builder createHeader(Map<String, String> header) {
        Headers.Builder headerBuilder = new Headers.Builder();

        if (header != null) {
            for (String key : header.keySet()) {
                headerBuilder.add(key, header.get(key));
            }
        }
        return headerBuilder;
    }

    public static String appendParams(String url, Map<String, String> params) {
        if (url == null || params == null || params.isEmpty()) {
            return url;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(url);
        sb.append("?");
        for (String key : params.keySet()) {
            sb.append(key);
            sb.append("=");
            sb.append(params.get(key));
            sb.append("&");
        }

        url = sb.substring(0, sb.length() - 1);

        return url;
    }

    public static String appendParamsEncode(String url, Map<String, String> params) {
        if (url == null || params == null || params.isEmpty()) {
            return url;
        }

        StringBuffer sb = new StringBuffer();
        sb.append(url);
        sb.append("?");
        for (String key : params.keySet()) {
            sb.append(key);
            sb.append("=");
            sb.append(encodeURIComponent(params.get(key)));
            sb.append("&");
        }

        url = sb.toString();
//todo
        return url;
    }

    public static String encodeURIComponent(String s) {
        String result;

        try {
            result = URLEncoder.encode(s, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
        } catch (UnsupportedEncodingException e) {
            result = s;
        }

        return result;
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(30, TimeUnit.SECONDS);
            builder.writeTimeout(30, TimeUnit.SECONDS);
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
