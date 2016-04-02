package com.idisfkj.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.idisfkj.recyclerview.fragment.AnimationRecyclerView;
import com.idisfkj.recyclerview.fragment.HeadBottomRecyclerView;
import com.idisfkj.recyclerview.fragment.NormalRecyclerView;

/**
 * Created by idisfkj on 16/4/2.
 * Email : idisfkj@qq.com.
 */
public class StartActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        int type = intent.getExtras().getInt("type");
        Log.d("TAG", "" + type);
        startFragment(type);
    }

    public void startFragment(int type) {
        switch (type) {
            case 0:
                getFragmentManager().beginTransaction()
                        .replace(R.id.content, NormalRecyclerView.newInstance(type))
                        .commit();
                break;
            case 1:
                getFragmentManager().beginTransaction()
                        .replace(R.id.content, NormalRecyclerView.newInstance(type))
                        .commit();
                break;
            case 2:
                getFragmentManager().beginTransaction()
                        .replace(R.id.content,NormalRecyclerView.newInstance(type))
                        .commit();
                break;
            case 3:
                getFragmentManager().beginTransaction()
                        .replace(R.id.content, AnimationRecyclerView.newInstance(type-3))
                        .commit();
                break;
            case 4:
                getFragmentManager().beginTransaction()
                        .replace(R.id.content, AnimationRecyclerView.newInstance(type-3))
                        .commit();
                break;
            case 5:
                getFragmentManager().beginTransaction()
                        .replace(R.id.content, AnimationRecyclerView.newInstance(type-3))
                        .commit();
                break;
            case 6:
                getFragmentManager().beginTransaction()
                        .replace(R.id.content, HeadBottomRecyclerView.newInstance(1))
                        .commit();
                break;
            default:
                break;
        }
    }
}
