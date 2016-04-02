package com.idisfkj.recyclerview.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;

import com.idisfkj.recyclerview.adapter.AnimationAdapter;

/**
 * Created by idisfkj on 16/3/30.
 * Email : idisfkj@qq.com.
 */
public class AnimationRecyclerView extends BaseFragment {

    public AnimationRecyclerView() {
    }

    private static class AnimationRecyclerViewHolder {
        private static AnimationRecyclerView instance = new AnimationRecyclerView();
    }

    public static AnimationRecyclerView newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        AnimationRecyclerViewHolder.instance.setArguments(bundle);
        return AnimationRecyclerViewHolder.instance;
    }

    @Override
    public void setAdapter() {
        AnimationAdapter adapter = new AnimationAdapter(getActivity(), mListData);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
