package com.ravix.vmeet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText roomEditText;

    private SharedPreferencesHelper sharedPreferencesHelper;

    private static final String PREF_USERNAME = "username";
    private static final String PREF_FILE_NAME = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        Button enterButton = findViewById(R.id.createButton);
        Button createButton = findViewById(R.id.createRoomBtn);
        Button joinButton = findViewById(R.id.joinRoomBtn);
        roomEditText = findViewById(R.id.roomEditText);
         sharedPreferencesHelper = new SharedPreferencesHelper(this);


        if (sharedPreferencesHelper.isUsernameAvailable()) {
            showSecondLayout();
        } else {
            showFirstLayout();
        }

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the entered username
                String username = usernameEditText.getText().toString().trim();
                if (!username.isEmpty()) {
                    sharedPreferencesHelper.saveUsername(username);
                    showSecondLayout();
                }
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String meetingCode = generateMeetingCode();
                    startMeetingActivity(meetingCode);

            }
        });

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String meetingId = roomEditText.getText().toString().trim();
                if (!meetingId.isEmpty()) {
                    startMeetingActivity(meetingId);
                }
            }
        });
    }

    private void showFirstLayout() {
        ConstraintLayout firstLayout = findViewById(R.id.first_layout);
        ConstraintLayout secondLayout = findViewById(R.id.second_layout);

        firstLayout.setVisibility(View.VISIBLE);
        secondLayout.setVisibility(View.GONE);
    }

    private void showSecondLayout() {
        ConstraintLayout firstLayout = findViewById(R.id.first_layout);
        ConstraintLayout secondLayout = findViewById(R.id.second_layout);
        TextView welcomeText = findViewById(R.id.welcome_text);

        firstLayout.setVisibility(View.GONE);
        secondLayout.setVisibility(View.VISIBLE);
        String username = sharedPreferencesHelper.getUsername();
        String welcomeMessage = "Welcome, " + username + "! What would you like to do?";
        welcomeText.setText(welcomeMessage);
    }

    private String generateMeetingCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }

    private void startMeetingActivity(String meetingCode) {
        String username = sharedPreferencesHelper.getUsername();
        Intent intent = new Intent(MainActivity.this, ConferenceActivity.class);
        intent.putExtra("meetingCode", meetingCode);
        intent.putExtra("username", username);
        startActivity(intent);
    }

}
