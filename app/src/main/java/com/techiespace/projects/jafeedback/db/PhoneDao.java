package com.techiespace.projects.jafeedback.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PhoneDao {

    @Query("SELECT * FROM phone WHERE org_id = :id LIMIT 1")
    LiveData<List<Phone>> findPhoneById(int id);

    @Insert
    void insert(Phone... phones);

    @Query("DELETE FROM org_list")
    void deleteAll();

    @Query("UPDATE phone SET phone = :phone WHERE org_id = :id")
    int updatePhone(String phone, int id);
}
