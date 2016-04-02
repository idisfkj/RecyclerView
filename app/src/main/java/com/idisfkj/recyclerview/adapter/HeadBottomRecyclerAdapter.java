package com.idisfkj.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idisfkj.recyclerview.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by idisfkj on 16/3/29.
 * Email : idisfkj@qq.com.
 */
public class HeadBottomRecyclerAdapter<T> extends BaseHeadBottomRecyclerAdapter<T> {

    public HeadBottomRecyclerAdapter(Context context, List<T> listData) {
        super(context);
        mListData = listData;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadViweHolder) {
            ((HeadViweHolder) holder).itemIvTv.setText((CharSequence) mListData.get(position));
        } else if (holder instanceof ContetnViewHolder) {
            ((ContetnViewHolder) holder).itemTv.setText((CharSequence) mListData.get(position));
        } else {
            ((BottomViewHolder) holder).itemIvTv.setText((CharSequence) mListData.get(position));
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateHeadViewHolder(ViewGroup parent) {
        return new HeadViweHolder(mLayoutInflater.inflate(R.layout.item_image_text, parent, false));
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        return new ContetnViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
    }

    @Override
    public RecyclerView.ViewHolder onCreateBottomViewHolder(ViewGroup parent) {
        return new BottomViewHolder(mLayoutInflater.inflate(R.layout.item_image_text, parent, false));
    }

    public static class HeadViweHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_iv_tv)
        TextView itemIvTv;

        public HeadViweHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_iv_tv)
        TextView itemIvTv;

        public BottomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class ContetnViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_tv)
        TextView itemTv;

        public ContetnViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getHeadCount() {
        return 1;
    }

    @Override
    public int getContentCount() {
        return 10;
    }

}
