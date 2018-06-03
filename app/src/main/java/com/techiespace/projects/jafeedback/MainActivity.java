package com.techiespace.projects.jafeedback;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.techiespace.projects.jafeedback.db.OrgList;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public OrgAdapter adapter;
    public OrgViewModel orgViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new OrgAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        orgViewModel = ViewModelProviders.of(this).get(OrgViewModel.class);
        orgViewModel.getOrgsLiveData().observe(MainActivity.this, new Observer<List<OrgList>>() {
            @Override
            public void onChanged(@Nullable List<OrgList> users) {
                adapter.setOrgLists(users);
            }
        });
    }
}
