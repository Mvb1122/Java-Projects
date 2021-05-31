package com.main.strigoi.mDB;

import com.main.strigoi.ui.Requests;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class getReq implements Runnable {
    public Requests self;
    public JSONObject output;
    // System.out.println(input.getString("content"));
// if (input.getString("spiritType") == "Text") {
    public String parsedResponse = "";
    public String address;
    private int sectionNum;
    private int spiritNum;
    private int panelNum;

    public getReq(int spiritNum, int panelNum) {
        this.parsedResponse = "";
        this.sectionNum = 1;
        this.spiritNum = spiritNum;
        this.panelNum = panelNum;
        address = "https://ihaveawebsite.tk/mDB/" + spiritNum + "/" + panelNum + "/" + sectionNum + ".json";
        self = new Requests(address, "GET");
    }

    @Override
    public void run() {
        // int numSec = 1;
        self.run();
        try {
            output = new JSONObject(self.response);
            parseResponse(output);
            int numSec = output.getInt("length");

            if (numSec > 1) {
                for (int i = 2; i <= numSec; i++) {
                    try {
                        Requests nextSec = new Requests("https://ihaveawebsite.tk/mDB/" + spiritNum + "/" + panelNum + "/" + i + ".json", "GET");
                        nextSec.run();
                        JSONObject nextSecJSON = new JSONObject(nextSec.response);
                        String lineBreak = "\n\n\n";
                        this.parsedResponse += lineBreak + "\nNEXT PANEL:\nPanel " + i + lineBreak;
                        parseResponse(nextSecJSON);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void parseResponse(JSONObject input) throws JSONException {
            JSONArray tempArr = input.getJSONArray("content");
            for (int i = 0; i < tempArr.length(); i++) {
                String inputString = tempArr.getString(i);
                this.parsedResponse += "\n\n" + inputString;
            }
        // }
    }
}
