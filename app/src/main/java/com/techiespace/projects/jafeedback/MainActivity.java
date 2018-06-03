package com.techiespace.projects.jafeedback;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiespace.projects.jafeedback.db.OrgList;
import com.techiespace.projects.jafeedback.databinding.OrgRowBinding;

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
        adapter = new OrgAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerView.setAdapter(adapter);

        orgViewModel = ViewModelProviders.of(this).get(OrgViewModel.class);
        orgViewModel.getOrgsLiveData().observe(MainActivity.this, new Observer<List<OrgList>>() {
            @Override
            public void onChanged(@Nullable List<OrgList> users) {
                adapter.setUsers(users);
            }
        });

//        OrgRowBinding binding = OrgRowBinding.inflate(getLayoutInflater(),(ViewGroup)findViewById(R.id.activity_main),false);
//        binding.setOrgViewModel(orgViewModel);
//        binding.executePendingBindings();


    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        //OrgDatabase db = OrgDatabase.getDatabase(getApplicationContext());
//        //List<OrgList> users = (List<OrgList>) db.orgListDao().getAllOrg();
//        //adapter.setUsers(users);
//
//    }
}
