package com.main.strigoi.mDB;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.main.strigoi.ui.Requests;

import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        // TODO: Optimize code to push data to Database.

        // TODO: Find the value that best optimizes for thread usage and for time to complete.
        ExecutorService pool = Executors.newScheduledThreadPool((int) inputArr.length / 2);

        System.out.println("\nStrigoiNum: " + strigoiNum + "\n" + "SpiritNum: " + spiritNum);
        for (int i = 1; i < inputArr.length + 1; i++) {
            try {
                String data = "{\n\t\"content\": [\n\t\"" + inputArr[i] + "\"\n\t]\n}";
                if (i == 1) {
                    data = "{\n\t\"spiritType\": \"Text\",\n\t\"length\": " + inputArr.length + ",\n\t\"content\": [\n\t\"" + inputArr[1] + "\"\n]\n}";
                }
                Requests requests = new Requests(address + i + ".json", "POST", data);
                pool.submit(requests);
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }
}
