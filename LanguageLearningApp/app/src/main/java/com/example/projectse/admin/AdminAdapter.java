package com.example.projectse.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectse.R;
import com.example.projectse.bohoctap.BoHocTap;
import com.example.projectse.bohoctap.BoHocTapAdapter;

import java.util.ArrayList;

public class AdminAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<String> list;

    public AdminAdapter(Context context, int layout, ArrayList<String> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTenBo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdminAdapter.ViewHolder holder;
        if(convertView == null){
            holder = new AdminAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtTenBo = (TextView) convertView.findViewById(R.id.tvTenBo);
            convertView.setTag(holder);
        }else {
            holder = (AdminAdapter.ViewHolder) convertView.getTag();
        }
        String itemList = list.get(position);
        holder.txtTenBo.setText(itemList);
        return convertView;
    }
}
