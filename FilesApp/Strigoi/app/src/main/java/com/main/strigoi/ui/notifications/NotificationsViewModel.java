package com.main.strigoi.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.main.strigoi.ui.dashboard.DashboardViewModel;

public class NotificationsViewModel extends ViewModel {



    private MutableLiveData<String> mText;

    public NotificationsViewModel() {

        mText = new MutableLiveData<>();

        try {
            mText.setValue(DashboardViewModel.testReqFormDB.parsedResponse);
        } catch (NullPointerException e) {
            mText.setValue("Please click on the Dashboard button.");
        }
    }

    public LiveData<String> getText() {
        return mText;
    }


}