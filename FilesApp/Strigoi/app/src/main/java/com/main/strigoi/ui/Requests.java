package com.main.strigoi.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Requests implements Runnable {

    private static final String USER_AGENT = "Mozilla/5.0";

    private String type;

    private String GET_URL;

    public String response;

    public Requests(String url, String type) {
        System.out.println("Request object created.");
        this.GET_URL = url;
        this.type = type;
    }

    @Override
    public void run() {
        if (this.type.equals("GET")) {
            try {
                System.out.println("Request requested.");
                this.response = sendGET();
                System.out.println("Request completed.");
            } catch (IOException e) {
                e.printStackTrace();
                // TODO: Replace error message.
                this.response = "REQUEST FAILED!!!";
                System.out.println("Request failed :(");
            }
        }
    }

    private String sendGET() throws IOException {
        URL obj = new URL(GET_URL);
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
