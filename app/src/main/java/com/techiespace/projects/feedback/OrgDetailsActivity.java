package com.techiespace.projects.feedback;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.techiespace.projects.feedback.databinding.OrgDetailsBinding;


public class OrgDetailsActivity extends AppCompatActivity {

    public int orgId;
    OrgDetailsBinding orgDetailsBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_details);
        orgId = getIntent().getIntExtra("id", 1);
        OrgDetailsViewModel orgDetailsViewModel = ViewModelProviders.of(this, new ViewModelFactory(this.getApplication(), orgId)).get(OrgDetailsViewModel.class);
        orgDetailsBinding = DataBindingUtil.setContentView(this, R.layout.org_details);
        orgDetailsBinding.setLifecycleOwner(this);
        orgDetailsBinding.setOrgDetailsViewModel(orgDetailsViewModel);
        setContentView(orgDetailsBinding.getRoot());
        displayDetails(orgId);
    }

    private void displayDetails(final int orgId) {
        orgDetailsBinding.dPriEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setType("text/plain");
                emailIntent.setData(Uri.parse("mailto:" + orgDetailsBinding.dPriEmail.getText()));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello Madam/Sir,");
                if (emailIntent.resolveActivity(getPackageManager()) != null)
                    startActivity(Intent.createChooser(emailIntent, "Choose an email client :)"));
                else
                    Toast.makeText(OrgDetailsActivity.this, "No suitable app found to send email :(", Toast.LENGTH_LONG).show();
            }
        });
        orgDetailsBinding.dPriEmail.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogFragment dialogFragment = EditDataFragment.newInstance(orgId, "email", orgDetailsBinding.dPriEmail.getText().toString());
                dialogFragment.show(OrgDetailsActivity.this.getSupportFragmentManager(), "dialog_view_edit");    //What is the use of tag
                return true;
            }
        });
        orgDetailsBinding.dPriPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:" + orgDetailsBinding.dPriPhone.getText()));
                if (phoneIntent.resolveActivity(getPackageManager()) != null)
                    startActivity(phoneIntent);
                else
                    Toast.makeText(OrgDetailsActivity.this, "No suitable app found to make a phone call :(", Toast.LENGTH_LONG).show();
            }
        });
        orgDetailsBinding.dPriPhone.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogFragment dialogFragment = EditDataFragment.newInstance(orgId, "phone", orgDetailsBinding.dPriPhone.getText().toString());
                dialogFragment.show(OrgDetailsActivity.this.getSupportFragmentManager(), "dialog_view_edit");    //What is the use of tag
                return true;
            }
        });
        orgDetailsBinding.dFacebook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String[] fbLink = getFacebookPageURL(OrgDetailsActivity.this, orgDetailsBinding.dFacebook.getText().toString());
                Intent fbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fbLink[0]));
//                if(fbLink[1].equals("fbLite")){
//                    Log.d("test", "getFacebookPageURL: fbLite2");
//                    //fbIntent.setPackage("com.facebook.lite");
//                }
                startActivity(fbIntent);
            }
        });
        orgDetailsBinding.dFacebook.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                DialogFragment dialogFragment = EditDataFragment.newInstance(orgId, "fb", orgDetailsBinding.dFacebook.getText().toString());
                dialogFragment.show(OrgDetailsActivity.this.getSupportFragmentManager(), "dialog_view_edit");    //What is the use of tag
                return true;
            }
        });
        orgDetailsBinding.dTwitter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent twIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + orgDetailsBinding.dTwitter.getText().toString()));
                startActivity(twIntent);
            }
        });
        orgDetailsBinding.dTwitter.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                DialogFragment dialogFragment = EditDataFragment.newInstance(orgId, "tw", orgDetailsBinding.dTwitter.getText().toString());
                dialogFragment.show(OrgDetailsActivity.this.getSupportFragmentManager(), "dialog_view_edit");    //What is the use of tag
                return true;
            }
        });
        orgDetailsBinding.dYoutube.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent ytIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/user/" + orgDetailsBinding.dYoutube.getText().toString()));
                startActivity(ytIntent);
            }
        });
        orgDetailsBinding.dYoutube.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                DialogFragment dialogFragment = EditDataFragment.newInstance(orgId, "yt", orgDetailsBinding.dYoutube.getText().toString());
                dialogFragment.show(OrgDetailsActivity.this.getSupportFragmentManager(), "dialog_view_edit");    //What is the use of tag
                return true;
            }
        });
        orgDetailsBinding.dWebLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + orgDetailsBinding.dWebLink.getText().toString()));
                startActivity(webIntent);
            }
        });
        orgDetailsBinding.dWebLink.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogFragment dialogFragment = EditDataFragment.newInstance(orgId, "web_link", orgDetailsBinding.dWebLink.getText().toString());
                dialogFragment.show(OrgDetailsActivity.this.getSupportFragmentManager(), "dialog_view_edit");    //What is the use of tag
                return true;
            }
        });
    }

    //method to get the right URL to use in the intent
    public String[] getFacebookPageURL(Context context, String facebook) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo fbAppInfo = getPackageManager().getApplicationInfo("com.facebook.katana", 0);
            if (fbAppInfo.enabled) {
                int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
                if (versionCode >= 3002850) { //newer versions of fb app
                    return new String[]{"fb://facewebmodal/f?href=https://www.facebook.com/" + facebook, "fb"};
                } else { //older versions of fb app
                    return new String[]{"fb://page/" + facebook};
                }
            }

        } catch (PackageManager.NameNotFoundException e) {
//            try{
//                ApplicationInfo fbLiteAppInfo = getPackageManager().getApplicationInfo("com.facebook.lite", 0);
//                if(fbLiteAppInfo.enabled){
//                    String[]  tempUrl = {"fb://facewebmodal/f?href=https://www.facebook.com/" + facebook,"fbLite"};
//                    return tempUrl;
//                }
//            }
//            catch (PackageManager.NameNotFoundException ex) {
            return new String[]{"https://www.facebook.com/" + facebook, "web"}; //normal web url
//            }
        }
        return new String[]{"https://www.facebook.com/" + facebook, "web"};
    }
}
