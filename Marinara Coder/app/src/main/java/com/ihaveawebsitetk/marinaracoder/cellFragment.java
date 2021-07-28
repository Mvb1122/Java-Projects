package com.ihaveawebsitetk.marinaracoder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class cellFragment extends Fragment {
    private String text;

    public cellFragment(String text) {
        super(R.layout.cell);
        this.text = text;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Thread doWhenVisible = new Thread(() -> {
            do {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (getActivity().findViewById(R.id.cellText) == null);

            getActivity().runOnUiThread(() -> {
                TextView cellText = getActivity().findViewById(R.id.cellText);
                cellText.setText(text);
            });
        });
        doWhenVisible.start();

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
