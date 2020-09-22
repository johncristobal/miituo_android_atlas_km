package com.miituo.atlaskm.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.tabs.TabLayout;

import java.io.File;

import com.miituo.atlaskm.R;
import com.miituo.atlaskm.api.ApiClient;
import com.miituo.atlaskm.data.IinfoClient;
import com.miituo.atlaskm.fragments.PagerAdapter;
import com.miituo.atlaskm.threats.GetPDFSync;
import com.miituo.atlaskm.utils.CallBack;
import com.miituo.atlaskm.utils.SimpleCallBack;
import static com.miituo.atlaskm.fragments.PagerAdapter.context;

public class DetallesActivity extends AppCompatActivity {
    ViewPager viewPager;
    PagerAdapter adapter;
    private ImageButton back;
    public static File pdf;
    public static AlertDialog alertaPago;
    public String pathPhotos = new ApiClient(this).pathPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        SharedPreferences app_preferences= getSharedPreferences("miituo", Context.MODE_PRIVATE);
        long starttime = app_preferences.getLong("time",0);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("").setIcon(R.drawable.poliza));
        //tabLayout.addTab(tabLayout.newTab().setText("").setIcon(R.drawable.historial));
        tabLayout.addTab(tabLayout.newTab().setText("").setIcon(R.drawable.odom));
        //tabLayout.addTab(tabLayout.newTab().setText("").setIcon(R.drawable.pago));
        tabLayout.addTab(tabLayout.newTab().setText("").setIcon(R.drawable.siniestro));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(Color.rgb(255,153,51));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                //change icon
                if(tab.getPosition()==0){
                    tab.setIcon(R.drawable.poliza);
                }else if(tab.getPosition()==1){
                    tab.setIcon(R.drawable.odom);
                }else if(tab.getPosition()==2){
                    tab.setIcon(R.drawable.siniestro);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //change icon
                if(tab.getPosition()==0){
                    tab.setIcon(R.drawable.poliza);
                }else if(tab.getPosition()==1){
                    tab.setIcon(R.drawable.odom);
                }else if(tab.getPosition()==2){
                    tab.setIcon(R.drawable.siniestro);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        init();
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(),this,starttime,viewPager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        back = (ImageButton)findViewById(R.id.BackButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void init(){
        TextView  lbDescargar= (TextView)findViewById(R.id.textView37);
        TextView poliza = (TextView)findViewById(R.id.textViewpolizadetail);
        poliza.setText(IinfoClient.InfoClientObject.getPolicies().getNoPolicy());
        lbDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetPDFSync(DetallesActivity.this,true, IinfoClient.InfoClientObject.getPolicies().getId(),IinfoClient.InfoClientObject.getPolicies().getNoPolicy(), new SimpleCallBack() {
                    @Override
                    public void run(boolean status, String res) {
                        if(!status){
//            Toast.makeText(c,"descarga fallida",Toast.LENGTH_LONG).show();
                            final AlertDialog.Builder builder = new AlertDialog.Builder(DetallesActivity.this);
                            builder.setTitle("Descarga Fallida");
                            builder.setMessage("Por favor intentalo más tarde.");
                            builder.setCancelable(false);
                            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    alertaPago.dismiss();
                                }
                            });
                            alertaPago = builder.create();
                            alertaPago.show();
                        }else{
                            // Toast.makeText(c,"descarga exitosa",Toast.LENGTH_LONG).show();
                            final AlertDialog.Builder builder = new AlertDialog.Builder(DetallesActivity.this);
                            builder.setTitle("Descarga completa");
                            builder.setMessage("Ahora tu póliza se encuentra en tus descargas.");
                            builder.setCancelable(false);
                            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    alertaPago.dismiss();
//                    c.startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
                                    startActivity(new Intent(getApplicationContext(), PDFViewer.class).putExtra("isPoliza",true));
                                }
                            });
                            alertaPago = builder.create();
                            alertaPago.show();
                        }
                    }
                }).execute();
            }
        });

        ImageView fotocarro = (ImageView)findViewById(R.id.imageView5);
        if(fotocarro!= null){
            //pintaImg(imagen,position);
            pintaImg(fotocarro, IinfoClient.InfoClientObject.getPolicies().getId());
        }
        /*
        File image = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)+File.separator+"frontal"+PagerAdapter.tiempo+IinfoClient.InfoClientObject.getPolicies().getNoPolicy()+".png");
        File image2 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)+File.separator+"frontal_"+IinfoClient.InfoClientObject.getPolicies().getNoPolicy()+".png");
        if(image.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(image.getAbsolutePath());
            Bitmap resized = Bitmap.createScaledBitmap(myBitmap, myBitmap.getWidth()/4, myBitmap.getHeight()/4, false);
            fotocarro.setImageBitmap(resized);
        }else if(image2.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(image2.getAbsolutePath());
            Bitmap resized = Bitmap.createScaledBitmap(myBitmap, myBitmap.getWidth()/4, myBitmap.getHeight()/4, false);
            fotocarro.setImageBitmap(resized);
        }*/
    }

    private void pintaImg(ImageView iv, int id) {
        //ruta del archivo...https://filesdev.miituo.com/21/FROM_VEHICLE.png

        Glide.with(this).asBitmap().
                load(pathPhotos + id + "/FROM_VEHICLE.png")
                //.into(iv);
                .listener(new RequestListener<Bitmap>() {
                              @Override
                              public boolean onLoadFailed(@Nullable GlideException e, Object o, Target<Bitmap> target, boolean b) {
                                  iv.setImageResource(R.drawable.foto);
                                  return false;
                              }

                              @Override
                              public boolean onResourceReady(Bitmap bitmap, Object o, Target<Bitmap> target, DataSource dataSource, boolean b) {
                                  iv.setImageBitmap(bitmap);// .setImage(ImageSource.bitmap(bitmap));
                                  return false;
                              }
                          }
                ).submit();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu_principal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return true;
    }
}
