package com.main.strigoi.mDB;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.main.strigoi.ui.Requests;

import org.json.JSONException;
import org.json.JSONObject;

public class getSeriesInfo implements Runnable {

    private final int strigoiNum;
    public final String url;
    private int _creatorId;
    public String seriesName;
    public String creationDate;
    public String creatorName;
    private String imageURL;

    /**
     * @param StrigoiNum The number of the Strigoi you want the information of.
     *
     * @returns several properties, view source to see them all:
     *
     * @returns url, A string containing the url of the seriesInfo.
     * @returns seriesName, A string containing the name of the series.
     * @returns creationDate, A string containing the date that the series was created, DD/MM/YYYY
     * @returns creatorName, A string containing either the ID or the name of the creator.
     * @returns imageURL, the series's thumbnail image.
     */
    public getSeriesInfo(int StrigoiNum) {
        this.strigoiNum = StrigoiNum;
        this.url = "https://ihaveawebsite.tk/json/" + strigoiNum + "/seriesInfo.json";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        Requests getSeriesInfoFromServer = new Requests(url, "GET", "None.");
        getSeriesInfoFromServer.run();
        try {
            JSONObject seriesJSON = new JSONObject(getSeriesInfoFromServer.response);
            _creatorId = seriesJSON.getInt("creatorId");
            seriesName = seriesJSON.getString("name");
            creationDate = seriesJSON.getString("creationDate");
            creatorName = getCreatorName(_creatorId);

            // Get Icon separately, so I can give it replacement.
            try {
                imageURL = seriesJSON.getString("thumbURL");
            } catch (JSONException e) {
                System.out.println("Strigoi " + strigoiNum + " does not have an image!!!");
                imageURL = "https://ihaveawebsite.tk/favicon.ico";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id The ID of the creator.
     * @return A string, representing the name of the id you passed in.
     * @return The ID you passed in, if getting the Name fails.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getCreatorName(int id) {
        Requests getName = new Requests("https://ihaveawebsite.tk/users/" + id + ".json", "GET", "None.");
        getName.run();
        try {
            JSONObject userInfo = new JSONObject(getName.response);
            return userInfo.getString("username");
        } catch (JSONException e) {
            return "" + id + "";
        }
    }
}
