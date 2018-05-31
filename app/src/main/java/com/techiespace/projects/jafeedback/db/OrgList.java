package com.techiespace.projects.jafeedback.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "org_list",
        indices = {@Index(value = "org", unique = true)})
public class OrgList implements Parcelable {


    public static final Creator<OrgList> CREATOR = new Creator<OrgList>() {
        @Override
        public OrgList createFromParcel(Parcel in) {
            return new OrgList(in);
        }

        @Override
        public OrgList[] newArray(int size) {
            return new OrgList[size];
        }
    };
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
    public String facebook;
    @ColumnInfo
    public String twitter;
    @ColumnInfo
    public String youtube;
    @ColumnInfo
    public String priEmail;
    @ColumnInfo
    public String priPhone;

    public OrgList(@NonNull String org, String webLink, String feedbackLink, String facebook, String twitter, String youtube, String priEmail, String priPhone) {
        this.org = org;
        this.webLink = webLink;
        this.feedbackLink = feedbackLink;
        this.facebook = facebook;
        this.twitter = twitter;
        this.youtube = youtube;
        this.priEmail = priEmail;
        this.priPhone = priPhone;
    }

    public OrgList(Parcel in) {
        this.orgId = in.readInt();
        org = in.readString();
        webLink = in.readString();
        feedbackLink = in.readString();
        facebook = in.readString();
        twitter = in.readString();
        youtube = in.readString();
        priEmail = in.readString();
        priPhone = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(orgId);
        dest.writeString(org);
        dest.writeString(webLink);
        dest.writeString(feedbackLink);
        dest.writeString(facebook);
        dest.writeString(youtube);
        dest.writeString(twitter);
        dest.writeString(priEmail);
        dest.writeString(priPhone);
    }
}
