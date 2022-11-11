package com.main.strigoi.mDB;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.main.strigoi.ui.Requests;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class createSeries implements Runnable {
    private final int creatorId;
    private final String thumbURL;
    private String name;

    public createSeries(String name, int creatorId) {
        this(name, creatorId, "https://micahb.dev/favicon.ico");
    }

    public createSeries(String name, int creatorId, String thumbURL) {
        this.name = name;
        this.creatorId = creatorId;
        this.thumbURL = thumbURL;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {
        // Get the date of creation.
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        // Create JSON text to send to server.
        String outputJSON = "{\n\t\"creatorId\": " + creatorId + ",\n\t\"name\": \"" + name + "\",\n\t\"creationDate\": \"" + date + "\",\n\t\"thumbURL\": \"" + thumbURL + "\"\n}";

        // Get the number for a new Series.
        Requests getNewSeriesNumber = new Requests("https://micahb.dev/seriesNumber.json", "GET", "None.");
        getNewSeriesNumber.run();

        // Post Data to server.
        try {
            JSONObject seriesNumber = new JSONObject(getNewSeriesNumber.response);
            Requests postInformationToDB = new Requests("https://micahb.dev/json/" + seriesNumber.getInt("number") + "/seriesInfo.json", "POST", outputJSON);
            postInformationToDB.run();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
