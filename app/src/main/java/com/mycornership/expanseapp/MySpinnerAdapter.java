package com.mycornership.expanseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MySpinnerAdapter extends ArrayAdapter {
    private List<CurrencyItems> myItems;

    public MySpinnerAdapter(@NonNull Context context, List<CurrencyItems> items) {
        super(context,0, items);
        myItems = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.currency_spinner_row, parent, false);

            CircleImageView icon = convertView.findViewById(R.id.currency_icon);
            TextView symbl = convertView.findViewById(R.id.currency_label);
            icon.setImageResource(myItems.get(position).getIcon());
            symbl.setText(myItems.get(position).getSymbol());

        }
        return  convertView;
    }

}
