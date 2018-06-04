package com.techiespace.projects.feedback;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techiespace.projects.feedback.db.Description;
import com.techiespace.projects.feedback.db.DescriptionDao;
import com.techiespace.projects.feedback.db.Email;
import com.techiespace.projects.feedback.db.EmailDao;
import com.techiespace.projects.feedback.db.OrgDatabase;
import com.techiespace.projects.feedback.db.OrgList;
import com.techiespace.projects.feedback.db.OrgListDao;
import com.techiespace.projects.feedback.db.Phone;
import com.techiespace.projects.feedback.db.PhoneDao;
import com.techiespace.projects.feedback.db.Social;
import com.techiespace.projects.feedback.db.SocialDao;

import java.util.List;

public class AddOrgActivity extends AppCompatActivity {

    final SocialDao socialDao = OrgDatabase.getDatabase(this).socialDao();
    Button saveOrg;
    private LiveData<OrgList> orgLiveData;
    private LiveData<List<Phone>> phonesLiveData;
    private LiveData<List<Email>> emailsLiveData;
    private LiveData<List<Social>> socialLiveData;
    private LiveData<List<Description>> descLiveData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_org_details);
        saveOrg = findViewById(R.id.save_org_button);
    }

    public void saveOrg(View view) {

        EditText addOrg = findViewById(R.id.d_edit_org_name);
        String strAddOrg = addOrg.getText().toString();
        EditText addEmail = findViewById(R.id.d_edit_pri_email);
        String strAddEmail = addEmail.getText().toString();
        EditText addPhone = findViewById(R.id.d_edit_pri_phone);
        String strAddPhone = addPhone.getText().toString();
        EditText addFb = findViewById(R.id.d_edit_facebook);
        String strAddFb = addFb.getText().toString();
        EditText addTw = findViewById(R.id.d_edit_twitter);
        String strAddTw = addTw.getText().toString();
        EditText addYt = findViewById(R.id.d_edit_youtube);
        String strAddYt = addYt.getText().toString();
        EditText addWebsite = findViewById(R.id.d_edit_web_link);
        String strAddWebsite = addWebsite.getText().toString();

        if (strAddOrg.length() == 0) {
            Toast.makeText(this, "Please enter a Name", Toast.LENGTH_SHORT).show();
            saveOrg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveOrg(v);
                }

            });
        } else {
            new AddDbAsync(this, strAddOrg, strAddWebsite, "feedback_link", strAddEmail, strAddPhone, strAddFb, strAddTw, strAddYt, strAddWebsite).execute();

            finish();
        }

    }

    private static class AddDbAsync extends AsyncTask<Void, Void, Void> {
        private final OrgListDao orgListDao;
        private final PhoneDao phoneDao;
        private final EmailDao emailDao;
        private final SocialDao socialDao;
        private final DescriptionDao descriptionDao;

        String orgName, website, feedback, valEmail, valPhone, fb, tw, yt, web;

        private AddDbAsync(Context context, String orgName, String website, String feedback, String valEmail, String valPhone, String fb, String tw, String yt, String web) {
            orgListDao = OrgDatabase.getDatabase(context).orgListDao();
            phoneDao = OrgDatabase.getDatabase(context).phoneDao();
            emailDao = OrgDatabase.getDatabase(context).emailDao();
            socialDao = OrgDatabase.getDatabase(context).socialDao();
            descriptionDao = OrgDatabase.getDatabase(context).descriptionDao();

            this.orgName = orgName;
            this.website = website;
            this.feedback = feedback;
            this.valEmail = valEmail;
            this.valPhone = valPhone;
            this.fb = fb;
            this.tw = tw;
            this.yt = yt;
            this.web = web;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            OrgList org = new OrgList(orgName, website, "g.co/feedback", valEmail, valPhone);

            int orgId = (int) orgListDao.insert(org);
            Phone phone = new Phone(valPhone, orgId);

            Email email = new Email(valEmail, orgId);

            Social soc = new Social(orgId, fb, tw, yt);

            Description desc = new Description(orgId, website);
            phoneDao.insert(phone);
            emailDao.insert(email);
            socialDao.insert(soc);
            descriptionDao.insert(desc);
            return null;
        }
    }
}
