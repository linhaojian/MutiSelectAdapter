package com.lhj.test.mutiselectadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by zdq on 2018/5/11.
 */

public class StringAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<String> list;

    public StringAdapter(Context context,List<String> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        StringHolder stringHolder = new StringHolder(view);
        return stringHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        StringHolder stringHolder = (StringHolder) holder;
        stringHolder.textView.setText(list.get(position));
        stringHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,list.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class StringHolder extends RecyclerView.ViewHolder{
        TextView textView;
        LinearLayout linearlayout;
        public StringHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            linearlayout = itemView.findViewById(R.id.linearlayout);
        }
    }

}
