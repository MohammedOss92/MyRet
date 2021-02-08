package com.example.myret.ViewModal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myret.Modal.MsgTypes;
import com.example.myret.Repository.MsgTypesRespository;

import java.util.List;

public class MsgTypesViewModal extends AndroidViewModel {

    private MsgTypesRespository msgTypesRespository;
    private LiveData<List<MsgTypes>>getAllMsgTypesaa;

    public MsgTypesViewModal(@NonNull Application application) {
        super(application);
        msgTypesRespository = new MsgTypesRespository(application);
        getAllMsgTypesaa = msgTypesRespository.getAllMsgTypesaa();
    }

    public void insert_msgtypes (List<MsgTypes> list){
        msgTypesRespository.insert_msgtypes(list);
    }

    public LiveData<List<MsgTypes>> getAllMsgTypesm(){

        return getAllMsgTypesaa ;
    }
}
