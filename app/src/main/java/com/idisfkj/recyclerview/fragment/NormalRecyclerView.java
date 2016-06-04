package com.idisfkj.recyclerview.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.idisfkj.recyclerview.App.App;
import com.idisfkj.recyclerview.adapter.NormalRecyclerAdapter;
import com.idisfkj.recyclerview.adapter.OnItemTouchListener;
import com.squareup.leakcanary.RefWatcher;

import java.util.Collections;

import butterknife.ButterKnife;

/**
 * Created by idisfkj on 16/3/28.
 * Email : idisfkj@qq.com.
 */
public class NormalRecyclerView extends BaseFragment {

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
        NormalRecyclerAdapter adapter = new NormalRecyclerAdapter(getActivity(), mListData);
        recyclerView.setAdapter(adapter);
        final ItemTouchHelper helper = new ItemTouchHelper(new MyCallBack());
        helper.attachToRecyclerView(recyclerView);
        recyclerView.addOnItemTouchListener(new OnItemTouchListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {

            }

            @Override
            public void onItemLongPressClick(RecyclerView.ViewHolder vh) {
                if (vh.getAdapterPosition() != 0) {
                    helper.startDrag(vh);
                }
            }
        });
    }

    public class MyCallBack extends ItemTouchHelper.Callback {

        /**
         * 拖动标识
         */
        private int dragFlags;
        /**
         * 删除滑动标识
         */
        private int swipeFlags;

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            dragFlags = 0;
            swipeFlags = 0;
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager
                    || recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN
                        | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                if (viewHolder.getAdapterPosition() != 0)
                    swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            }
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            if (toPosition != 0) {
                if (fromPosition < toPosition)
                    //向下拖动
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(mListData, i, i + 1);
                    }
                else {
                    //向上拖动
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(mListData, i, i - 1);
                    }
                }
                recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            }
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int positon = viewHolder.getAdapterPosition();
            recyclerView.getAdapter().notifyItemRemoved(positon);
            mListData.remove(positon);
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return false;
        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                viewHolder.itemView.setPressed(true);
            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            viewHolder.itemView.setPressed(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        RefWatcher refWatcher = App.getRefWatcher(getActivity());
        refWatcher.watch(this);

    }
}
