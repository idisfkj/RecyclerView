package com.idisfkj.recyclerview.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idisfkj.recyclerview.App.App;
import com.idisfkj.recyclerview.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by idisfkj on 16/3/30.
 * Email : idisfkj@qq.com.
 */
public abstract class BaseFragment extends Fragment {
    private int type;
    protected List<String> mListData;
    private String[] list;
    protected GridLayoutManager gridLayoutManager;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt("type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.recycler_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListData = new ArrayList<>();
        list = getResources().getStringArray(R.array.recycler_conten);
        Collections.addAll(mListData, list);
        switch (type) {
            case App.LINEAR_LAYOUT:
                //ListView
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                break;
            case App.GRID_LAYOUT:
                //GridView
                gridLayoutManager = new GridLayoutManager(getActivity(),3);
                recyclerView.setLayoutManager(gridLayoutManager);
                break;
            case App.STAGGERED_GRID_LAYOUT:
                //Can customize the waterfall flow
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
                break;
            default:
                break;
        }
        setAdapter();
    }

    public abstract void setAdapter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

