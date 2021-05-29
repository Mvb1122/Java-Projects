package com.main.filesapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.main.filesapp.databinding.FragmentSecondBinding;
import com.main.filesapp.MainActivity;
import com.main.reader.*;

import java.io.IOException;

public class SecondFragment extends Fragment {
    private Reader reader;
    private FragmentSecondBinding binding;
    private TextView readText;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        /* TODO: Alright, now that I've got your attention,
                 Just remember that this is where you should
                 Put code for F2 running.
         */
        // TODO: Remember that F2 is fragment 2. Alright thanks.

        super.onViewCreated(view, savedInstanceState);
        // System.out.println("Swapped to F2.");

        readText = binding.textviewSecond;
        try {
            readText.setText(reader.readFile("test.txt", getContext()));
        } catch (IOException e) {
            readText.setText((CharSequence) e);
            e.printStackTrace();
        }

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}