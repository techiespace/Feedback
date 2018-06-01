package com.techiespace.projects.jafeedback.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SocialDao {
    @Query("SELECT * FROM social WHERE org_id = :id LIMIT 1")
    LiveData<List<Social>> findSocialById(int id);

    @Insert
    void insert(Social... socials);

    @Query("SELECT * FROM social")
    LiveData<List<Social>> getAllSocial();

    @Query("DELETE FROM social")
    void deleteAll();

}
