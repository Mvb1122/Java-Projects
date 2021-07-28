package com.ihaveawebsitetk.marinaracoder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class settingsFragment extends Fragment {
    public settingsFragment() {
        super(R.layout.fragment_settings);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Thread doWhenVisible = new Thread(() -> {
            do {
                if (getActivity().findViewById(R.id.settingsMenuDisplay) != null) break;

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (getActivity().findViewById(R.id.settingsMenuDisplay) == null);

            getActivity().runOnUiThread(() -> {
                Button debugSettingButton = getActivity().findViewById(R.id.debugModeButton);

                String status;
                if (coderFragment.debugEnabled) {
                    status = "On";
                } else {
                    status = "Off";
                }
                debugSettingButton.setText("Debug Mode: " + status);

                debugSettingButton.setOnClickListener(v -> {
                    System.out.println("debugModeButton hit. Status = " + coderFragment.debugEnabled);

                    Boolean debugStatus = coderFragment.debugEnabled;
                    coderFragment.setDebug(!debugStatus);

                    String debugStatusString;
                    if (coderFragment.debugEnabled) {
                        debugStatusString = "On";
                    } else {
                        debugStatusString = "Off";
                    }

                    debugSettingButton.setText("Debug Mode: " + debugStatusString);
                });
            });
        });
        doWhenVisible.start();

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
