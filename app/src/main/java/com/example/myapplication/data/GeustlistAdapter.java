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


    private TextView House, GaestName;

    private ArrayList<GeustlistResponse> Geustlist = new ArrayList<GeustlistResponse>();

    public GeustlistAdapter() {
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
        final int pos = position;
        final Context context = parent.getContext();

        if (converView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            converView = inflater.inflate(R.layout.glist, parent, false);
        }

        House = (TextView) converView.findViewById(R.id.House);
        GaestName = (TextView) converView.findViewById(R.id.GaestName);

        House.setText(Geustlist.get(position).getlive_code() + " - " + Geustlist.get(position).getHouse());
        GaestName.setText(Geustlist.get(position).getGeustName());

        return converView;
    }

    public void addItem(int live_code, int house, String geustName1) {
        GeustlistResponse item = new GeustlistResponse();

        item.setlive_code(live_code);
        item.setHouse(house);
        item.setGeustName(geustName1);

        Geustlist.add(item);
    }
}
