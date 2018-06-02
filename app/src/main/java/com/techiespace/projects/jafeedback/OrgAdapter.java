package com.techiespace.projects.jafeedback;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.techiespace.projects.jafeedback.db.OrgDatabase;
import com.techiespace.projects.jafeedback.db.OrgList;

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
        final String email = orgLists.get(position).priEmail;
        final String phone = orgLists.get(position).priPhone;
        holder.firstName.setText(orgLists.get(position).org);
        holder.email.setText(email);
        holder.phone.setText(phone);
        holder.orgName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, OrgDetailsActivity.class);
                intent.putExtra("id", orgLists.get(position).orgId);
                mcontext.startActivity(intent);
            }
        });
        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:" + phone));
                if (phoneIntent.resolveActivity(mcontext.getPackageManager()) != null)
                    mcontext.startActivity(phoneIntent);
                else
                    Toast.makeText(mcontext, "No suitable app found to make a phone call :(", Toast.LENGTH_LONG).show();
            }
        });
        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setType("text/plain");
                emailIntent.setData(Uri.parse("mailto:" + email));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello Madam/Sir,");
                if (emailIntent.resolveActivity(mcontext.getPackageManager()) != null)
                    mcontext.startActivity(Intent.createChooser(emailIntent, "Choose an email client :)"));
                else
                    Toast.makeText(mcontext, "No suitable app found to send email :(", Toast.LENGTH_LONG).show();
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
