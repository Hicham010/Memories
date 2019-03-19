package com.example.memories;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MemoryListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Memory> memoryList;

    public MemoryListAdapter(Context context, int layout, ArrayList<Memory> memoryList) {
        this.context = context;
        this.layout = layout;
        this.memoryList = memoryList;
    }

    @Override
    public int getCount() {
        return memoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return memoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView memory_image;
        TextView locationTextView, dateTextView, descriptionTextView;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.locationTextView = (TextView) row.findViewById(R.id.locationTextView);
            holder.dateTextView = (TextView) row.findViewById(R.id.dateTextView);
            holder.descriptionTextView = (TextView) row.findViewById(R.id.descriptionTextView);
            holder.memory_image = (ImageView) row.findViewById(R.id.memory_image);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }

        Memory memory = memoryList.get(position);

        holder.locationTextView.setText(memory.getLocation());
        holder.dateTextView.setText(memory.getDate());

        holder.descriptionTextView.setText(memory.getDescription());

        byte [] memoryImage = memory.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(memoryImage, 0 ,memoryImage.length);
        holder.memory_image.setImageBitmap(bitmap);

        return row;
    }
}
