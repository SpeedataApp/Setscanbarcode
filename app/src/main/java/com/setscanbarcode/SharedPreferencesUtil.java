package com.setscanbarcode;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * Created by suntianwei on 2017/4/14.
 */

public class SharedPreferencesUtil {
    private android.content.SharedPreferences sharedPreferences;
    private android.content.SharedPreferences.Editor editor;
    private static SharedPreferencesUtil preferencesUtil = null;


    @SuppressLint("WrongConstant")
    public SharedPreferencesUtil(Context context, String key) {
        sharedPreferences = context.getSharedPreferences(key, Context.MODE_APPEND);
        editor = sharedPreferences.edit();
    }

    public static SharedPreferencesUtil getInstance(Context context, String filename) {
        if (preferencesUtil == null) {
            preferencesUtil = new SharedPreferencesUtil(context, filename);
        }
        return preferencesUtil;
    }

    public void write(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void write(String key, boolean[] value) {
        for (int i = 0; i < value.length; i++) {
            editor.putBoolean(key, value[i]);
        }
        editor.commit();
        editor.apply();
    }


    public void write(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String read(String key, String defValue) {
        String string = defValue;
        try {
            string = sharedPreferences.getString(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public boolean read(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    public boolean []read(String key, boolean[] defValue) {
        boolean []booleens = new boolean[defValue.length];
//        boolean[] booleens = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false};
        if (defValue != null) {
            for (int i = 0; i < defValue.length; i++) {
                booleens[i] =sharedPreferences.getBoolean(key, defValue[i]);
            }
        }
        return booleens;
//        return false;
    }

    public void delete(String key) {
        editor.remove(key);
        editor.commit();
    }
}
