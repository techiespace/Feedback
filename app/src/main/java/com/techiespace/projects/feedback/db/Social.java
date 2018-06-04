package com.techiespace.projects.feedback.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = OrgList.class,
        parentColumns = "orgId",
        childColumns = "org_id"))
public class Social {

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    public int socialId;

    @ColumnInfo(name = "org_id")
    public int orgId;

    @ColumnInfo
    public String facebook;

    @ColumnInfo
    public String twitter;

    @ColumnInfo
    public String youtube;

    public Social(int orgId, String facebook, String twitter, String youtube) {
        this.orgId = orgId;
        this.facebook = facebook;
        this.twitter = twitter;
        this.youtube = youtube;
    }
}
