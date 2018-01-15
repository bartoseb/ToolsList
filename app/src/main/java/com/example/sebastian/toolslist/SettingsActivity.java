package com.example.sebastian.toolslist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    private EditText et_view;
    private final String DB_PATH_KEY = "db_path";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        et_view = findViewById(R.id.et_db_path);

        loadSettings();
    }

    public void saveSettings(View view){
        SharedPreferences prefs = this.getSharedPreferences("general_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String db_path = et_view.getText().toString();
        editor.putString(DB_PATH_KEY, db_path);
        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void loadSettings(){
        SharedPreferences prefs = this.getSharedPreferences("general_settings", Context.MODE_PRIVATE);
        String defaultPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        String db_path = prefs.getString(DB_PATH_KEY, defaultPath+"/toolslist.sqlite");
        et_view.setText(db_path);
    }
}
