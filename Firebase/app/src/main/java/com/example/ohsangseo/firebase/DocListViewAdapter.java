package com.example.ohsangseo.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DocListViewAdapter extends BaseAdapter {
    private ArrayList<Doclist> Doclist = new ArrayList<>();

    @Override
    public int getCount() {
        return Doclist.size();
    }

    @Override
    public Object getItem(int i) {
        return Doclist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Context context = parent.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.doclist, parent, false);
        }

        ImageView imageView = (ImageView)view.findViewById(R.id.imageview);
        TextView textViewTitle = (TextView)view.findViewById(R.id.textViewTitle);
        TextView textViewContent = (TextView)view.findViewById(R.id.textViewContent);

        textViewTitle.setText(Doclist.get(position).getTitle());
        textViewContent.setText(Doclist.get(position).getContent());

        return view;
    }

    public void addItem(String title, String content) {
        Doclist item = new Doclist();

        item.setTitle(title);
        item.setContent(content);

        Doclist.add(item);
    }

}
