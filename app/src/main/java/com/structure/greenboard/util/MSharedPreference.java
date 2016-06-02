package com.structure.greenboard.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by abhishekdewa on 5/23/2016.
 */
public class MSharedPreference {
    private static String PREFERENCE_Name = "";
    private SharedPreferences sharedPreferences;
    private static MSharedPreference mSharedPreference;

    private MSharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_Name, Context.MODE_PRIVATE);
        mSharedPreference = this;
    }

    public static MSharedPreference getSharedPref(Context context) {
        if (mSharedPreference == null)
            new MSharedPreference(context);
        return mSharedPreference;
    }

    public void addGcmId(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("gcmId", token);
        editor.apply();
    }

    public String getGcmId() {
        String token = sharedPreferences.getString("gcmId", null);
        return token;
    }

    public void setTutorialFlag() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("tutorial", 1);
        editor.apply();
    }

    public int getTutorialFlag() {
        return sharedPreferences.getInt("tutorial", -1);
    }
}
