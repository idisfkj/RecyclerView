package com.idisfkj.recyclerview.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.idisfkj.recyclerview.adapter.HeadBottomRecyclerAdapter;

/**
 * Created by idisfkj on 16/3/30.
 * Email : idisfkj@qq.com.
 */
public class HeadBottomRecyclerView extends BaseFragment {

    private HeadBottomRecyclerAdapter adapter;

    private HeadBottomRecyclerView() {

    }

    private static class HeadBottomRecyclerViewHolder {
        public static HeadBottomRecyclerView instance = new HeadBottomRecyclerView();
    }

    public static HeadBottomRecyclerView newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        HeadBottomRecyclerViewHolder.instance.setArguments(bundle);
        return HeadBottomRecyclerViewHolder.instance;
    }

    @Override
    public void setAdapter() {
        adapter = new HeadBottomRecyclerAdapter(getActivity(), mListData);
        recyclerView.setAdapter(adapter);
        if (gridLayoutManager != null) {
            //if layout is GridView them this head and bottom occupied a line;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (adapter.getHeadCount() > position ||
                            adapter.getContentCount() + adapter.getHeadCount() <= position)
                            ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }

}
