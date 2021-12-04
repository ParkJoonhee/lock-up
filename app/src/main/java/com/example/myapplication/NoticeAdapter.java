package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class NoticeAdapter extends BaseAdapter {

    private TextView ntitle,ndate,nname;
    private LinearLayout notice;

    private ArrayList<NoticeItem> NoticeItemList = new ArrayList<NoticeItem>();

    @Override
    public int getCount() {
        return NoticeItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return NoticeItemList.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final int pos = position;
        final Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.noticelist, viewGroup, false);
        }

        ntitle = (TextView) view.findViewById(R.id.ntitle);
        ndate = (TextView) view.findViewById(R.id.ndate);
        nname = (TextView) view.findViewById(R.id.nname);

        NoticeItem listViewItem = NoticeItemList.get(position);

        ntitle.setText(listViewItem.getTitle());
        ndate.setText(listViewItem.getDate());
        nname.setText(listViewItem.getName());

        notice = (LinearLayout) view.findViewById(R.id.notice);

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(context,listViewItem.getTitle(), listViewItem.printContents());
            }
        });

        return view;
    }

    public void addItem(String title, String date, String name,String notice) {
        NoticeItem item = new NoticeItem();

        item.setTitle(title);
        item.setDate(date);
        item.setName(name);
        item.setNotice(notice);

        NoticeItemList.add(item);
    }


    void showDialog(Context context,String title,String notice){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title).setMessage(notice);
        builder.setCancelable(false);

        builder.setNeutralButton("닫기", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id){}
        });

        AlertDialog alertDialog = builder.create();

        alertDialog.show();

    }


}
