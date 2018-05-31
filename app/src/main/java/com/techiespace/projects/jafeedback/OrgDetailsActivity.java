package com.techiespace.projects.jafeedback;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.techiespace.projects.jafeedback.db.Email;
import com.techiespace.projects.jafeedback.db.OrgList;

import java.util.List;


public class OrgDetailsActivity extends AppCompatActivity {

    public int orgId;
    OrgDetailsViewModel orgDetailsViewModel;
    OrgList dOrg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_details);
        //OrgList orgList = getIntent().getParcelableExtra("id");
        orgId = getIntent().getIntExtra("id", 1);


        displayDetails(orgId);
    }

    private void displayDetails(final int orgId) {
        /*TextView dWebLink;
        TextView dFeedbackLink;
        TextView dFacebook;
        TextView dTwitter;
        TextView dYoutube;*/
        final TextView dOrgName;
        final TextView dPriEmail;

        /*dWebLink = findViewById(R.id.d_email);
        dFeedbackLink = findViewById(R.id.d_email);
        dFacebook = findViewById(R.id.d_email);
        dTwitter = findViewById(R.id.d_email);
        dYoutube = findViewById(R.id.d_email);*/
        dOrgName = findViewById(R.id.org_name);
        dPriEmail = findViewById(R.id.d_pri_email);


        //org
        orgDetailsViewModel = ViewModelProviders.of(this, new ViewModelFactory(this.getApplication(), orgId)).get(OrgDetailsViewModel.class);
        orgDetailsViewModel.getOrgLiveData().observe(this, new Observer<OrgList>() {
            @Override
            public void onChanged(@Nullable OrgList orgList) {
                dOrgName.setText(orgList.org);
            }
        });

        //email
        orgDetailsViewModel.getEmailsLiveData().observe(this, new Observer<List<Email>>() {
            @Override
            public void onChanged(@Nullable List<Email> emails) {
                dPriEmail.setText(emails.get(0).email);
            }
        });

        //phone
        // TODO: 31/5/18
        orgDetailsViewModel.getOrgLiveData().observe(this, new Observer<OrgList>() {
            @Override
            public void onChanged(@Nullable OrgList orgList) {
                dPriEmail.setText(orgList.priEmail);
            }
        });

        //social
        // TODO: 31/5/18
        orgDetailsViewModel.getOrgLiveData().observe(this, new Observer<OrgList>() {
            @Override
            public void onChanged(@Nullable OrgList orgList) {
                dOrgName.setText(orgList.org);
                dPriEmail.setText(orgList.priEmail);
            }
        });
        //desc

        /*dWebLink.setText(orgList.webLink);
        dFeedbackLink.setText(orgList.feedbackLink);
        dFacebook.setText(orgList.facebook);
        dTwitter.setText(orgList.twitter);
        dYoutube.setText(orgList.youtube);*/
        //dOrgName.setText(dOrg.org);
        dPriEmail.setText(String.valueOf(orgId));

    }
}
