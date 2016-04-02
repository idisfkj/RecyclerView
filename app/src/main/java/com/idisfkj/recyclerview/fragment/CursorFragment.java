package com.idisfkj.recyclerview.fragment;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idisfkj.recyclerview.R;
import com.idisfkj.recyclerview.adapter.CursorAdapter;
import com.idisfkj.recyclerview.bean.ItemInfo;
import com.idisfkj.recyclerview.dao.RecyclerDataHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by idisfkj on 16/4/2.
 * Email : idisfkj@qq.com.
 */
public class CursorFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private RecyclerDataHelper mHelper;
    private CursorAdapter mAdapter;
    private String[] item;

    private CursorFragment() {
    }

    private static class CursorFragmentHolder {
        private static CursorFragment instance = new CursorFragment();
    }

    public static CursorFragment newInstance() {
        return CursorFragmentHolder.instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.recycler_view, null);
        ButterKnife.bind(this, view);
        mHelper = new RecyclerDataHelper(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CursorAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    public void initData() {
        List<ItemInfo> list = new ArrayList<>();
        item = this.getResources().getStringArray(R.array.item);
        for (int i = 0; i < item.length; i++) {
            ItemInfo info = new ItemInfo();
            info.setContetn(item[i]);
            list.add(info);
        }
        mHelper.bulkInsert(list);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return mHelper.getCursorLoader();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data == null || data.getCount() == 0) {
            initData();
        } else {
            mAdapter.changeCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.changeCursor(null);
    }
}
