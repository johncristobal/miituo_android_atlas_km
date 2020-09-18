package com.miituo.atlaskm.threats;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.IOException;

import com.miituo.atlaskm.api.ApiClient;
import com.miituo.atlaskm.utils.SimpleCallBack;

public class GetUrlAsync extends AsyncTask<String, Void, Void> {


    Activity c;
    SimpleCallBack cb;
    String resp=null, url="";
    boolean status = false;
    public ProgressDialog progress;

    //Constructor
    public GetUrlAsync (Activity c, String url, SimpleCallBack cb) {
        this.c = c;
        this.url = url;
        this.cb = cb;
        progress = new ProgressDialog(c);
    }

    @Override
    protected void onPreExecute() {
        progress.setMessage("Recuperando datos...");
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.show();
    }

    @Override
    protected Void doInBackground(String... strings) {
        ApiClient ac = new ApiClient(c);
        try{
            status = false;
            resp = ac.getUrl(url);
            status = true;
        }
        catch (IOException e) {
            e.printStackTrace();
            resp = e.getMessage();
            status = false;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(progress!=null){
            progress.dismiss();
        }
        cb.run(status,resp);
    }
}
