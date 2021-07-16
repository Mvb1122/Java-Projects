package com.main.strigoi.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.main.strigoi.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    public static int shownSpirit;
    public static int shownStrigoi;

    public static void setShownSpirit(int shownSpirit) {
        System.out.println("Shown Spirit set to " + shownSpirit);
        NotificationsFragment.shownSpirit = shownSpirit;
    }

    public static void setShownStrigoi(int shownStrigoi) {
        System.out.println("Shown Strigoi set to " + shownStrigoi);
        NotificationsFragment.shownStrigoi = shownStrigoi;
    }

    public static void setShownStrigoiAndSpirit(int shownSpirit, int shownStrigoi) {
        System.out.println("Shown Strigoi and Spirit set to: Spirit " + shownSpirit + " Strigoi: " + shownStrigoi);
        NotificationsFragment.shownSpirit = shownSpirit;
        NotificationsFragment.shownStrigoi = shownStrigoi;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { textView.setText(s); }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}