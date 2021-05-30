package com.main.strigoi.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.main.strigoi.ui.Reader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private Reader reader = new Reader();

    public DashboardViewModel() throws JSONException {
        mText = new MutableLiveData<>();

        if (reader.fileExists("Registrar", "json")) {
            String registrarFileValue = reader.readFile("Registrar.json");
            if (registrarFileValue.startsWith("java.io")) {
                writeErrorToScreen();
            } else {
                JSONObject registrar = new JSONObject(registrarFileValue);
                mText.setValue(registrar.getString("content"));
            }

        } else {
            writeErrorToScreen();
        }

        // mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void writeErrorToScreen() {
        String errorMessage = "Registrar file not found, creating now.\nPlease relaunch the app.";
        mText.setValue(errorMessage);
        String data = "Hello, if you can see me, that means that the registrar file exists.";
        String jsonData = "{\n\t\"content\": \"" + data + "\"\n}";
        reader.writeFile(jsonData, "Registrar.json");
    }
}
