package com.example.myret.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.myret.Dao.MsgsDao;
import com.example.myret.Database.MsgDatabase;
import com.example.myret.Modal.Msgs;

import java.util.List;

public class MsgsRespository {
    private static final String TAG = "MsgsRespository";

    private MsgDatabase database;
    int TypeDescription;
    public MsgsRespository(Application application) {
        database=MsgDatabase.getInstance(application);

    }

    public void Insert(Msgs msgs){
        new InsertAsynTask(database).execute(msgs);
    }

    public LiveData<List<Msgs>> getAllMsgs(int TypeDescription){
        return database.msgsDao().getAllMsgsDao(TypeDescription);
    }

    public LiveData<List<Msgs>> getMsgFilter (int TypeDescription,String filterValue){
        return database.msgsDao().getMsgFilterDao(TypeDescription, filterValue);
    }

    class InsertAsynTask extends AsyncTask<Msgs,Void,Void> {
        private MsgsDao msgsDao;
        InsertAsynTask(MsgDatabase msgDatabase) {
            msgsDao=msgDatabase.msgsDao();
        }
        @Override
        protected Void doInBackground(Msgs... msgs) {

            Log.d(TAG, "doInBackground: " + msgs[0].getMsgDescription());
            Log.d(TAG, "doInBackground: " + msgs[0].getTypeDescription());
            Log.d(TAG, "doInBackground: " + msgs[0].getNewMsg());

            msgsDao.insert_msgs(msgs[0]);
            Log.d("count","gf"+msgsDao.getCount(TypeDescription));
            return null;
        }
    }
}
