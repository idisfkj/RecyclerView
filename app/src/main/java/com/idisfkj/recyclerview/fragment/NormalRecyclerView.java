package com.idisfkj.recyclerview.fragment;

import android.os.Bundle;

import com.idisfkj.recyclerview.App.App;
import com.idisfkj.recyclerview.adapter.NormalRecyclerAdapter;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;

/**
 * Created by idisfkj on 16/3/28.
 * Email : idisfkj@qq.com.
 */
public class NormalRecyclerView extends BaseFragment{

    private static class NormalRecyclerViewHolder {
        public static NormalRecyclerView instance = new NormalRecyclerView();
    }

    private NormalRecyclerView() {
    }

    public static NormalRecyclerView newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        NormalRecyclerViewHolder.instance.setArguments(bundle);
        return NormalRecyclerViewHolder.instance;
    }

    @Override
    public void setAdapter() {
        NormalRecyclerAdapter adapter = new NormalRecyclerAdapter(getActivity(),mListData);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        RefWatcher refWatcher = App.getRefWatcher(getActivity());
        refWatcher.watch(this);

    }
}
