package com.example.sebastian.toolslist;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ToolsRepository {
    private final Context context;
    private final String DB_PATH_KEY = "db_path";
    private final ArrayList<String> emptyList;
    private String db_path;
    private SQLiteDatabase database;

    public ToolsRepository(Context context){
        this.context = context;
        this.emptyList = new ArrayList<String>(1);
        this.emptyList.add("No records found");
    }

    public List<String> getToolsList(String filter){
        if(database!=null){
            Cursor cursor = null;
            if(filter == null || filter.isEmpty()){
                cursor = database.query("ToolItems",new String[]{"ToolName"}, null, null,null,null,"ToolName");
            }else{
                cursor = database.query("ToolItems",new String[]{"ToolName"}, "ToolName like ?", new String[]{ filter+"%"},null,null,"ToolName");
            }

            ArrayList<String> items = new ArrayList<String>();
            if (cursor.moveToFirst()) {

                while (cursor.isAfterLast() == false) {
                    String toolName = cursor.getString(cursor.getColumnIndex("ToolName"));
                    items.add(toolName);
                    cursor.moveToNext();
                }
                cursor.close();
                return items;
            }
        }

        return emptyList;
    }

    public void Initialize() {
        SharedPreferences prefs = context.getSharedPreferences("general_settings", Context.MODE_PRIVATE);
        db_path = prefs.getString(DB_PATH_KEY, null);
        if(db_path != null){
            File db_file = new File(db_path);
            if(db_file.exists()){
                database = SQLiteDatabase.openDatabase(db_path,null,SQLiteDatabase.OPEN_READONLY);
            }
        }

    }
}
