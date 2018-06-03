package com.techiespace.projects.jafeedback;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.techiespace.projects.jafeedback.databinding.OrgRowBinding;
import com.techiespace.projects.jafeedback.db.OrgDatabase;
import com.techiespace.projects.jafeedback.db.OrgList;

import java.util.List;

public class OrgAdapter extends RecyclerView.Adapter<OrgAdapter.MyViewHolder> implements View.OnClickListener {

    List<OrgList> orgLists;
    private Context mcontext;

    public OrgAdapter(Context context) {
        mcontext = context;
    }

    public void setOrgLists(List<OrgList> orgLists) {
        this.orgLists = orgLists;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrgDatabase db = OrgDatabase.getDatabase(mcontext);
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        OrgRowBinding orgRowBinding = OrgRowBinding.inflate(
                layoutInflater, parent, false);
        return new MyViewHolder(orgRowBinding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final String email = orgLists.get(position).priEmail;
        final String phone = orgLists.get(position).priPhone;
        OrgList orgList = orgLists.get(position);
        holder.bind(orgList);

        holder.binding.orgName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, OrgDetailsActivity.class);
                intent.putExtra("id", orgLists.get(holder.getAdapterPosition()).orgId);
                mcontext.startActivity(intent);
            }
        });
        holder.binding.phone.setOnClickListener(new View.OnClickListener() {
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
        holder.binding.email.setOnClickListener(new View.OnClickListener() {
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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private OrgRowBinding binding;

        public MyViewHolder(OrgRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(OrgList orgList) {
            binding.setVariable(BR.orgList, orgList);
            binding.executePendingBindings();
        }
    }
}
