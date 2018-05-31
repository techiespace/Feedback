package com.techiespace.projects.jafeedback;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.techiespace.projects.jafeedback.db.OrgList;


public class OrgDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_details);
        OrgList orgList = getIntent().getParcelableExtra("id");
        displayDetails(orgList);
    }

    private void displayDetails(OrgList orgList) {
        TextView dWebLink;
        TextView dFeedbackLink;
        TextView dFacebook;
        TextView dTwitter;
        TextView dYoutube;
        TextView dEmail;

        dWebLink = findViewById(R.id.d_email);
        dFeedbackLink = findViewById(R.id.d_email);
        dFacebook = findViewById(R.id.d_email);
        dTwitter = findViewById(R.id.d_email);
        dYoutube = findViewById(R.id.d_email);
        dEmail = findViewById(R.id.d_email);


        dWebLink.setText(orgList.webLink);
        dFeedbackLink.setText(orgList.feedbackLink);
        dFacebook.setText(orgList.facebook);
        dTwitter.setText(orgList.twitter);
        dYoutube.setText(orgList.youtube);
        dEmail.setText(orgList.email);

    }
}
