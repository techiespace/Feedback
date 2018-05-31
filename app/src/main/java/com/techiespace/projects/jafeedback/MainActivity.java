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

    RecyclerView recyclerView;
    OrgAdapter adapter;
    OrgViewModel orgViewModel;

    public OrgAdapter getAdapter() {
        return adapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new OrgAdapter(this);
        recyclerView.setAdapter(adapter);

        orgViewModel = ViewModelProviders.of(this).get(OrgViewModel.class);
        orgViewModel.getOrgsLiveData().observe(this, new Observer<List<OrgList>>() {
            @Override
            public void onChanged(@Nullable List<OrgList> users) {
                adapter.setUsers(users);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //OrgDatabase db = OrgDatabase.getDatabase(getApplicationContext());
        //List<OrgList> users = (List<OrgList>) db.orgListDao().getAllOrg();
        //adapter.setUsers(users);

    }
}
