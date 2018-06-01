package com.techiespace.projects.jafeedback;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.techiespace.projects.jafeedback.db.Description;
import com.techiespace.projects.jafeedback.db.DescriptionDao;
import com.techiespace.projects.jafeedback.db.Email;
import com.techiespace.projects.jafeedback.db.EmailDao;
import com.techiespace.projects.jafeedback.db.OrgDatabase;
import com.techiespace.projects.jafeedback.db.OrgList;
import com.techiespace.projects.jafeedback.db.OrgListDao;
import com.techiespace.projects.jafeedback.db.Phone;
import com.techiespace.projects.jafeedback.db.PhoneDao;
import com.techiespace.projects.jafeedback.db.Social;
import com.techiespace.projects.jafeedback.db.SocialDao;

import java.util.List;

public class OrgDetailsViewModel extends AndroidViewModel {

    private OrgListDao orgListDao;
    private PhoneDao phoneDao;
    private EmailDao emailDao;
    private SocialDao socialDao;
    private DescriptionDao descDao;

    private LiveData<OrgList> orgLiveData;
    private LiveData<List<Phone>> phonesLiveData;
    private LiveData<List<Email>> emailsLiveData;
    private LiveData<List<Social>> socialLiveData;
    private LiveData<List<Description>> descLiveData;

    public OrgDetailsViewModel(@NonNull Application application, int orgId) {
        super(application);
        orgListDao = OrgDatabase.getDatabase(application).orgListDao();
        orgLiveData = orgListDao.getOrg(orgId);

        phoneDao = OrgDatabase.getDatabase(application).phoneDao();
        phonesLiveData = phoneDao.findPhoneById(orgId);

        emailDao = OrgDatabase.getDatabase(application).emailDao();
        emailsLiveData = emailDao.findEmailById(orgId);

        socialDao = OrgDatabase.getDatabase(application).socialDao();
        socialLiveData = socialDao.findSocialById(orgId);

        descDao = OrgDatabase.getDatabase(application).descriptionDao();
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

    public LiveData<List<Social>> getSocailLiveData() {
        return socialLiveData;
    }

    public LiveData<List<Description>> getDescLiveData() {
        return descLiveData;
    }
}
