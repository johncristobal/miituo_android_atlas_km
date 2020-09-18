package com.miituo.atlaskm.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.miituo.atlaskm.R;
import com.miituo.atlaskm.api.ApiClient;
import com.miituo.atlaskm.data.IinfoClient;
import com.miituo.atlaskm.data.InfoClient;
import com.miituo.atlaskm.data.InfoSectionItem;
import com.miituo.atlaskm.multiaseguradora.Aseguradoras;

/**
 * Created by john.cristobal on 04/05/17.
 */

public class TabFragment4 extends Fragment {

    Context context;
    Typeface tipo;

    /*public TabFragment4(Context c,Typeface t){
        context = c;
        tipo = t;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab4, container, false);

        Button call = (Button)v.findViewById(R.id.button6);
        TextView res = (TextView)v.findViewById(R.id.textView56);
        TextView fon = (TextView)v.findViewById(R.id.textView);

        res.setTypeface(PagerAdapter.tipo);
        fon.setTypeface(PagerAdapter.tipo);
        ImageView iconAseguradora=(ImageView)v.findViewById(R.id.imageView8);

        final String noAseguradora;  // atlas

        if(IinfoClient.getInfoClientObject().getPolicies().getInsuranceCarrier().getId()== Aseguradoras.atlas){
            iconAseguradora.setImageResource(R.drawable.segurosatlas);
            res.setText(getString(R.string.tel_atlas));
            noAseguradora=getString(R.string.tel_atlas);
        }
        else if(IinfoClient.getInfoClientObject().getPolicies().getInsuranceCarrier().getId()==Aseguradoras.ana){
            iconAseguradora.setImageResource(R.drawable.segurosana);
            res.setText(getString(R.string.tel_ana));
            noAseguradora=getString(R.string.tel_ana);
        }
        else{
            iconAseguradora.setImageResource(R.drawable.segurosatlas);
            res.setText(getString(R.string.tel_gnp));
            noAseguradora=getString(R.string.tel_gnp);
        }

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Intent intent = new Intent(Intent.ACTION_CALL);
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", noAseguradora, null));
                    //intent.setData(Uri.parse("11025280"));
                    getActivity().startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        return v;
    }

}
