package com.idisfkj.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.idisfkj.recyclerview.fragment.CursorFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getFragmentManager().beginTransaction()
                .replace(R.id.content, CursorFragment.newInstance())
                .commit();
    }
}
