package com.techiespace.projects.feedback.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface OrgListDao {
    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    void insert(OrgList... orgList);

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    long insert(OrgList orgList);

    @Query("SELECT * FROM org_list WHERE orgId = :id LIMIT 1")
    LiveData<OrgList> getOrg(int id);

    @Query("SELECT * FROM org_list ORDER BY org ASC")
    LiveData<List<OrgList>> getAllOrg();

    @Query("DELETE FROM org_list")
    void deleteAll();

    @Query("UPDATE org_list SET priPhone = :phone WHERE orgId = :id")
    int updatePhone(String phone, int id);

    @Query("UPDATE org_list SET priEmail = :em WHERE orgId = :id")
    int updateEmail(String em, int id);

    @Query("SELECT * FROM org_list WHERE org LIKE :orgText")
    LiveData<List<OrgList>> findOrgList(String orgText);
}
