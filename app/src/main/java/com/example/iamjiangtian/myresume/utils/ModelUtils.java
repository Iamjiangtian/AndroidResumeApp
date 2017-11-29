package com.example.iamjiangtian.myresume.utils;
//import

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by iamjiangtian on 12/14/16.
 */
public class ModelUtils{
    private static final String PREF_NAME = "prefFile";
    private static Gson gson = new Gson();
  public static void write(Context context, Object object, String dataName){
      SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
      String json = gson.toJson(object);
      SharedPreferences.Editor editor = sp.edit();
      editor.putString(dataName, json);
      editor.apply();
  }
  public static<T> T read(Context context, Type T, String dataName){
      SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
      String json = sp.getString(dataName, "");
      return gson.fromJson(json, T);
      //return retObj;
  }

}

