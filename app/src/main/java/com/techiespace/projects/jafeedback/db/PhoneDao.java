package com.techiespace.projects.jafeedback.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PhoneDao {

    @Query("SELECT * FROM phone WHERE id = :id LIMIT 1")
    LiveData<Phone> findPhoneById(int id);

    @Insert
    void insert(Phone... phones);

    @Query("SELECT * FROM phone")
    LiveData<List<Phone>> getAllPhone();

    @Query("DELETE FROM org_list")
    void deleteAll();
}
