package com.example.myret.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.myret.Dao.MsgTypesDao;
import com.example.myret.Database.MsgDatabase;
import com.example.myret.Modal.MsgTypes;
import com.example.myret.Modal.Msgs;
import com.example.myret.Modal.MsgsTypeWithCount;

import java.util.List;

public class MsgTypesRespository {

    private MsgDatabase database;
    public LiveData<List<MsgsTypeWithCount>> getAllMsgTypesaa;

    public MsgTypesRespository(Application application) {
        database = MsgDatabase.getInstance(application);
        getAllMsgTypesaa = database.msgTypesDao().getAllMsgTypesWithCounts();
    }

    public void insert_msgtypes(List<MsgTypes>msgTypesList){
        new InsertMsgTypesAsyncTasc(database).execute(msgTypesList);
    }

    public LiveData<List<MsgsTypeWithCount>> getAllMsgTypesaa(){
        return getAllMsgTypesaa;
    }

    class InsertMsgTypesAsyncTasc extends AsyncTask<List<MsgTypes>,Void,Void>{
        private MsgTypesDao msgTypesDao;

        public InsertMsgTypesAsyncTasc(MsgDatabase msgDatabase) {
            msgTypesDao=database.msgTypesDao();
        }

        @Override
        protected Void doInBackground(List<MsgTypes>... lists) {
            msgTypesDao.insert_msgtypes(lists[0]);
            Log.d("Abdullah", "size: " + msgTypesDao.getCount());
            return null;
        }
    }
}
