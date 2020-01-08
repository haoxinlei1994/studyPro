package com.mrh.chapter4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by haoxinlei on 2020-01-07.
 */
public class MyAdapter extends RecyclerView.Adapter {

    private List<String> dataSet;

    public MyAdapter(List<String> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((VH)viewHolder).mTextView.setText(dataSet.get(i));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    static class VH extends RecyclerView.ViewHolder{

        TextView mTextView;

        public VH(@NonNull View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }
    }
}
