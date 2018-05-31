package com.techiespace.projects.jafeedback;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techiespace.projects.jafeedback.db.OrgDatabase;
import com.techiespace.projects.jafeedback.db.OrgList;
import com.techiespace.projects.jafeedback.db.Phone;

import java.util.List;

public class OrgAdapter extends RecyclerView.Adapter<OrgAdapter.ViewHolder> implements View.OnClickListener {

    List<OrgList> orgLists;
    private Context mcontext;
    private OrgDatabase db;

    public OrgAdapter(List<OrgList> orgLists) {
        this.orgLists = orgLists;
    }

    public OrgAdapter(Context context) {
        mcontext = context;
    }

    public void setUsers(List<OrgList> orgLists) {
        //this.users.clear();
        //this.users.addAll(users);
        this.orgLists = orgLists;
        notifyDataSetChanged();
    }


    @Override
    public OrgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        db = OrgDatabase.getDatabase(mcontext);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.org_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OrgAdapter.ViewHolder holder, final int position) {
        holder.firstName.setText(orgLists.get(position).org);
        holder.email.setText(orgLists.get(position).email);

        final LiveData<Phone> phone = db.phoneDao().findPhoneById(orgLists.get(position).orgId);  //this requires allowqueriesonmainthread of livedata is removed
        final Observer<Phone> ob = new Observer<Phone>() {
            @Override
            public void onChanged(@Nullable Phone mphone) {
                holder.phone.setText(mphone.phone);
                phone.removeObserver(this);
            }
        };
        phone.observeForever(ob);

        holder.orgName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, OrgDetailsActivity.class);
                intent.putExtra("id", orgLists.get(position));
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (orgLists != null) {
            return orgLists.size();
        } else
            return 0;

    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView firstName;
        public TextView email;
        public TextView phone;
        public TextView orgName;

        public ViewHolder(View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.org_name);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
            orgName = itemView.findViewById(R.id.org_name);
        }
    }
}
