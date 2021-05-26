package com.example.ExponentialsApp;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {
    private TextView message;
    private TextView konaText;
    private TextView initValText;
    private TextView percentRemovedText;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = findViewById(R.id.equationShower);
        konaText = findViewById(R.id.konaText);
        ImageView droid = findViewById(R.id.konaImage);

        //Define and attach click listener
        droid.setOnClickListener(v -> tapKona());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void tapKona() {
        initValText = findViewById(R.id.initVal);
        int initValTextNum = Integer.parseInt(initValText.getText().toString());
        int startingValue;
        if (initValTextNum >= 1) {
            // System.out.println(initValTextNum);
            startingValue = initValTextNum;
        } else {
            startingValue = 100;
        }

        percentRemovedText = findViewById(R.id.precentRemoved);
        // message.setText("" + startingValue + "");

        int prText = Integer.parseInt(percentRemovedText.getText().toString());
        int percentRemoved;

        if (prText >= 1) {
            percentRemoved = prText;
        } else {
            percentRemoved = 10;
        }

        StringBuilder text = new StringBuilder("Values:\n\nx = 0 y = " + startingValue);

        double decVal = startingValue;
        for (int i = 1; i < 11; i++) {
            decVal = decVal * (0.01 * (100 - percentRemoved));
            int opV = Math.toIntExact((long) startingValue);
            text.append("\nx = " + i + " y = " + decVal);
        }
        message.setText(text.toString());
        String outputKonaText = "Here are your values, Nii-sama~~";
        konaText.setText(outputKonaText);
    }
}