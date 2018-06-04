package com.techiespace.projects.feedback.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DescriptionDao {

    @Query("SELECT * FROM description WHERE org_id = :id LIMIT 1")
    LiveData<List<Description>> findDescriptionById(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Description... descriptions);

    @Query("DELETE FROM description")
    void deleteAll();

    @Query("UPDATE description SET website = :webLink WHERE org_id = :id")
    int updateWebLink(String webLink, int id);

}
