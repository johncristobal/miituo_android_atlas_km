package com.miituo.atlaskm.activities;

import android.app.Activity;
import android.app.Application;

import java.util.List;

import com.miituo.atlaskm.data.InfoClient;

public class GlobalActivity extends Application {

    private List<InfoClient> polizas;

    public List<InfoClient> getPolizas() {
        return polizas;
    }

    public void setPolizas(List<InfoClient> polizas) {
        this.polizas = polizas;
    }
}
