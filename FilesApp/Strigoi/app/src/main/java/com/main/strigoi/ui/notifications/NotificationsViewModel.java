package com.main.strigoi.ui.notifications;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.main.strigoi.MainActivity;

public class NotificationsViewModel extends ViewModel {



    private MutableLiveData<String> mText;

    public NotificationsViewModel() {

        mText = new MutableLiveData<>();

        // TODO: Learn how to make new threads, then implement it here.

        String content = MainActivity.getEx.response;
        mText.setValue(MainActivity.address + "\n\n\n" + content);
    }

    public LiveData<String> getText() {
        return mText;
    }


}