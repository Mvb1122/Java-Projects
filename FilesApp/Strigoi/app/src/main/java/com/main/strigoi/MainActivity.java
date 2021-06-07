package com.main.strigoi;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.main.strigoi.databinding.ActivityMainBinding;
import com.main.strigoi.mDB.mDBRequest;
import com.main.strigoi.ui.Reader;
import com.main.strigoi.ui.Requests;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    public static boolean get1x1;
    public static String content;
    // private static Reader reader;
    private ActivityMainBinding binding;
    private static Context context;
    // public static String address;
    public static EditText strigoiNum;
    public static EditText spiritNum;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Only put code to run on app startup here.

        super.onCreate(savedInstanceState);

        MainActivity.context = getApplicationContext();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.get1x1 = true;

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

        Thread userNameDisplayDelayed = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                EditText usernameDisplay = findViewById(R.id.userNameInputBox);
                Reader reader = new Reader();
                String registrarString = "{ \"exists\": false }";
                try {
                    registrarString = reader.readFile("Registrar.json");
                    JSONObject registrar = new JSONObject(registrarString);
                    Requests getUserInfo = new Requests("https://ihaveawebsite.tk/users/" + registrar.getInt("userId") + ".json", "GET", "None");
                    getUserInfo.run();
                    JSONObject onlineUserInfo = new JSONObject(getUserInfo.response);
                    usernameDisplay.setHint(onlineUserInfo.getString("username"));
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        userNameDisplayDelayed.start();

    }

    public static Context getContext() {
        if (MainActivity.context.toString().length() > 0) {
            return MainActivity.context;
        } else {
            System.out.println("ERROR: No Context.");
        }
        return MainActivity.context;
    }

    public void makeRequests(View view) {
            get1x1 = false;
            int strigoiNum;
            int spiritNum;
            try {
                String strNumE = MainActivity.strigoiNum.getText().toString();
                String spiritNumE = MainActivity.spiritNum.getText().toString();
                if (!(strNumE.equals("") || spiritNumE.equals(""))) {
                    strigoiNum = parseInt(strNumE);
                    spiritNum = parseInt(spiritNumE);
                } else {
                    strigoiNum = 1;
                    spiritNum = 1;
                }
            } catch (NullPointerException e) {
                strigoiNum = 1;
                spiritNum = 1;
            }

            mDBRequest buttonReq = new mDBRequest(strigoiNum, spiritNum);
        int finalStrigoiNum = strigoiNum;
        int finalSpiritNum = spiritNum;
        Thread TRFmDB = new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void run() {
                    buttonReq.run();
                    MainActivity.content = buttonReq.parsedResponse;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("\n\n\nStrigoiNum: " + finalStrigoiNum + "\nSpiritNum: " + finalSpiritNum + "\nPARSED RESPONSE:" + MainActivity.content + "\n\n\n");
                }
            });
            TRFmDB.start();
    }

    public void postText(View view) {
        System.out.println("Post button clicked.");
        int strigoiNum;
        int spiritNum;
        try {
            String strNumE = MainActivity.strigoiNum.getText().toString();
            String spiritNumE = MainActivity.spiritNum.getText().toString();
            if (!(strNumE.equals("") || spiritNumE.equals(""))) {
                strigoiNum = parseInt(strNumE);
                spiritNum = parseInt(spiritNumE);
            } else {
                strigoiNum = 1;
                spiritNum = 1;
            }
        } catch (NullPointerException e) {
            strigoiNum = 1;
            spiritNum = 1;
        }


        EditText prim = findViewById(R.id.newContentInput);
        Editable primData = prim.getText();
        String data = "{\n\t\"content\": [\n\t\t\"" + primData + "\"\n\t]\n}";

        Requests baseInfo = new Requests("https://ihaveawebsite.tk/json/" + strigoiNum + "/" + spiritNum + "/1.json", "GET", "None");
        baseInfo.response = "";
        Thread baseGetter = new Thread(baseInfo);
        baseGetter.start();
        int panelNum;
        try {
             JSONObject response = new JSONObject(baseInfo.response);
             panelNum = response.getInt("length") + 1;
             System.out.println("\n\n\n Length: " + panelNum);
        } catch (JSONException e) {
            panelNum = 5;
        }
        Requests postReq = new Requests("https://ihaveawebsite.tk/json/" + strigoiNum + "/" + spiritNum + "/" + panelNum + ".json", "POST", data);
        Thread poster = new Thread(postReq);
        poster.start();
    }

    public void updateUsername(View view) {
        // TODO: write code to post value from R.id.userNameInputBox to ./users/${userId}.json
        MutableLiveData<String> mText = new MutableLiveData<>();

        JSONObject userInfo = null;
        try {
            userInfo = new JSONObject("{ \"exists\": false }");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Reader reader = new Reader();
        try {
            String inputString = reader.readFile("Registrar.json");
            userInfo = new JSONObject(inputString);
        } catch (IOException | JSONException e) {
            System.out.println("ERROR: No user Information on Update button press.");
        }

        try {
            Requests getUserInf = new Requests("https://ihaveawebsite.tk/users/" + userInfo.getInt("userId") + ".json", "GET", "None");
            Thread processor = new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void run() {
                    getUserInf.run();
                    try {
                        JSONObject userInfo = new JSONObject(getUserInf.response);
                        int userId = userInfo.getInt("userId");
                        EditText newUserName = findViewById(R.id.userNameInputBox);

                        String jsonData = "{\n\t\"userId\": \"" + userId + "\",\n\t\"username\": \"" + newUserName.getText() + "\"\n}";
                        Requests poster = new Requests("https://ihaveawebsite.tk/users/" + userInfo.getInt("userId") + ".json", "POST", jsonData);
                        Thread posterT = new Thread(poster);
                        posterT.start();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            processor.start();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    // public Requests getRequests() { return getEx; }

}