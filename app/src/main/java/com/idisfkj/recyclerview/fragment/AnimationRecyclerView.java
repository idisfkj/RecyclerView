package com.idisfkj.recyclerview.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import com.idisfkj.recyclerview.adapter.AnimationAdapter;
import com.idisfkj.recyclerview.adapter.OnItemTouchListener;

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
        final AnimationAdapter adapter = new AnimationAdapter(getActivity(), mListData);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(new OnItemTouchListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                if (vh.getLayoutPosition() != 1) {
                    adapter.mListData.add("add" + vh.getLayoutPosition());
                    adapter.notifyItemInserted(vh.getLayoutPosition());
                } else {
                    adapter.mListData.remove(vh.getLayoutPosition());
                    adapter.notifyItemRemoved(vh.getLayoutPosition());
                }
            }
        });
    }
}
