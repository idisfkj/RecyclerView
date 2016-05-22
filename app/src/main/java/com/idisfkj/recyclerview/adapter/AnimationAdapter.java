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
 * Created by idisfkj on 16/3/30.
 * Email : idisfkj@qq.com.
 */
public class AnimationAdapter<T> extends RecyclerView.Adapter {

    private LayoutInflater mLayoutInflater;
    public List<T> mListData;
    public static String[] item;

    public AnimationAdapter(Context context, List<T> listData) {
        mLayoutInflater = LayoutInflater.from(context);
        mListData = new ArrayList<>();
        mListData = listData;
        item = context.getResources().getStringArray(R.array.recycler_conten);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).itemTv.setText((CharSequence) mListData.get(position));
    }

    @Override
    public int getItemCount() {
        return mListData != null ? mListData.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_tv)
        TextView itemTv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
