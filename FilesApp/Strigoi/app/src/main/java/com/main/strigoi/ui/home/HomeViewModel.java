package com.main.strigoi.ui.home;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.main.strigoi.ui.Reader;
import com.main.strigoi.ui.Requests;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private Reader reader;

    public HomeViewModel() throws JSONException {
        mText = new MutableLiveData<>();

        JSONObject userInfo;
        reader = new Reader();
        try {
            String inputString = reader.readFile("Registrar.json");
            userInfo = new JSONObject(inputString);
        } catch (IOException | JSONException e) {
            int userId = (int) Math.floor(Math.random() * 999999);
            String jsonData = "{\n\t\"userId\": \"" + userId + "\",\n\t\"username\": \"A new user\"\n}";

            Thread userDataPoster = new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void run() {
                    Requests uDP = new Requests("https://micahb.dev/users/" + userId + ".json", "POST", jsonData);
                    uDP.run();
                }
            });
            userDataPoster.start();

            userInfo = new JSONObject(jsonData);
            reader.writeFile(jsonData, "Registrar.json");
        }

        mText.setValue("Your userId is: " + userInfo.getInt("userId"));
    }

    public LiveData<String> getText() {
        return mText;
    }
}