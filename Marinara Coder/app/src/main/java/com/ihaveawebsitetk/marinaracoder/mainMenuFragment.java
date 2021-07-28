package com.ihaveawebsitetk.marinaracoder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class mainMenuFragment extends Fragment {
    public mainMenuFragment() {
        super(R.layout.fragment_main_menu);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Thread doThisIn1Second = new Thread(() -> {
            do {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (getActivity().findViewById(R.id.menuDisplay) == null);

            getActivity().runOnUiThread(() -> {
                Button menuDisplay = (Button) findViewById(R.id.menuDisplay);
                menuDisplay.setTextSize(50f);
                menuDisplay.setBackgroundColor(-16777216);
                menuDisplay.setTextColor(-1);

                Button gotoEncode = (Button) findViewById(R.id.gotoEncode);
                gotoEncode.setTextSize(25f);
                gotoEncode.setBackgroundColor(-16777216);
                gotoEncode.setTextColor(-1);

                Button gotoDecode = (Button) findViewById(R.id.gotoDecode);
                gotoDecode.setTextSize(25f);
                gotoDecode.setBackgroundColor(-16777216);
                gotoDecode.setTextColor(-1);

                FloatingActionButton gotoHelp = (FloatingActionButton) findViewById(R.id.questionButton);
                gotoHelp.setOnClickListener(v -> {
                    System.out.println("Help Button hit.");

                    helpFragment helpFragment = new helpFragment();
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.contentViewContainer, helpFragment);
                    transaction.commit();
                });

                gotoDecode.setOnClickListener(v -> {
                    System.out.println("Button hit.");

                    coderFragment decodeFrag = new coderFragment(false);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.contentViewContainer, decodeFrag);
                    transaction.commit();
                });

                gotoEncode.setOnClickListener(v -> {
                    System.out.println("Button hit.");

                    coderFragment encodeFrag = new coderFragment(true);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.contentViewContainer, encodeFrag);
                    transaction.commit();
                });
            });
        });
        doThisIn1Second.start();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private View findViewById(int menuDisplay) {
        return getActivity().findViewById(menuDisplay);
    }
}
