package com.example.coolweather.util;

import android.app.Person;
import android.text.TextUtils;


import com.example.coolweather.db.Province;
import com.example.coolweather.gson.ProvenceGson;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GsonUtility {
    public static boolean ghandleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)) {
            try {
                Gson gson = new Gson();
                List<ProvenceGson> provenceGsonList = gson.fromJson(response, new TypeToken<List<ProvenceGson>>(){}.getType());
                for (ProvenceGson p : provenceGsonList){
                    Province province = new Province();
                    province.setProvinceCode(p.getId());
                    province.setProvinceName(p.getName());
                    province.save();
                }
                return true;
            } catch (JsonIOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
