package com.main.strigoi.ui.dashboard;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.main.strigoi.MainActivity;
import com.main.strigoi.mDB.mDBRequest;

import static java.lang.Integer.parseInt;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public static mDBRequest testReqFormDB;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public DashboardViewModel() {
        mText = new MutableLiveData<>();

        if (MainActivity.get1x1) {
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

            testReqFormDB = new mDBRequest(strigoiNum, spiritNum);
            testReqFormDB.parsedResponse = "";
            Thread TRFmDB = new Thread(testReqFormDB);
            TRFmDB.start();
            mText.setValue(testReqFormDB.parsedResponse);
        }
    }

    public LiveData<String> getText() {
        try {
            mText.setValue(MainActivity.content);
        } catch (NullPointerException e) {
            mText.setValue("huh");
        }
        return mText;
    }

    public void writeErrorToScreen() {
        String errorMessage = "Registrar file not found, creating now.\nPlease relaunch the app.";
        mText.setValue(errorMessage);

    }
}
