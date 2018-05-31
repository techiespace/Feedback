package com.techiespace.projects.jafeedback.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = @ForeignKey(entity = OrgList.class,
        parentColumns = "orgId",
        childColumns = "org_id"))
public class Email {

    @ColumnInfo(name = "org_id")
    public int orgId;

    @ColumnInfo
    @PrimaryKey
    @NonNull
    public String email;

    public Email(String email, int orgId) {
        this.email = email;
        this.orgId = orgId;
    }
}
