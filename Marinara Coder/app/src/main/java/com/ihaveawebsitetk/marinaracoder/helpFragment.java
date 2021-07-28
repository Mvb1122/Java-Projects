package com.ihaveawebsitetk.marinaracoder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class helpFragment extends Fragment {
    public helpFragment() {
        super(R.layout.fragment_help_screen);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Thread doThisWhenShown = new Thread(() -> {
            do {
                if (getActivity().findViewById(R.id.helpScrollview) != null) break;

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (getActivity().findViewById(R.id.helpScrollview) == null);

            // Setup ScrollView
            getActivity().runOnUiThread(() -> {
                // Insert Guide
                guideFragment guideView = new guideFragment();

                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.add(R.id.helpScrollViewFrameLayout, guideView);
                transaction.commit();

                // Insert Codes Table
                tableFragment codesTable = new tableFragment();

                FragmentTransaction transaction1 = fm.beginTransaction();
                transaction1.add(R.id.helpScrollViewFrameLayout, codesTable);
                transaction1.commit();

                // Link button to go back.
                Button backButton = getActivity().findViewById(R.id.helpScreenBackButton);
                backButton.setOnClickListener(v -> {
                    FragmentTransaction transaction2 = fm.beginTransaction();
                    transaction2.replace(R.id.contentViewContainer, new mainMenuFragment());
                    transaction2.commit();
                });

                // Insert settings Fragment
                settingsFragment settingsFragment = new settingsFragment();

                FragmentTransaction transaction3 = fm.beginTransaction();
                transaction3.add(R.id.helpScrollViewFrameLayout, settingsFragment);
                transaction3.commit();
            });
        });
        doThisWhenShown.start();

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
