package com.miituo.atlaskm.tuto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.miituo.atlaskm.R;

/**
 * Created by john.cristobal on 26/05/17.
 */

public class ImageFragment extends Fragment {

    static ImageFragment init(int val) {
        ImageFragment truitonFrag = new ImageFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        truitonFrag.setArguments(args);
        return truitonFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fragVal = getArguments() != null ? getArguments().getInt("val") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View layoutView = inflater.inflate(R.layout.fragment_image, container,false);
        //View tv = layoutView.findViewById(R.id.text);
        //((TextView) tv).setText("Truiton Fragment #" + fragVal);
        return null;
    }

}

