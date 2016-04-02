package com.idisfkj.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idisfkj.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by idisfkj on 16/3/28.
 * Email : idisfkj@qq.com.
 */
public class NormalRecyclerAdapter<T> extends RecyclerView.Adapter<NormalRecyclerAdapter.NormalViewHolder> {

    public List<T> mListData;
    private LayoutInflater mLayoutInflater;

    public NormalRecyclerAdapter(Context context, List<T> listData) {
        mListData = new ArrayList<>();
        mListData = listData;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
    }


    @Override
    public void onBindViewHolder(NormalViewHolder holder, int position) {
        holder.itemTv.setText((CharSequence) mListData.get(position));
    }



    public static class NormalViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_tv)
        TextView itemTv;

        public NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @Override
    public int getItemCount() {
        return mListData != null ? mListData.size() : 0;
    }
}
