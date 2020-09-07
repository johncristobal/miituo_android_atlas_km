package com.miituo.atlaskm.activities;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.miituo.atlaskm.api.ApiClient;

public class BaseActivity extends AppCompatActivity {
    public void print(String c){
        if (ApiClient.ambiente!=4) {
            Log.d(this.getClass().getSimpleName(), "msg: " + c);
        }
    }
    public void toast(String text){
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }
}
