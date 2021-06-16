package com.main.strigoi.mDB;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.main.strigoi.ui.Requests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class pushToDB implements Runnable {
    public int strigoiNum;
    public int spiritNum;
    public String parsedResponse;
    public String address;
    public String[] inputArr;

    /**
     * @param strigoiNum The number of the Strigoi you want to push to.
     * @param spiritNum The Spirit Number you want to push to.
     * @param input An array, containing each line of text in a different element.
     * @author Mvb1122
     */
    public pushToDB(int strigoiNum, int spiritNum, String[] input) {
        parsedResponse = "";
        inputArr = input;
        this.spiritNum = spiritNum;
        this.strigoiNum = strigoiNum;
        address = "https://ihaveawebsite.tk/json/" + strigoiNum + "/" + spiritNum + "/";
    }

    /**
     * @author Mvb1122
     * @see {@link pushToDB}
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        /** Consider adjusting the number of threads used for this thread pool: */
        ExecutorService pool = Executors.newScheduledThreadPool((int) inputArr.length / 2);

        System.out.println("StrigoiNum: " + strigoiNum + "SpiritNum: " + spiritNum);
        for (int i = 1; i < inputArr.length + 1; i++) {
            String inputString = inputArr[i - 1].replace("\"", "\\\"");

            try {
                String data = "{\n\t\"content\": [\n\t\t\"" + inputString + "\"\n\t]\n}";
                if (i == 1) {
                    data = "{\n\t\"spiritType\": \"Text\",\n\t\"length\": " + inputArr.length + ",\n\t\"content\": [\n\t\t\"" + inputString + "\"\n\t]\n}";
                }
                Requests requests = new Requests(address + i + ".json", "POST", data);
                pool.submit(requests);
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }
}
