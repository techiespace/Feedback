package com.techiespace.projects.jafeedback;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.techiespace.projects.jafeedback.db.Email;
import com.techiespace.projects.jafeedback.db.EmailDao;
import com.techiespace.projects.jafeedback.db.OrgDatabase;
import com.techiespace.projects.jafeedback.db.OrgList;
import com.techiespace.projects.jafeedback.db.OrgListDao;
import com.techiespace.projects.jafeedback.db.Phone;
import com.techiespace.projects.jafeedback.db.PhoneDao;

import java.util.List;

public class OrgViewModel extends AndroidViewModel {

    private OrgListDao orgListDao;
    private PhoneDao phoneDao;
    private EmailDao emailDao;
    private LiveData<List<OrgList>> orgsLiveData;
    private LiveData<List<Phone>> phoneLiveData;
    private LiveData<List<Email>> emailLiveData;

    public OrgViewModel(@NonNull Application application) {
        super(application);
        orgListDao = OrgDatabase.getDatabase(application).orgListDao();
        orgsLiveData = orgListDao.getAllOrg();
//        phoneDao = OrgDatabase.ge6findEmailById(orgId);
    }

    public LiveData<List<OrgList>> getOrgsLiveData() {
        return orgsLiveData;
    }

    public void insert(OrgList... orgLists) {
        orgListDao.insert(orgLists);
    }

    public void deleteAll() {
        orgListDao.deleteAll();
    }
}
