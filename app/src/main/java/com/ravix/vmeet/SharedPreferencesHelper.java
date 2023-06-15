package com.ravix.vmeet;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    private static final String PREF_FILE_NAME = "MyPrefs";
    private static final String PREF_USERNAME = "username";

    private SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void saveUsername(String username) {
        sharedPreferences.edit().putString(PREF_USERNAME, username).apply();
    }

    public String getUsername() {
        return sharedPreferences.getString(PREF_USERNAME, null);
    }

    public boolean isUsernameAvailable() {
        return sharedPreferences.contains(PREF_USERNAME);
    }

    public void clearUsername() {
        sharedPreferences.edit().remove(PREF_USERNAME).apply();
    }
}
