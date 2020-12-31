package com.boylab.projectstruct.utilre;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    private static final String TAG = "HttpUtils";

    private static String httputils_get_string(String url_string, int timeout) {
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        try {
            Log.i(TAG, "url_string: " + url_string);
            URL url = new URL(url_string);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(timeout);
            httpURLConnection.setReadTimeout(timeout);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            //代表请求成功
            if (httpURLConnection.getResponseCode() == 200) {
                InputStream in = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(in));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                String response_string = result.toString();
                Log.i(TAG, "response_string: " + response_string);
                return response_string;
            }
        } catch (Throwable tr) {
            tr.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Throwable tr) {
                    tr.printStackTrace();
                }
            }
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Throwable tr) {
                    tr.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String httputils_get_string(String url_string) {
        int timeout = 10000;
        long beginTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - beginTime < timeout) {
            try {
                String response_string = httputils_get_string(url_string, timeout);
                if ((response_string != null) && (response_string.length() > 0))
                    return response_string;
                else
                    Thread.sleep(100);
            } catch (Throwable tr) {
                tr.printStackTrace();
            }
        }
        return null;
    }

    public static byte[] httputils_get_bytes_once(String url_string, int timeout, HttpDownloadProgressListener listener, HttpDownloadProgressController controller) {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            Log.i(TAG, "url_string: " + url_string);
            URL url = new URL(url_string);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(timeout);
            httpURLConnection.setReadTimeout(timeout);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            //代表请求成功
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                Log.i(TAG, "ContentLength: " + httpURLConnection.getContentLength());
                byte[] response_bytes = new byte[httpURLConnection.getContentLength()];
                int totalReaded = 0;
                long updateTime = System.currentTimeMillis();
                while (totalReaded < response_bytes.length) {
                    if ((controller != null) && (controller.break_download))
                        break;
                    int bytesReaded = inputStream.read(response_bytes, totalReaded, response_bytes.length - totalReaded);
                    Log.i(TAG, "bytesReaded: " + bytesReaded);
                    totalReaded += bytesReaded;
                    if (listener != null) {
                        // 稍微限制一下更新频率
                        if ((totalReaded == response_bytes.length) || (System.currentTimeMillis() - updateTime >= 1000)) {
                            listener.onDownloadProgressUpdated(response_bytes.length, totalReaded);
                            updateTime = System.currentTimeMillis();
                        }
                    }
                    if (totalReaded == response_bytes.length) {
                        return response_bytes;
                    }
                }
            }
        } catch (Throwable tr) {
            tr.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable tr) {
                    tr.printStackTrace();
                }
            }
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Throwable tr) {
                    tr.printStackTrace();
                }
            }
        }
        return null;
    }

    public static byte[] httputils_get_bytes_loop(String url_string, int timeout, HttpDownloadProgressListener listener, HttpDownloadProgressController controller) {
        long beginTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - beginTime < timeout) {
            if ((controller != null) && (controller.break_download))
                break;
            try {
                byte[] response_bytes = httputils_get_bytes_once(url_string, timeout, listener, controller);
                if ((response_bytes != null) && (response_bytes.length > 0))
                    return response_bytes;
                else
                    Thread.sleep(100);
            } catch (Throwable tr) {
                tr.printStackTrace();
            }
        }
        return null;
    }

    public static byte[] httputils_get_bytes(String url_string) {
        return httputils_get_bytes_loop(url_string, 10000, null, null);
    }

    private static String upLoadFilePost1(String actionUrl, Map<String, File> files) throws IOException {
        String BOUNDARY = java.util.UUID.randomUUID().toString();
        String PREFIX = "--", LINEND = "\r\n";
        String MULTIPART_FROM_DATA = "multipart/form-data";
        String CHARSET = "UTF-8";
        URL uri = new URL(actionUrl);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setReadTimeout(10000);
        conn.setDoInput(true);// 允许输入
        conn.setDoOutput(true);// 允许输出
        conn.setUseCaches(false);
        conn.setRequestMethod("POST"); // Post方式
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);

        DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
        // 发送文件数据
        if (files != null)
            for (Map.Entry<String, File> file : files.entrySet()) {
                StringBuilder sb1 = new StringBuilder();
                sb1.append(PREFIX);
                sb1.append(BOUNDARY);
                sb1.append(LINEND);
                sb1.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getKey() + "\"" + LINEND);
                sb1.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
                sb1.append(LINEND);
                outStream.write(sb1.toString().getBytes());
                InputStream is = new FileInputStream(file.getValue());
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }

                is.close();
                outStream.write(LINEND.getBytes());
            }

        // 请求结束标志
        byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
        outStream.write(end_data);
        outStream.flush();

        // 得到响应码
        int res = conn.getResponseCode();
        if (res == 200) {
            InputStream in = conn.getInputStream();
            InputStreamReader isReader = new InputStreamReader(in);
            BufferedReader bufReader = new BufferedReader(isReader);
            String line = "";
            String data = "";
            while ((line = bufReader.readLine()) != null) {
                data += line;
            }
            outStream.close();
            conn.disconnect();
            return data;
        }
        outStream.close();
        conn.disconnect();
        return "";
    }

    private static String upLoadFilePost2(String actionUrl, Map<String, byte[]> files, int timeout) throws IOException {
        String BOUNDARY = java.util.UUID.randomUUID().toString();
        String PREFIX = "--", LINEND = "\r\n";
        String MULTIPART_FROM_DATA = "multipart/form-data";
        String CHARSET = "UTF-8";
        URL uri = new URL(actionUrl);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setReadTimeout(timeout);
        conn.setDoInput(true);// 允许输入
        conn.setDoOutput(true);// 允许输出
        conn.setUseCaches(false);
        conn.setRequestMethod("POST"); // Post方式
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);

        DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
        // 发送文件数据
        if (files != null)
            for (Map.Entry<String, byte[]> file : files.entrySet()) {
                StringBuilder sb1 = new StringBuilder();
                sb1.append(PREFIX);
                sb1.append(BOUNDARY);
                sb1.append(LINEND);
                sb1.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getKey() + "\"" + LINEND);
                sb1.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
                sb1.append(LINEND);
                outStream.write(sb1.toString().getBytes());
                outStream.write(file.getValue());
                outStream.write(LINEND.getBytes());
            }

        // 请求结束标志
        byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
        outStream.write(end_data);
        outStream.flush();

        // 得到响应码
        int res = conn.getResponseCode();
        if (res == 200) {
            InputStream in = conn.getInputStream();
            InputStreamReader isReader = new InputStreamReader(in);
            BufferedReader bufReader = new BufferedReader(isReader);
            String line = "";
            String data = "";
            while ((line = bufReader.readLine()) != null) {
                data += line;
            }
            outStream.close();
            conn.disconnect();
            return data;
        }
        outStream.close();
        conn.disconnect();
        return "";
    }

    private static String httputils_post(String url_string, String data_key, byte[] data_value, int timeout) {
        try {
            Log.i(TAG, "url_string: " + url_string);
            Map<String, byte[]> files = new HashMap<>();
            files.put(data_key, data_value);
            String response_string = upLoadFilePost2(url_string, files, timeout);
            Log.i(TAG, "response_string: " + response_string);
            return response_string;
        } catch (Throwable tr) {
            tr.printStackTrace();
        }
        return null;
    }

    public static String httputils_post(String url_string, String data_key, byte[] data_value) {
        int timeout = 10000;
        long beginTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - beginTime < timeout) {
            try {
                String response_string = httputils_post(url_string, data_key, data_value, timeout);
                if ((response_string != null) && (response_string.length() > 0))
                    return response_string;
                else
                    Thread.sleep(100);
            } catch (Throwable tr) {
                tr.printStackTrace();
            }
        }
        return null;
    }

    public interface HttpDownloadProgressListener {
        public void onDownloadProgressUpdated(int total, int finished);
    }

    public static class HttpDownloadProgressController {
        public boolean break_download = false;
    }

}
