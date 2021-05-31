package com.main.strigoi.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.main.strigoi.MainActivity;

public class NotificationsViewModel extends ViewModel {



    private MutableLiveData<String> mText;

    public NotificationsViewModel() {

        mText = new MutableLiveData<>();


        // mText.setValue(MainActivity.testReqFormDB.address + "\n\n\n" + MainActivity.testReqFormDB.parsedResponse);
        mText.setValue(MainActivity.testReqFormDB.parsedResponse);

    }

    public LiveData<String> getText() {
        return mText;
    }


}