package com.techiespace.projects.jafeedback;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.techiespace.projects.jafeedback.databinding.OrgDetailsBinding;
import com.techiespace.projects.jafeedback.db.OrgList;


public class OrgDetailsActivity extends AppCompatActivity {

    public int orgId;
    OrgDetailsViewModel orgDetailsViewModel;
    OrgList dOrg;

    //handle fb old version apps
    public static String FACEBOOK_URL = "https://www.facebook.com/YourPageName";
    public static String FACEBOOK_PAGE_ID = "YourPageName";

    //intents

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_details);
        //OrgList orgList = getIntent().getParcelableExtra("id");
        orgId = getIntent().getIntExtra("id", 1);


        OrgDetailsViewModel orgDetailsViewModel = ViewModelProviders.of(this, new ViewModelFactory(this.getApplication(), orgId)).get(OrgDetailsViewModel.class);
        OrgDetailsBinding orgDetailsBinding = DataBindingUtil.setContentView(this, R.layout.org_details);
        //OrgDetailsBinding.inflate(getLayoutInflater());
//            orgDetailsBinding.setOrgDetailsViewModel(orgDetailsViewModel);

        orgDetailsBinding.setLifecycleOwner(this);
        orgDetailsBinding.setVariable(BR.orgDetailsViewModel, orgDetailsViewModel);
        setContentView(orgDetailsBinding.getRoot());

        displayDetails(orgId);
    }

    private void displayDetails(final int orgId) {
        final TextView dWebLink;
        //TextView dFeedbackLink;
        final TextView dFacebook;
        final TextView dTwitter;
        final TextView dYoutube;
        final TextView dOrgName;
        final TextView dPriEmail;
        final TextView dPriPhone;

        dWebLink = findViewById(R.id.d_web_link);
        //dFeedbackLink = findViewById(R.id.d_email);
        dFacebook = findViewById(R.id.d_facebook);
        dTwitter = findViewById(R.id.d_twitter);
        dYoutube = findViewById(R.id.d_youtube);
        dOrgName = findViewById(R.id.org_name);
        dPriEmail = findViewById(R.id.d_pri_email);
        dPriPhone = findViewById(R.id.d_pri_phone);

        String weblink; //JAVA concept. I'm not able to use these variables to store the values
        String facebbok;//recieved in the onChanged() method
        String twitter;
        String youtube;
        String orgName;
        String priEmail;
        String priPhone;

        //org


        /*orgDetailsViewModel.getOrgLiveData().observe(this, new Observer<OrgList>() {
            @Override
            public void onChanged(@Nullable OrgList orgList) {
                dOrgName.setText(orgList.org);
            }
        });*/

        //email
//        orgDetailsViewModel.getEmailsLiveData().observe(this, new Observer<List<Email>>() {
//            @Override
//            public void onChanged(@Nullable List<Email> emails) {
//                dPriEmail.setText(emails.get(0).email);
//                dPriEmail.setOnClickListener(new View.OnClickListener(){
//
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(OrgDetailsActivity.this, "Hi", Toast.LENGTH_SHORT).show();
//                        //Intent emailInent = new Intent(Intent.ACTION_DIAL);
//                        //emailInent.setData(Uri.parse("tel:"+phones.get(0).phone));
//                    }
//                });
//            }
//        });
//
//        //phone
//        orgDetailsViewModel.getPhonesLiveData().observe(this, new Observer<List<Phone>>() {
//            @Override
//            public void onChanged(@Nullable List<Phone> phones) {
//                dPriPhone.setText(phones.get(0).phone);
//            }
//        });
//
//        //social
//        orgDetailsViewModel.getSocailLiveData().observe(this, new Observer<List<Social>>() {
//            @Override
//            public void onChanged(@Nullable List<Social> socials) {
//                dFacebook.setText(socials.get(0).facebook);
//                dTwitter.setText(socials.get(0).twitter);
//                dYoutube.setText(socials.get(0).youtube);
//            }
//        });
//
//        //desc
//        orgDetailsViewModel.getDescLiveData().observe(this, new Observer<List<Description>>() {
//            @Override
//            public void onChanged(@Nullable List<Description> descriptions) {
//                dWebLink.setText(descriptions.get(0).website);
//            }
//        });
//
//
//        dPriPhone.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        dFacebook.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        dTwitter.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        dYoutube.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        dWebLink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


    }

    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }
}
