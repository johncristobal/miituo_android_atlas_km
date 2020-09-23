package com.miituo.atlaskm.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.miituo.atlaskm.R;
import com.miituo.atlaskm.data.InfoClient;
import com.miituo.atlaskm.db.Notifications;
import com.miituo.atlaskm.utils.CallBack;
import com.miituo.atlaskm.utils.SimpleCallBack;

/**
 * Created by miituo on 26/01/2017.
 */
@SuppressWarnings("serial")
public class PushAdapter extends BaseAdapter {

    private Context mContext;
    private List<Notifications> pushes;
    private SimpleCallBack callBack;
    public String noTel="";

    public PushAdapter(Context mContext, List<Notifications> pushes, SimpleCallBack callBack) {
        this.mContext = mContext;
        this.pushes = pushes;
        this.callBack=callBack;
    }

    @Override
    public int getCount() {
        return pushes.size();
    }

    @Override
    public Object getItem(int position) {
        return pushes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v=View.inflate(mContext,R.layout.push_item,null);
        TextView title=(TextView)v.findViewById(R.id.title);
        TextView body=(TextView)v.findViewById(R.id.body);
        ImageView lbOpts=(ImageView) v.findViewById(R.id.lbOpts);
        TextView lbDate=(TextView)v.findViewById(R.id.lbDate);
        RelativeLayout btnInfo=(RelativeLayout) v.findViewById(R.id.btnGral);
        ImageView imagen = (ImageView)v.findViewById(R.id.iconPush);
        lbOpts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.run(false,""+pushes.get(position).getId());
            }
        });
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.run(true,""+position);
            }
        });

        try {
            title.setText(pushes.get(position).getTitle());
            v.setTag(pushes.get(position));
            //update image upon statu pictures
            body.setText(pushes.get(position).getMsg());
            lbDate.setText(getDate(pushes.get(position).getPostDate()));
            switch (pushes.get(position).getTipoPush()){
                case 2:
                    imagen.setImageDrawable(mContext.getResources().getDrawable(R.drawable.noti_renew));
                    break;
                case 3:
                    imagen.setImageDrawable(mContext.getResources().getDrawable(R.drawable.noti_renew));
                    break;
                case 4:
                    imagen.setImageDrawable(mContext.getResources().getDrawable(R.drawable.noti_cancel));
                    break;
                case 5:
                    imagen.setImageDrawable(mContext.getResources().getDrawable(R.drawable.noti_odom));
                    break;
                case 6:
                    imagen.setImageDrawable(mContext.getResources().getDrawable(R.drawable.noti_foto));
                    break;
                case 7:
                    imagen.setImageDrawable(mContext.getResources().getDrawable(R.drawable.noti_foto));
                    break;
                case 8:
                    imagen.setImageDrawable(mContext.getResources().getDrawable(R.drawable.noti_warn));
                    break;
                default:
                    imagen.setImageDrawable(mContext.getResources().getDrawable(R.drawable.noti_talk));
                    break;
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        if(pushes.get(position).getIsRead()){
            btnInfo.setBackgroundColor(Color.parseColor("#e6e6e6"));
        }
        return v;
    }

    private String getDate(Date date){
        String fecha="";
        Calendar c = Calendar.getInstance();
//        c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH),0,0);
        c.add(Calendar.DATE, -1);
        Date hoy = c.getTime();

        if(date.before(hoy)) {
            c.add(Calendar.DATE, -6);
            Date semPas = c.getTime();
            if(date.before(semPas)) {
                SimpleDateFormat dia = new SimpleDateFormat("dd/MM/yyyy");
                return dia.format(date);
            }
            SimpleDateFormat dia = new SimpleDateFormat("EEEE");
            return dia.format(date);
        }
        SimpleDateFormat dia = new SimpleDateFormat("HH:mm");
        return dia.format(date);
    }
}

