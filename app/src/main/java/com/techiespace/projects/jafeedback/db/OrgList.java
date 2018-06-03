package com.techiespace.projects.jafeedback.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "org_list",
        indices = {@Index(value = "org", unique = true)})
public class OrgList {

    @PrimaryKey(autoGenerate = true)
    public Integer orgId;
    @ColumnInfo
    @NonNull
    public String org;
    @ColumnInfo(name = "web_link")
    public String webLink;
    @ColumnInfo(name = "feedback_link")
    public String feedbackLink;
    @ColumnInfo
    public String priEmail;
    @ColumnInfo
    public String priPhone;

    public OrgList(@NonNull String org, String webLink, String feedbackLink, String priEmail, String priPhone) {
        this.org = org;
        this.webLink = webLink;
        this.feedbackLink = feedbackLink;
        this.priEmail = priEmail;
        this.priPhone = priPhone;
    }

}
