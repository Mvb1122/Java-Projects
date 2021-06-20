package com.main.strigoi.ui;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Requests implements Runnable {

    private static final String USER_AGENT = "Mozilla/5.0";

    private final String type;

    private final String url;

    private final String data;

    public String response;

    public Requests(String url, String type, String data) {
        System.out.println("Request object created, URL: " + url);
        this.url = url;
        this.type = type;
        this.data = data;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        if (this.type.equals("GET")) {
            try {
                System.out.println("Request requested.");
                this.response = sendGET();
                System.out.println("Request completed.");
            } catch (IOException e) {
                e.printStackTrace();
                this.response = e.toString();
            }
        } else if (this.type.equals("POST")) {
            try {
                System.out.println("Request POST'd");
                this.response = sendPOST();
                System.out.println("Posting Posted.");
            } catch (IOException e) {
                e.printStackTrace();
                this.response = e.toString();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String sendPOST() throws IOException {
        URL obj = new URL(url);
        HttpURLConnection http = (HttpURLConnection)obj.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
        return "Write complete!";
    }

    private String sendGET() throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
            return "GET request not worked";
        }

    }

}
