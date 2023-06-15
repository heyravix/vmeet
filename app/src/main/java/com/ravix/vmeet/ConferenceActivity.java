package com.ravix.vmeet;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.zegocloud.uikit.plugin.invitation.components.ZegoAcceptInvitationButton;
import com.zegocloud.uikit.plugin.invitation.components.ZegoStartInvitationButton;
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceConfig;
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceFragment;
import com.zegocloud.uikit.prebuilt.videoconference.config.ZegoBottomMenuBarConfig;

import java.util.UUID;

public class ConferenceActivity extends AppCompatActivity {

    String conferenceID =" ";
    String id =" ";
    String name = " ";
    String meetID ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name  =extras.getString("username");
            meetID = extras.getString("meetingCode");

        }

        id = getUniqueId();
        addFragment();


    }

    public void addFragment() {
        long appID = 640883823;
        String appSign = "7d2ad43ed2abcb257976914ba26b3cd424ee0c672c6e1fb200632d0e6012de66";

        String conferenceID = meetID;
        String userID = id;
        String userName = name;

        ZegoUIKitPrebuiltVideoConferenceConfig config = new ZegoUIKitPrebuiltVideoConferenceConfig();
        ZegoUIKitPrebuiltVideoConferenceFragment fragment = ZegoUIKitPrebuiltVideoConferenceFragment.newInstance(appID, appSign, userID, userName,conferenceID,config);



          getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow();
    }

    String getUniqueId(){
        return UUID.randomUUID().toString();
    }

}