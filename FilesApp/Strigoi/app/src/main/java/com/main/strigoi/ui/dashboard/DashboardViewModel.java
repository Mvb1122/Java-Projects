package com.main.strigoi.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.main.strigoi.MainActivity;
import com.main.strigoi.mDB.getReq;
import com.main.strigoi.ui.Reader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private Reader reader = new Reader();
    public static getReq testReqFormDB;

    public DashboardViewModel() throws JSONException {
        mText = new MutableLiveData<>();

        JSONObject input;
        try {
            String inputString = reader.readFile("Registrar.json");
            input = new JSONObject(inputString);
        } catch (IOException e) {
            int userId = (int) Math.floor(Math.random() * 999999);
            String jsonData = "{\n\t\"userId\": \"" + userId + "\"\n}";
            input = new JSONObject(jsonData);
            reader.writeFile(jsonData, "Registrar.json");
        }

        mText.setValue("Your user ID is: " + input.getInt("userId"));
        int strigoiNum;
        int spiritNum;
        try {
            String strNumE = MainActivity.strigoiNum.getText().toString();
            String spiritNumE = MainActivity.spiritNum.getText().toString();
            if (!(strNumE.equals("") || spiritNumE.equals(""))) {
                strigoiNum = parseInt(strNumE);
                spiritNum = parseInt(spiritNumE);
            } else {
                strigoiNum = 1;
                spiritNum = 1;
            }
        } catch (NullPointerException e) {
            strigoiNum = 1;
            spiritNum = 1;
        }

        testReqFormDB = new getReq(strigoiNum, spiritNum);
        testReqFormDB.parsedResponse = "";
        Thread TRFmDB = new Thread(testReqFormDB);
        TRFmDB.start();

        // mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void writeErrorToScreen() {
        String errorMessage = "Registrar file not found, creating now.\nPlease relaunch the app.";
        mText.setValue(errorMessage);

    }
}
