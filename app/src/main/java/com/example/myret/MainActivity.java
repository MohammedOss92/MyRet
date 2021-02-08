package com.example.myret;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.myret.Adapter.MsgTypesAdapter;
import com.example.myret.Modal.MsgTypes;
import com.example.myret.Modal.MsgTypesResponse;
import com.example.myret.Modal.Msgs;
import com.example.myret.Modal.MsgsResponse;
import com.example.myret.Network.ApiClient;
import com.example.myret.Network.ApiClientMsg;
import com.example.myret.Repository.MsgTypesRespository;
import com.example.myret.Repository.MsgsRespository;
import com.example.myret.ViewModal.MsgTypesViewModal;
import com.example.myret.ViewModal.MsgsViewModal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MsgTypesAdapter msgTypesAdapter;
    private List<MsgTypes> msgTypeList= new ArrayList<>();
    MsgTypesRespository msgTypesRespository;
    MsgTypesViewModal msgTypesViewModal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        msgTypesAdapter = new MsgTypesAdapter(MainActivity.this, msgTypeList);

        msgTypesRespository = new MsgTypesRespository(getApplication());
        msgTypesViewModal = new ViewModelProvider(this).get(MsgTypesViewModal.class);
        msgTypesViewModal.getAllMsgTypesm().observe(this, new Observer<List<MsgTypes>>() {
            @Override
            public void onChanged(List<MsgTypes> msgTypesList) {
                msgTypesAdapter.setAllMsgTypes(msgTypesList);
                recyclerView.setAdapter(msgTypesAdapter);

            }
        });

        getAllModals();
    }

    private void getAllModals() {
        Call<MsgTypesResponse>call = ApiClient.getInstance().getApiInterface().getAllMsgTypes();

        call.enqueue(new Callback<MsgTypesResponse>() {
            @Override
            public void onResponse(Call<MsgTypesResponse> call, Response<MsgTypesResponse> response) {
//                msgTypeList = response.body().getMsgTypes();
////                    customAdabter.setData(modals);
//                msgTypesAdapter = new MsgTypesAdapter(MainActivity.this, msgTypeList);
//                recyclerView.setAdapter(msgTypesAdapter);
//                msgTypesAdapter.notifyDataSetChanged();
                if(response.isSuccessful()){
                    msgTypesRespository.insert_msgtypes(response.body().getMsgTypes());
                }
                for(MsgTypes msgTypes : response.body().getMsgTypes()){
                    getAllModals22(msgTypes.getTypeID()) ;
                }
            }

            @Override
            public void onFailure(Call<MsgTypesResponse> call, Throwable t) {

            }
        });
    }

    private void getAllModals22(int TypeDescription) {
        Call<MsgsResponse> call = ApiClientMsg.getInstance().getApiInterface().getAllMsgs(TypeDescription);

        call.enqueue(new Callback<MsgsResponse>() {
            @Override
            public void onResponse(Call<MsgsResponse> call, Response<MsgsResponse> response) {

//                if(response.isSuccessful()){
//                    msgsRespository.Insert(response.body().getMsgs());
//                }


                if (response.isSuccessful()){

                    MsgsResponse msgsResponse = response.body();

                    List<Msgs> msgs = msgsResponse.getMsgs();

//                    Log.d(TAG, "onResponse: SIZE IS: " + msgs.size());

                    for (Msgs msgs1 : msgs){

                        Msgs msgs2 = new Msgs(msgs1.getMsgDescription(),msgs1.getTypeDescription(),msgs1.getNewMsg());
                        msgs2.setMsgID(msgs1.getMsgID());

                        MsgsRespository msgsRespository =new MsgsRespository(getApplication());
                        msgsRespository.Insert(msgs2);

                    }
                }

                Log.e("Success",response.body().toString());
                Log.e("Success", String.valueOf(response.code()));
//                msgsList = response.body().getMsgs();
//                for(Msgs msg : msgsList)
//                    Log.i("Success", msg.getMsgDescription()) ;
////                    customAdabter.setData(modals);
//                msgsAdapter = new MsgsAdapter(MsgsActivity.this, msgsList);
//                recyclerView.setAdapter(msgsAdapter);
//                msgsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<MsgsResponse> call, Throwable t) {

            }
        });
    }

}