package com.main.strigoi;


import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.main.strigoi.databinding.ActivityMainBinding;
import com.main.strigoi.ui.Reader;

public class MainActivity extends AppCompatActivity {

    private static Reader reader;
    private ActivityMainBinding binding;
    private static Context context;
    public static String address;
    public static EditText strigoiNum;
    public static EditText spiritNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Only put code to run on app startup here.

        super.onCreate(savedInstanceState);

        MainActivity.context = getApplicationContext();

        /*
        int randNum = (int) Math.floor(Math.random() * 50);
        address = "https://jsonplaceholder.typicode.com/todos/" + randNum;
        System.out.println("\n\n\n" + address + "\n\n\n");
        getEx = new Requests(address, "GET");
        Thread thread = new Thread(getEx);
        // thread.start();
         */

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        Thread strigoiAndSpiritNumLoop = new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    strigoiNum = findViewById(R.id.strigoiNumBoxInput);
                    spiritNum = findViewById(R.id.spiritBoxInput);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // Wake.
                    }
                } while (true);
            }
        });
        strigoiAndSpiritNumLoop.start();
    }
    public static Context getContext() {
        if (MainActivity.context.toString().length() > 0) {
            return MainActivity.context;
        } else {
            System.out.println("ERROR: No Context.");
        }
        return MainActivity.context;
    }

    // public Requests getRequests() { return getEx; }

}