package com.techiespace.projects.feedback;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

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

public class OrgDetailsViewModel extends AndroidViewModel {

    private LiveData<OrgList> orgLiveData;
    private LiveData<List<Phone>> phonesLiveData;
    private LiveData<List<Email>> emailsLiveData;
    private LiveData<List<Social>> socialLiveData;
    private LiveData<List<Description>> descLiveData;

    public OrgDetailsViewModel(@NonNull Application application, int orgId) {
        super(application);
        OrgListDao orgListDao = OrgDatabase.getDatabase(application).orgListDao();
        orgLiveData = orgListDao.getOrg(orgId);

        PhoneDao phoneDao = OrgDatabase.getDatabase(application).phoneDao();
        phonesLiveData = phoneDao.findPhoneById(orgId);

        EmailDao emailDao = OrgDatabase.getDatabase(application).emailDao();
        emailsLiveData = emailDao.findEmailById(orgId);

        SocialDao socialDao = OrgDatabase.getDatabase(application).socialDao();
        socialLiveData = socialDao.findSocialById(orgId);

        DescriptionDao descDao = OrgDatabase.getDatabase(application).descriptionDao();
        descLiveData = descDao.findDescriptionById(orgId);
    }

    public LiveData<OrgList> getOrgLiveData() {
        return orgLiveData;
    }

    public LiveData<List<Phone>> getPhonesLiveData() {
        return phonesLiveData;
    }

    public LiveData<List<Email>> getEmailsLiveData() {
        return emailsLiveData;
    }

    public LiveData<List<Social>> getSocialLiveData() {
        return socialLiveData;
    }

    public LiveData<List<Description>> getDescLiveData() {
        return descLiveData;
    }
}
