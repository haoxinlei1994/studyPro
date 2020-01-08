package com.mrh.chapter4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoxinlei on 2020-01-07.
 */
public class StudyFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private String type;

    public static StudyFragment newInstance(String type) {
        StudyFragment studyFragment = new StudyFragment();
        studyFragment.type = type;
        return studyFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_study_layout, container, false);
        MyAdapter myAdapter = new MyAdapter(initData());
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(myAdapter);
        return view;
    }

    private List<String> initData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(type + "：" + i);
        }
        return data;
    }
}
