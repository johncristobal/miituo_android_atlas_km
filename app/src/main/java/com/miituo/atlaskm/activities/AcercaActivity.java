package com.miituo.atlaskm.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.miituo.atlaskm.R;

public class AcercaActivity extends AppCompatActivity {

    public ListView lista;
    public String [] opts;

    public WebView vista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Acerca de");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);*/

        //get back arrow
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView hola = (TextView)findViewById(R.id.textView57);
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            float version = Float.parseFloat(pInfo.versionName);
            hola.setText("Versión: "+version);
        }catch (Exception e){
            e.printStackTrace();
        }

        ImageButton back = (ImageButton)findViewById(R.id.BackButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lista = (ListView)findViewById(R.id.listaopciones);

        opts = new String[]{"Aviso de privacidad"};

        vista = (WebView)findViewById(R.id.webvista);
        String data = "<!DOCTYPE html>\n" +
                "<!--\n" +
                "To change this license header, choose License Headers in Project Properties.\n" +
                "To change this template file, choose Tools | Templates\n" +
                "and open the template in the editor.\n" +
                "-->\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>TODO supply a title</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "        <style>\n" +
                "            @font-face {\n" +
                "                font-family: 'feast';\n" +
                "                src: url('fonts/herne1.ttf');\n" +
                "            }\n" +
                "\n" +
                "            body {font-family: 'feast';}\n" +
                "        </style>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <div>\n" +
                "            <h4>Si manejas poco, pagas poco.</h4>\n" +
                "            <p align=\"left\">\n" +
                "            El único seguro de autos que te ofrece pago por kilómetro.\n" +
                "            <br><br>\n" +
                "            En <b>miituo</b> hemos creado un seguro de auto donde solamente pagas por kilómetro recorrido.  \n" +
                "            Es muy fácil, tu tarifa es por kilómetro por lo que solamente pagas por lo que recorriste durante el mes y no \n" +
                "            importa si solamente fue 1 o 1,000 kilómetros ya que siempre estarás cubierto con alguno de nuestros planes \n" +
                "            (amplio, limitado o responsabilidad civil).\n" +
                "            <br><br>\n" +
                "            Nuestra tarifa es fácil y clara.\n" +
                "            <br><br>\n" +
                "            Con base en la información que nos proporciones, nuestros algoritmos calcularán una prima por kilómetro. \n" +
                "            Esta será fija durante los doce meses de vigencia de la póliza y se multiplicará por los kilómetros que \n" +
                "            recorriste durante el mes. \n" +
                "\n" +
                "            <br><br>\n" +
                "            Independientemente de los kilómetros que recorriste, siempre estarás cubierto con tu seguro de auto <b>miituo</b>.\n" +
                "\n" +
                "            <br><br>\n" +
                "            ¡Asi de Fácil!\n" +
                "            </p>\n" +
                "\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>\n";

        vista.loadDataWithBaseURL("file:///android_asset/",data,"text/html","utf-8",null);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.textcenter,R.id.textItem, opts);

        lista.setAdapter(adapter);

        // ListView Item Click Listener
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String  itemValue = (String) lista.getItemAtPosition(position);

                if(position == 0){
                    Intent i = new Intent(AcercaActivity.this,AvisoActivity.class);
                    startActivity(i);
                }
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
            //Intent i=new Intent(this,PrincipalActivity.class);
            //startActivity(i);
        }
        return true;
    }
}
