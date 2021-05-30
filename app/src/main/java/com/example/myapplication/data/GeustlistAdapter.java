package com.example.myapplication.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class GeustlistAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<GeustlistResponse> Geustlist;

    TextView r_number, GaestName;

    public GeustlistAdapter(Context context, ArrayList<GeustlistResponse> data) {
        mContext = context;
        Geustlist = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return Geustlist.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public GeustlistResponse getItem(int position) {
        return Geustlist.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.glist, null);

        r_number = (TextView) view.findViewById(R.id.r_number);
        GaestName = (TextView) view.findViewById(R.id.GaestName);

        r_number.setText(Geustlist.get(position).getR_number());
        GaestName.setText(Geustlist.get(position).getGeustName());

        return view;
    }
}
