package com.idisfkj.recyclerview.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idisfkj.recyclerview.R;
import com.idisfkj.recyclerview.StartActivity;
import com.idisfkj.recyclerview.bean.ItemInfo;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by idisfkj on 16/4/2.
 * Email : idisfkj@qq.com.
 */
public class CursorAdapter extends RecyclerBaseCursorAdapter<CursorAdapter.CursorViewHolder> {
    private LayoutInflater mLayoutInflater;

    public CursorAdapter(Context context) {
        super(context, null);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(CursorViewHolder holder, Cursor cursor) {
        holder.itemTv.setText(ItemInfo.formCursor(cursor).contetn);
    }

    @Override
    public CursorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_text, parent, false);
        return new CursorViewHolder(view, this);
    }

    static class CursorViewHolder extends RecyclerView.ViewHolder {
        private CursorAdapter mAdapter;

        @Bind(R.id.item_tv)
        TextView itemTv;

        CursorViewHolder(View view, CursorAdapter adapter) {
            super(view);
            ButterKnife.bind(this, view);
            mAdapter = adapter;
        }

        @OnClick(R.id.item_tv)
        public void onClick() {
            Intent intent = new Intent();
            intent.putExtra("type", getLayoutPosition());
            intent.setClass(mAdapter.mContext, StartActivity.class);
            mAdapter.mContext.startActivity(intent);
        }
    }
}
