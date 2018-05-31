package com.techiespace.projects.jafeedback.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = @ForeignKey(entity = OrgList.class,
        parentColumns = "orgId",
        childColumns = "id"))
public class Phone {

    @ColumnInfo
    public int id;

    @ColumnInfo
    @NonNull
    @PrimaryKey
    public String phone;

    @ColumnInfo
    public String desc;

    @Ignore
    public Phone(@NonNull String phone, int id) {
        this.phone = phone;
        this.id = id;
        this.desc = "Main Office";
    }

    public Phone(@NonNull String phone, int id, String desc) {
        this.phone = phone;
        this.id = id;
        this.desc = desc;
    }
}
