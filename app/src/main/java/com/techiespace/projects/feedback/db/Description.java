package com.techiespace.projects.feedback.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = @ForeignKey(entity = OrgList.class,
        parentColumns = "orgId",
        childColumns = "org_id"))
public class Description {

    @ColumnInfo(name = "org_id")
    public int org_id;

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int descId;

    @ColumnInfo
    public String website;

    public Description(int org_id, String website) {
        this.website = website;
        this.org_id = org_id;
    }

}
