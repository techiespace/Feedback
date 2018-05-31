package com.techiespace.projects.jafeedback.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface OrgListDao {
    @Insert
    void insert(OrgList... orgList);

    @Insert
    long insert(OrgList orgList);

    @Query("SELECT * FROM org_list")
    LiveData<List<OrgList>> getAllOrg();

    @Query("DELETE FROM org_list")
    void deleteAll();

}
