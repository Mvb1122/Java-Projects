package com.ihaveawebsitetk.marinaracoder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

import marinara.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // System.out.println("11 equals " +Coder.decode("11"));
        // System.out.println("b equals " + Coder.encode('b'));

        FragmentManager fm1 = getSupportFragmentManager();
        FragmentTransaction transaction1 = fm1.beginTransaction();
        transaction1.replace(R.id.contentViewContainer, new mainMenuFragment());
        transaction1.commit();
    }
}