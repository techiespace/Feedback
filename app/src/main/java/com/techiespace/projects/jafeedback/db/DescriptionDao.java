package com.techiespace.projects.jafeedback.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DescriptionDao {

    @Query("SELECT * FROM description WHERE org_id = :id LIMIT 1")
    LiveData<List<Description>> findDescriptionById(int id);

    @Insert
    void insert(Description... descriptions);

    @Query("SELECT * FROM description")
    LiveData<List<Description>> getAllDescriptions();

    @Query("DELETE FROM description")
    void deleteAll();

}
