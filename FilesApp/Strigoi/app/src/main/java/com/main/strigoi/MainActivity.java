package com.main.strigoi;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.main.strigoi.databinding.ActivityMainBinding;
import com.main.strigoi.mDB.mDBRequest;
import com.main.strigoi.mDB.pushToDB;
import com.main.strigoi.ui.Reader;
import com.main.strigoi.ui.Requests;
import com.main.strigoi.ui.dashboard.DashboardFragmentSup;
import com.main.strigoi.ui.edit.EditingFragment;
import com.main.strigoi.ui.home.HomeFragmentTwo;
import com.main.strigoi.ui.userFragment.userFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

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
    public Boolean userFragmentActive;
    private JSONObject onlineUserInfo;
    private Boolean editFragmentActive;
    public int spiritNumInt;
    public int strigoiNumInt;
    private boolean getEditText;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Only put code to run on app startup here.
        userFragmentActive = false;
        super.onCreate(savedInstanceState);

        MainActivity.context = getApplicationContext();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        get1x1 = true;

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        Thread strigoiAndSpiritNumLoop = new Thread(() -> {
            do {
                strigoiNum = findViewById(R.id.strigoiNumBoxInput);
                spiritNum = findViewById(R.id.spiritBoxInput);
                if (strigoiNum != null) {
                    try {
                        strigoiNumInt = Integer.parseInt(strigoiNum.getText().toString());
                    } catch (NumberFormatException e) {
                        if (strigoiNumInt != 0) {
                            strigoiNumInt = 1;
                        }
                    }
                }

                if (spiritNum != null) {
                    try {
                        spiritNumInt = Integer.parseInt(spiritNum.getText().toString());
                    } catch (NumberFormatException e) {
                        if (spiritNumInt != 0) {
                            spiritNumInt = 1;
                        }
                    }
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // Wake.
                }
            } while (true);
        });
        strigoiAndSpiritNumLoop.start();

        Thread userNameDisplayDelayed = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            EditText usernameDisplay = findViewById(R.id.userNameInputBox);
            Reader reader = new Reader();
            String registrarString;
            try {
                registrarString = reader.readFile("Registrar.json");
                JSONObject registrar = new JSONObject(registrarString);
                Requests getUserInfo = new Requests("https://ihaveawebsite.tk/users/" + registrar.getInt("userId") + ".json", "GET", "None");
                getUserInfo.run();
                onlineUserInfo = new JSONObject(getUserInfo.response);
                if (usernameDisplay != null) {
                    usernameDisplay.setHint(onlineUserInfo.getString("username"));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

        });
        userNameDisplayDelayed.start();


        // Setup UI on startup: (Home Fragment)
        Fragment fragment = new HomeFragmentTwo();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.contentFragment, fragment);
        transaction.commit();
        
        // Setup UI on visible: (Dashboard Fragment)
        editFragmentActive = false;
        Thread setDashboardFragment = new Thread(() -> {
            do {
                View view = findViewById(R.id.dashboardFragment);
                EditText editSpirit = findViewById(R.id.editSpiritText);

                if (view != null && spiritNum == null && strigoiNum == null && !editFragmentActive) {
                    System.out.println("Set to dashboard fragment.");
                    Fragment dashboardFragment = new DashboardFragmentSup();

                    FragmentManager fm2 = getSupportFragmentManager();
                    FragmentTransaction transaction2 = fm2.beginTransaction();
                    transaction2.replace(R.id.dashboardFragment, dashboardFragment);
                    transaction2.commit();

                    TextView loadingText = findViewById(R.id.loadingText);
                    loadingText.setVisibility(View.INVISIBLE);
                }

                if (editSpirit != null && editSpirit.getText().toString().equals("")) {
                    mDBRequest request = new mDBRequest(strigoiNumInt, spiritNumInt);

                    if (!getEditText) {
                        Thread getThisFromMDB = new Thread(() -> {
                            request.run();
                            while (request.parsedResponse == null) {
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            runOnUiThread(() -> editSpirit.setText(request.parsedResponse));
                        });
                        getThisFromMDB.start();
                        getEditText = true;
                    }
                }

                // Set the display for which spirit you're editing.
                TextView reminder = findViewById(R.id.editReminder);
                if (reminder != null) {
                    reminder.setText("You are editing Strigoi " + strigoiNumInt + ", Spirit " + spiritNumInt);
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        });
        setDashboardFragment.start();
        
    }

    public static Context getContext() {
        if (MainActivity.context.toString().length() > 0) {
            return MainActivity.context;
        } else {
            System.out.println("ERROR: No Context.");
        }
        return MainActivity.context;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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

            Thread TRFmDB = new Thread(() -> {
                buttonReq.run();
                MainActivity.content = buttonReq.parsedResponse;
                try {
                  Thread.sleep(500);
                } catch (InterruptedException e) {
                   e.printStackTrace();
               }
                System.out.println("\n\n\nStrigoiNum: " + finalStrigoiNum + "\nSpiritNum: " + finalSpiritNum + "\nPARSED RESPONSE:" + MainActivity.content + "\n\n\n");
            });
            TRFmDB.start();

        // Button goButton = findViewById(R.id.goButton);
        // goButton.setText("Spirit Retrieved.");
    }

    /*
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void postText(View view) {
        System.out.println("Post button clicked.");

        // Get the numbers from the input boxes.
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

        // Fetch content to post from the text input.
        EditText prim = findViewById(R.id.newContentInput);
        Editable primData = prim.getText();


        String[] primArr = primData.toString().split("\n");
        String output = "";
        for (int i = 0; i < primArr.length; i++) {
            String item = primArr[i];
            if (i == primArr.length - 1) {
                output += "\t\"" + item.replace("\"", "\\\"") + "\"\n\t";
            } else {
                output += "\t\"" + item.replace("\"", "\\\"") + "\",\n\t";
            }
        }

        // Create JSON Data to post to server.
        String data = "{\n\t\"content\": [\n\t" + output.substring(0, output.length() - 1) + "\n\t]\n}";

        // Get the info for the spirit from panel1.
        Requests baseInfo = new Requests("https://ihaveawebsite.tk/json/" + strigoiNum + "/" + spiritNum + "/1.json", "GET", "None");
        baseInfo.response = "";
        final int[] panelNum = new int[1];
        int finalStrigoiNum = strigoiNum;
        int finalSpiritNum = spiritNum;
        Thread baseGetter = new Thread(() -> {
            baseInfo.run();

            JSONObject response = null;
            try {
                response = new JSONObject(baseInfo.response);
            } catch (JSONException e) {
                String tempJSON = "{\n\t\"spiritType\": \"Text\",\n\"length\": 2,\n\"content\": [\n\t\"ERROR: No Content.\"\n\t]\n}";
                try {
                    response = new JSONObject(tempJSON);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }

            try {
                int length = response.getInt("length");
                panelNum[0] = length + 1;
                System.out.println("\n\n\n Length: " + panelNum[0]);
            } catch (JSONException e) {
                e.printStackTrace();
                panelNum[0] = 5;
            }

            // Post data.
            Requests postReq = new Requests("https://ihaveawebsite.tk/json/" + finalStrigoiNum + "/" + finalSpiritNum + "/" + panelNum[0] + ".json", "POST", data);
            postReq.run();

            // Increase length counter on panel1.
            String panel1Json;
            try {
                panel1Json = "{\n\t\"spiritType\": \"" + response.getString("spiritType") + "\",\n\t\"length\": " + (response.getInt("length") + 1) + ",\n\t\"content\": " + response.getJSONArray("content") + "\n}";
            } catch (JSONException e) {
                panel1Json = "{\n\t\"spiritType\": \"Text\",\n\"length\": 2,\n\"content\": [\n\t\"ERROR: No Content.\"\n\t]\n}";
                e.printStackTrace();
            }

            Requests postPanel1 = new Requests("https://ihaveawebsite.tk/json/" + finalStrigoiNum + "/" + finalSpiritNum + "/1.json", "POST", panel1Json);
            postPanel1.run();

            // Update Content display after 1 second.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            makeRequests(findViewById(R.id.updateUserButton));

        });
        baseGetter.start();
    }
    */

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateUsername(View view) {

        JSONObject userInfo = null;
        Reader reader = new Reader();
        try {
            String inputString = reader.readFile("Registrar.json");
            userInfo = new JSONObject(inputString);
        } catch (IOException | JSONException e) {
            System.out.println("ERROR: No user Information on Update button press.");
        }

        try {
            Requests getUserInf = new Requests("https://ihaveawebsite.tk/users/" + userInfo.getInt("userId") + ".json", "GET", "None");
            Thread processor = new Thread(() -> {
                getUserInf.run();
                try {
                    JSONObject userInfo1 = new JSONObject(getUserInf.response);
                    int userId = userInfo1.getInt("userId");
                    EditText newUserName = findViewById(R.id.userNameInputBox);

                    String jsonData = "{\n\t\"userId\": \"" + userId + "\",\n\t\"username\": \"" + newUserName.getText().toString().replace("\"", "\\\"") + "\"\n}";
                    Requests poster = new Requests("https://ihaveawebsite.tk/users/" + userInfo1.getInt("userId") + ".json", "POST", jsonData);
                    Thread posterT = new Thread(poster);
                    posterT.start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
            processor.start();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void goToUserFragment(View view) {
        if (userFragmentActive) {
            userFragmentActive = false;

            Button editUserButton = findViewById(R.id.editUserButton);
            editUserButton.setVisibility(View.VISIBLE);
            Fragment fragment = new HomeFragmentTwo();

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.contentFragment, fragment);
            transaction.commit();
        } else {
            userFragmentActive = true;

            Button editUserButton = findViewById(R.id.editUserButton);
            editUserButton.setVisibility(View.INVISIBLE);
            Fragment fragment = new userFragment();

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.contentFragment, fragment);
            transaction.commit();
        }

        System.out.println("Is the User Fragment active: " + userFragmentActive);
    }

    public void goToEditFragment(View view) {
        System.out.println("Edit Button Clicked");
        editFragmentActive = true;

        Fragment dashboardFragment = new EditingFragment();

        FragmentManager fm2 = getSupportFragmentManager();
        FragmentTransaction transaction2 = fm2.beginTransaction();
        transaction2.replace(R.id.dashboardFragment, dashboardFragment);
        getEditText = false;
        transaction2.commit();
    }

    public void goToDashboardFragment(View view) {
        System.out.println("Back button hit on the Edit Fragment");

        Fragment dashboardFragment = new DashboardFragmentSup();

        FragmentManager fm2 = getSupportFragmentManager();
        FragmentTransaction transaction2 = fm2.beginTransaction();
        transaction2.replace(R.id.dashboardFragment, dashboardFragment);
        transaction2.commit();
    }

    public void postFullToDB(View view) {
        EditText gottenText = findViewById(R.id.editSpiritText);
        String[] textArray = gottenText.getText().toString().split("\n");

        System.out.println(Arrays.toString(textArray));
        if (strigoiNumInt != 0 && spiritNumInt != 0) {
            pushToDB push = new pushToDB(strigoiNumInt, spiritNumInt, textArray);
            Thread pusher = new Thread(push);
            pusher.start();
        } else {
            CharSequence errorMessage = "You can't publish to Strigoi 0 and Spirit 0, it's empty for \"reasons.\"";
            Toast error = Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT);
            error.show();
        }
    }
}