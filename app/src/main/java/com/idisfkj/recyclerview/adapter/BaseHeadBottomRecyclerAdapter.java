package com.idisfkj.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.idisfkj.recyclerview.App.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by idisfkj on 16/3/29.
 * Email : idisfkj@qq.com.
 */
public abstract class BaseHeadBottomRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<T> mListData;
    protected int mHeadCount;
    protected int mBottomCount;
    protected int mContentCount;
    protected LayoutInflater mLayoutInflater;

    protected BaseHeadBottomRecyclerAdapter(Context context) {
        mListData = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case App.LINEAR_LAYOUT:
                return onCreateHeadViewHolder(parent);
            case App.GRID_LAYOUT:
                return onCreateContentViewHolder(parent);
            case App.STAGGERED_GRID_LAYOUT:
                return onCreateBottomViewHolder(parent);
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return mListData != null ? mListData.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        mHeadCount = getHeadCount();
        mContentCount = getContentCount();
        mBottomCount = mHeadCount + mContentCount;
        if (mHeadCount > position) {
            return App.LINEAR_LAYOUT;//ListView
        } else if (mBottomCount <= position) {
            return App.STAGGERED_GRID_LAYOUT;//Can customize the waterfall flow
        } else {
            return App.GRID_LAYOUT;//GridView
        }
    }

    public abstract RecyclerView.ViewHolder onCreateHeadViewHolder(ViewGroup parent);

    public abstract RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent);

    public abstract RecyclerView.ViewHolder onCreateBottomViewHolder(ViewGroup parent);

    public abstract int getHeadCount();

    public abstract int getContentCount();
}
