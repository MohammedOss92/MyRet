package com.example.myret.ViewModal;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myret.Dao.MsgsDao;
import com.example.myret.Database.MsgDatabase;
import com.example.myret.Modal.Msgs;
import com.example.myret.Repository.MsgsRespository;

import java.util.List;

public class MsgsViewModal extends AndroidViewModel {

    private MsgsRespository msgsRespository;
    private LiveData<List<Msgs>>getAllMsgs;

    public MsgsViewModal(@NonNull Application application) {
        super(application);
        msgsRespository=new MsgsRespository(application);

    }

    public void Insert (Msgs msgs){
        msgsRespository.Insert(msgs);
    }

    public LiveData<List<Msgs>>getAllMsg(int TypeDescription){

        return msgsRespository.getAllMsgs(TypeDescription);
    }

    public LiveData<List<Msgs>> getMsgFilter (int TypeDescription,String filterValue){
        return msgsRespository.getMsgFilter(TypeDescription, filterValue);
    }
}
