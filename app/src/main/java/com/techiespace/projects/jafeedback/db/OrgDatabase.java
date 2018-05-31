package com.techiespace.projects.jafeedback.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {OrgList.class, Phone.class}, version = 1)
public abstract class OrgDatabase extends RoomDatabase {
    private static final String DB_NAME = "org.db";
    private static final Executor executor = Executors.newFixedThreadPool(2);
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
                                        //executor.execute(new Runnable() {
                                        //    @Override
                                        //    public void run() {
                                        new PopulateDbAsync(INSTANCE).execute();
                                        //   }
                                        //});
                                    }
                                }
                            }).build();
                }
            }
        }
        return INSTANCE;
    }

    public void clearDb() {  //Is this ever called?
        if (INSTANCE != null)
            new PopulateDbAsync(INSTANCE).execute();
    }

    public abstract OrgListDao orgListDao();

    public abstract PhoneDao phoneDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final OrgListDao orgListDao;
        private final PhoneDao phoneDao;

        private PopulateDbAsync(OrgDatabase instance) {
            orgListDao = instance.orgListDao();
            phoneDao = instance.phoneDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            orgListDao.deleteAll();
            phoneDao.deleteAll();

            OrgList orgOne = new OrgList("Google", "www.google.com", "g.co/feedback", "fb.com/google", "twitter.com/google", "youtube.com/google", "google@gmail.com");
            OrgList orgTwo = new OrgList("Youtube", "www.youtube.com", "youtube.com/feedback", "fb.com/youtube", "youtube.com/twitter", "youtube.com", "yt@gmail.com");

            Phone phoneOne = new Phone("1234567890", (int) orgListDao.insert(orgOne));
            Phone phoneTwo = new Phone("0987654321", (int) orgListDao.insert(orgTwo));

            phoneDao.insert(phoneOne, phoneTwo);
            return null;
        }
    }

}
