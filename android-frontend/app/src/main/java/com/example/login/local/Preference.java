package com.example.login.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.login.BuildConfig;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class Preference {
    private static HashMap<String, SharedPreferences> mPreferences = new HashMap<>();

    private static String getPrefFileName(String prefFile) {
        return new StringBuilder(BuildConfig.APPLICATION_ID)
                .append('.')
                .append(prefFile)
                .toString();
    }

    public static void setPreference(Context context, String prefFile, String key, String value) {
        String prefFileName = getPrefFileName(prefFile);
        SharedPreferences preferences;
        if (mPreferences.containsKey(prefFileName)) {
            preferences = mPreferences.get(prefFileName);
        } else {
            preferences = context.getSharedPreferences(prefFileName, MODE_PRIVATE);
            mPreferences.put(prefFileName, preferences);
        }
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putString(key, value);
        preferencesEditor.apply();
    }

    public static void setPreference(Context context, String prefFile, Map<String, String> preferenceMap) {
        String prefFileName = getPrefFileName(prefFile);
        SharedPreferences preferences;
        if (mPreferences.containsKey(prefFileName)) {
            preferences = mPreferences.get(prefFileName);
        } else {
            preferences = context.getSharedPreferences(prefFileName, MODE_PRIVATE);
            mPreferences.put(prefFileName, preferences);
        }
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        Iterator it = preferenceMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) it.next();
            preferencesEditor.putString(entry.getKey(), entry.getValue());
        }
        preferencesEditor.apply();
    }

    public static String getPreference(Context context, String prefFile, String key) {
        String prefFileName = getPrefFileName(prefFile);
        SharedPreferences preferences;
        if (mPreferences.containsKey(prefFileName)) {
            preferences = mPreferences.get(prefFileName);
        } else {
            preferences=context.getSharedPreferences(prefFileName, MODE_PRIVATE);
            mPreferences.put(prefFile, preferences);
        }
        return preferences.getString(key, null);
    }

    public static void clearPreference(String prefFile) {
        String prefFileName = getPrefFileName(prefFile);
        SharedPreferences preferences;
        if (mPreferences.containsKey(prefFileName)) {
            preferences = mPreferences.get(prefFileName);
            SharedPreferences.Editor preferencesEditor = preferences.edit();
            preferencesEditor.clear();
            preferencesEditor.apply();
        }
    }
}
