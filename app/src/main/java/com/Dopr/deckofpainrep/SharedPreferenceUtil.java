package com.Dopr.deckofpainrep;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {
    public static final String APP_SHARED_PREFS = "thisApp.SharedPreference";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferenceUtil(Context context) {
        this.sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    // test
    public void setSharedTest(String test) {
        editor.putString("test", test);
        editor.commit();
    }
    public String getSharedTest() {
        return sharedPreferences.getString("test", "defValue");
    }

    // 세트 수
    public void setSet(int set) {
        editor.putInt("setNum", set);
        editor.commit();
    }
    public int getSet() {
        return sharedPreferences.getInt("setNum", 54);
    }

    // 카드별 횟수
    public void setACardCount(int count) {
        editor.putInt("aCard", count);
        editor.commit();
    }
    public void setJCardCount(int count) {
        editor.putInt("jCard", count);
        editor.commit();
    }
    public void setQCardCount(int count) {
        editor.putInt("qCard", count);
        editor.commit();
    }
    public void setKCardCount(int count) {
        editor.putInt("kCard", count);
        editor.commit();
    }
    public void setJokerCardCount(int count) {
        editor.putInt("jokerCard", count);
        editor.commit();
    }

    public int getACardCount() {
        return sharedPreferences.getInt("aCard", 10);
    }
    public int getJCardCount() {
        return sharedPreferences.getInt("jCard", 10);
    }
    public int getQCardCount() {
        return sharedPreferences.getInt("qCard", 10);
    }
    public int getKCardCount() {
        return sharedPreferences.getInt("kCard", 10);
    }
    public int getJokerCardCount() {
        return sharedPreferences.getInt("jokerCard", 11);
    }

    // 운동 종류 설정

    public void setHeartType(String type) {
        editor.putString("heartType", type);
        editor.commit();
    }public void setDiamondType(String type) {
        editor.putString("diamondType", type);
        editor.commit();
    }public void setCloverType(String type) {
        editor.putString("cloverType", type);
        editor.commit();
    }public void setSpadeType(String type) {
        editor.putString("spadeType", type);
        editor.commit();
    }

    public String getHeartType() {return sharedPreferences.getString("heartType", "스쿼트");}
    public String getDiamondType() {return sharedPreferences.getString("diamondType", "스쿼트");}
    public String getCloverType() {return sharedPreferences.getString("cloverType", "왼발 런지");}
    public String getSpadeType() {return sharedPreferences.getString("spadeType", "오른발 런지");}

}
