package com.techiespace.projects.jafeedback.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface EmailDao {

    @Query("SELECT * FROM email WHERE org_id = :id LIMIT 1")
    LiveData<List<Email>> findEmailById(int id);

    @Insert
    void insert(Email... emails);

    @Query("SELECT * FROM email")
    LiveData<List<Email>> getAllEmail();

    @Query("DELETE FROM org_list")
    void deleteAll();
}
