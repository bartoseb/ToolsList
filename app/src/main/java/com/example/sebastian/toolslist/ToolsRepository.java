package com.example.sebastian.toolslist;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class ToolsRepository {
    private final Context context;
    private final String DB_PATH_KEY = "db_path";
    private final String db_path;

    public ToolsRepository(Context context){
        this.context = context;
        SharedPreferences prefs = context.getSharedPreferences("general_settings", Context.MODE_PRIVATE);
        db_path = prefs.getString(DB_PATH_KEY, null);
    }

    public List<String> getToolsList(String filter){
        if(db_path != null){


        }

        return null;
    }

}
