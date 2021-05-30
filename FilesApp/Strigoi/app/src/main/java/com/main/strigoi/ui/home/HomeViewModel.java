package com.main.strigoi.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.main.strigoi.MainActivity;
import com.main.strigoi.ui.Reader;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private Reader reader;

    public HomeViewModel() {
        mText = new MutableLiveData<>();

        mText.setValue("Hello?");

        /*
        if (reader.fileExists("Registrar", "json")) {
            mText.setValue(reader.readFile("Registrar.json"));
        } else {
            mText.setValue("Registrar file not found.");
        }
         */
    }

    public LiveData<String> getText() {
        return mText;
    }
}