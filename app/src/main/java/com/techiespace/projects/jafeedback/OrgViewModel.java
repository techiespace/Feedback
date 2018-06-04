package com.techiespace.projects.jafeedback;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.techiespace.projects.jafeedback.db.OrgDatabase;
import com.techiespace.projects.jafeedback.db.OrgList;
import com.techiespace.projects.jafeedback.db.OrgListDao;

import java.util.List;

public class OrgViewModel extends AndroidViewModel {

    private OrgListDao orgListDao;
    private LiveData<List<OrgList>> orgsLiveData;
    private LiveData<List<OrgList>> findOrgsLiveData;

    public OrgViewModel(@NonNull Application application) {
        super(application);
        orgListDao = OrgDatabase.getDatabase(application).orgListDao();
        orgsLiveData = orgListDao.getAllOrg();
    }

    public LiveData<List<OrgList>> getOrgsLiveData() {
        return orgsLiveData;
    }

    public LiveData<List<OrgList>> findOrgListData(String searchText) {
        return orgListDao.findOrgList(searchText);
    }

    public void insert(OrgList... orgLists) {
        orgListDao.insert(orgLists);
    }

    public void deleteAll() {
        orgListDao.deleteAll();
    }
}
