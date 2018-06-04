package com.techiespace.projects.feedback;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.techiespace.projects.feedback.db.OrgList;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public OrgAdapter adapter;
    public OrgViewModel orgViewModel;
    private SearchView searchView;


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

    private SearchView.OnQueryTextListener onQueryTextListener =
            new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    fetchFromDb(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    fetchFromDb(newText);
                    return true;
                }

                private void fetchFromDb(String searchText) {
                    searchText = "%" + searchText + "%";
                    orgViewModel.findOrgListData(searchText).observe(MainActivity.this, new Observer<List<OrgList>>() {
                        @Override
                        public void onChanged(@Nullable List<OrgList> users) {
                            if (users == null)
                                return;
                            adapter.setOrgLists(users);
                        }
                    });
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(onQueryTextListener);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_org) {
            Intent addOrgIntent = new Intent(this, AddOrgActivity.class);
            this.startActivity(addOrgIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
