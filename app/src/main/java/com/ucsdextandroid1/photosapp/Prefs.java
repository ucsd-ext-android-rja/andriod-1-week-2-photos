package com.ucsdextandroid1.photosapp;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;

/**
 * Created by rjaylward on 4/13/19
 */
public class Prefs {

    private static final String PREFS_NAME = "photosapp-prefs";

    private static final String USERNAME = "username";

    public void setUsername(Context context, String username) {
        getEditor(context).putString(USERNAME, username);
    }

    @Nullable
    public String getUsername(Context context) {
        return getPreferences(context).getString(USERNAME, null);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
    }

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

}
