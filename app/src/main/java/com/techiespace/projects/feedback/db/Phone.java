package com.techiespace.projects.feedback.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = @ForeignKey(entity = OrgList.class,
        parentColumns = "orgId",
        childColumns = "org_id"))
public class Phone {

    @ColumnInfo(name = "org_id")
    public int orgId;

    @ColumnInfo
    @NonNull
    @PrimaryKey
    public String phone;

    @ColumnInfo
    public String desc;

    @Ignore
    public Phone(@NonNull String phone, int orgId) {
        this.phone = phone;
        this.orgId = orgId;
        this.desc = "Main Office";
    }

    public Phone(@NonNull String phone, int orgId, String desc) {
        this.phone = phone;
        this.orgId = orgId;
        this.desc = desc;
    }
}
