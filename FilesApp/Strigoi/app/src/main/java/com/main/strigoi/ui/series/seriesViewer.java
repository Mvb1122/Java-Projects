package com.main.strigoi.ui.series;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.main.strigoi.databinding.FragmentSeriesViewBinding;

public class seriesViewer extends Fragment {
    private seriesViewModel seriesViewModel;
    private FragmentSeriesViewBinding binding;
    private Activity activity;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public seriesViewer() {
        // Do nothing?
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = getActivity();

        seriesViewModel = new ViewModelProvider(this).get(seriesViewModel.class);

        binding = FragmentSeriesViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Thread refresher = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            seriesInfo.refreshAll();
        });
        refresher.start();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
