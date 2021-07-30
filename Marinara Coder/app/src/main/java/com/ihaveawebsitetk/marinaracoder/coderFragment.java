package com.ihaveawebsitetk.marinaracoder;


import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import marinara.Coder;

public class coderFragment extends Fragment {
    private Boolean mode;
    /**
     * True means that Debug is enabled, False means it's disabled.
     */
    protected static Boolean debugEnabled;
    static {
        // Set debug to be disabled by default.
        debugEnabled = false;
    }

    /**
     * @param mode The mode of the fragment, True is Encrypt, False is Decrypt.
     */
    public coderFragment(Boolean mode) {
        super(R.layout.fragment_coder);
        this.mode = mode;
    }

    /**
     * @param setup The debug mode to put the fragments into; True means that debug is involved.
     */
    public static void setDebug(Boolean setup) {
        debugEnabled = setup;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Activity activity = getActivity();

        Thread doThisIn1Second = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (getActivity().findViewById(R.id.coderModeTESTtext) != null) break;
            }

            getActivity().runOnUiThread(() -> {
                if (debugEnabled) {
                    TextView modeDisplay = activity.findViewById(R.id.coderModeTESTtext);
                    modeDisplay.setText("DEBUG: " + mode.toString());
                }

                Button backButton = activity.findViewById(R.id.backButton);
                backButton.setTextSize(15f);
                backButton.setBackgroundColor(-16777216);
                backButton.setTextColor(-1);

                backButton.setOnClickListener(v -> {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.contentViewContainer, new mainMenuFragment());
                    transaction.commit();
                });

                EditText input = activity.findViewById(R.id.input);
                TextView output = activity.findViewById(R.id.output);
                if (mode) {
                    input.setText("Your text to convert.");
                } else {
                    input.setText("The codes you have");
                }
                output.setText("The outputted text.");

                input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String parsedOutput = "";
                        if (mode) {
                            // Encode
                            System.out.println("Encoding.");
                            for (int i = 0; i < s.length(); i++) {
                                if (s.charAt(i) == '\n') {
                                    parsedOutput += '\n';
                                    continue;
                                }

                                parsedOutput += Coder.encode(Character.toLowerCase(s.charAt(i)));
                            }
                        } else {
                            if (s.length() >= 1) {
                                for (int i = 0; i < s.length(); i += 2) {
                                    // Ensure that the characters in question are both numbers
                                    try {
                                        try {
                                            try {
                                                char first = s.charAt(i);
                                                char second = s.charAt(i + 1);
                                                int num1 = Integer.parseInt(String.valueOf(first));
                                                int num2 = Integer.parseInt(String.valueOf(second));
                                                parsedOutput += Coder.decode(("" + num1 + num2).toLowerCase());
                                            } catch (IndexOutOfBoundsException e) {
                                                // Do nothing.
                                            }
                                        } catch (NumberFormatException e) {
                                            // Do nothing.
                                        }
                                    } catch (NumberFormatException e) {
                                        parsedOutput += s.charAt(i) + s.charAt(i + 1);
                                    }
                                }
                            }
                        }
                        output.setText(parsedOutput);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {}
                });
            });
        });
        doThisIn1Second.start();

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
