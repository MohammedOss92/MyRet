package com.example.myret.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myret.Dao.MsgTypesDao;
import com.example.myret.Dao.MsgsDao;
import com.example.myret.Modal.MsgTypes;
import com.example.myret.Modal.Msgs;


@Database(entities = {MsgTypes.class,Msgs.class},version=2)
public abstract class MsgDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "sss";

    public abstract MsgTypesDao msgTypesDao();
    public abstract MsgsDao msgsDao();

    private static volatile MsgDatabase INSTANCE;

    public static MsgDatabase getInstance(Context context) {

        if (INSTANCE == null){
            synchronized (MsgDatabase.class){
                if (INSTANCE == null){
                    INSTANCE= Room.databaseBuilder(context,MsgDatabase.class
                            ,DATABASE_NAME)
                            .addCallback(callback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    static Callback callback= new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InsertMsgAsyncTask(INSTANCE);
        }
    };

    private static class InsertMsgAsyncTask extends AsyncTask<Void,Void,Void>{
        private MsgTypesDao msgTypesDao;
        private MsgsDao msgsDao;

        private InsertMsgAsyncTask(MsgDatabase msgDatabase){
            msgTypesDao=msgDatabase.msgTypesDao();
            msgsDao=msgDatabase.msgsDao();
        }
        @Override
        protected Void doInBackground(Void... Voids) {
            msgTypesDao.deleteAllMsgTypes();
            msgsDao.deleteAllmessage();
            return null;
        }
    }


}

