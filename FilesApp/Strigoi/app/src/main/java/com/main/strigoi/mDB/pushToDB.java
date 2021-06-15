package com.main.strigoi.mDB;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.main.strigoi.ui.Requests;

import org.json.JSONObject;

import java.util.Arrays;

public class pushToDB implements Runnable {
    public int strigoiNum;
    public Requests self;
    public JSONObject output;
    // System.out.println(input.getString("content"));
// if (input.getString("spiritType") == "Text") {
    public String parsedResponse = "";
    public String address;
    public int sectionNum;
    public int spiritNum;
    public String[] inputArr;

    /**
     * @param strigoiNum The number of the Strigoi you want to push to.
     * @param spiritNum The Spirit Number you want to push to.
     * @param input An array, containing each line of text in a different element.
     * @returns A pushToDB {@link Runnable}.
     *
     * @author Mvb1122
     */
    public pushToDB(int strigoiNum, int spiritNum, String[] input) {
        parsedResponse = "";
        sectionNum = 1;
        inputArr = input;
        spiritNum = spiritNum;
        strigoiNum = strigoiNum;
        address = "https://ihaveawebsite.tk/json/" + strigoiNum + "/" + spiritNum + "/";
        self = new Requests(address, "GET", "None");


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        // TODO: Write code to push data to Database.
        System.out.println("\nStrigoiNum: " + strigoiNum + "\n" + "SpiritNum: " + spiritNum);
        System.out.println(Arrays.toString(inputArr));
    }
}
