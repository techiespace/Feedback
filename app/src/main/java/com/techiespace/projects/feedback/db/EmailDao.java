package com.techiespace.projects.feedback.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface EmailDao {

    @Query("SELECT * FROM email WHERE org_id = :id LIMIT 1")
    LiveData<List<Email>> findEmailById(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Email... emails);

    @Query("DELETE FROM org_list")
    void deleteAll();

    @Query("UPDATE email SET email = :em WHERE org_id = :id")
    int updateEmail(String em, int id);
}
