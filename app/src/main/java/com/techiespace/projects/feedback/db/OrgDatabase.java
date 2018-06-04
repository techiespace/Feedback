package com.techiespace.projects.feedback.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

@Database(entities = {OrgList.class, Phone.class, Email.class, Social.class, Description.class}, version = 1, exportSchema = false)
public abstract class OrgDatabase extends RoomDatabase {
    private static final String DB_NAME = "org.db";
    public static OrgDatabase INSTANCE;

    public static OrgDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (OrgDatabase.class) {   //if this class is singleton then why do we need this?
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            OrgDatabase.class, DB_NAME)//.allowMainThreadQueries()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    {
                                        super.onCreate(db);
                                        Log.d("org.db", "onCreate: Population with data...");
                                        new PopulateDbAsync(INSTANCE).execute();
                                    }
                                }
                            }).build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract OrgListDao orgListDao();

    public abstract PhoneDao phoneDao();

    public abstract EmailDao emailDao();

    public abstract SocialDao socialDao();

    public abstract DescriptionDao descriptionDao();


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final OrgListDao orgListDao;
        private final PhoneDao phoneDao;
        private final EmailDao emailDao;
        private final SocialDao socialDao;
        private final DescriptionDao descriptionDao;

        private PopulateDbAsync(OrgDatabase instance) {
            orgListDao = instance.orgListDao();
            phoneDao = instance.phoneDao();
            emailDao = instance.emailDao();
            socialDao = instance.socialDao();
            descriptionDao = instance.descriptionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            orgListDao.deleteAll();
            phoneDao.deleteAll();
            emailDao.deleteAll();
            socialDao.deleteAll();
            descriptionDao.deleteAll();

            OrgList orgOne = new OrgList("Google", "www.google.com", "g.co/feedback", "google@gmail.com", "1234657980");
            OrgList orgTwo = new OrgList("Youtube", "www.youtube.com", "youtube.com/feedback", "yt@gmail.com", "9876543210");

            int orgOneId = (int) orgListDao.insert(orgOne);
            int orgTwoId = (int) orgListDao.insert(orgTwo);
            Phone phoneOne = new Phone("1234567890", orgOneId);
            Phone phoneTwo = new Phone("0987654321", orgTwoId, "California Office");

            Email emailOne = new Email("google@gmail.com", orgOneId);
            Email emailTwo = new Email("yt@gmail.com", orgTwoId);

            Social socOne = new Social(orgOneId, "google", "Google", "Google");
            Social socTwo = new Social(orgTwoId, "youtube", "YouTube", "youtube");

            Description descOne = new Description(orgOneId, "google.com");
            Description descTwo = new Description(orgTwoId, "youtube.com");
            phoneDao.insert(phoneOne, phoneTwo);
            emailDao.insert(emailOne, emailTwo);
            socialDao.insert(socOne, socTwo);
            descriptionDao.insert(descOne, descTwo);
            return null;
        }
    }

}
