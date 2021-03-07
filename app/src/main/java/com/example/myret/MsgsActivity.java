package com.example.myret;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.myret.Adapter.MsgsAdap;
import com.example.myret.Adapter.MsgsAdapter;
import com.example.myret.Modal.Msgs;
import com.example.myret.Modal.MsgsResponse;
import com.example.myret.Network.ApiClientMsg;
import com.example.myret.Repository.MsgsRespository;
import com.example.myret.ViewModal.MsgTypesViewModal;
import com.example.myret.ViewModal.MsgsViewModal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MsgsActivity extends AppCompatActivity {

    private static final String TAG = "MsgsActivity";
    RecyclerView recyclerView;
    private List<Msgs>msgsList = new ArrayList<>();
    MsgsAdapter msgsAdapter;
    MsgsAdap msgsAdap;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    int TypeDescription;
    int titleID;
    MsgsRespository msgsRespository;
    MsgsViewModal msgsViewModal;
    String filterValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msgs);

        Intent i = getIntent();
        if (i.getExtras()!=null) {
            TypeDescription = i.getExtras().getInt("TypeDescription");
        }


//        msgsViewModal = ViewModelProviders.of(MsgsActivity.this).get(MsgsViewModal.class);
        msgsViewModal = new ViewModelProvider(this).get(MsgsViewModal.class);
        recyclerView = findViewById(R.id.recyclerviewMsgs);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);


        recyclerView.setItemAnimator(new DefaultItemAnimator());

        msgsRespository = new MsgsRespository(getApplication());
//        msgsAdapter = new MsgsAdapter(MsgsActivity.this);
        msgsAdap = new MsgsAdap(MsgsActivity.this);
        recyclerView.setAdapter(msgsAdap);
//        recyclerView.setAdapter(msgsAdapter);

        Log.d(TAG, "onCreate: TYPE DESC: " + TypeDescription);

        msgsViewModal.getAllMsg(TypeDescription).observe(this, new Observer<List<Msgs>>() {
            @Override
            public void onChanged(List<Msgs> msgsList) {

                Log.d(TAG, "onChanged: Size of list is" + msgsList.size());
                msgsAdap.setAllMsgs(msgsList);
//                recyclerView.setAdapter(msgsAdapter);
//                msgsAdapter.submitList(msgsList);
                msgsAdap.notifyDataSetChanged();
            }
        });

        msgsViewModal.getMsgFilter(TypeDescription,filterValue).observe(this, new Observer<List<Msgs>>() {
            @Override
            public void onChanged(List<Msgs> msgsList) {

            }
        });

        getAllModals();
    }

    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_msg,menu);
        return true;
    }

    private void getAllModals() {
        Call<MsgsResponse> call = ApiClientMsg.getInstance().getApiInterface().getAllMsgs(TypeDescription);

        call.enqueue(new Callback<MsgsResponse>() {
            @Override
            public void onResponse(Call<MsgsResponse> call, Response<MsgsResponse> response) {

//                if(response.isSuccessful()){
//                    msgsRespository.insertmsgs(response.body().getMsgs());
//                }

                if (response.isSuccessful()){

                    MsgsResponse msgsResponse = response.body();

                    List<Msgs> msgs = msgsResponse.getMsgs();

                    Log.d(TAG, "onResponse: SIZE IS: " + msgs.size());

                    for (Msgs msgs1 : msgs){

                        Msgs msgs2 = new Msgs(msgs1.getMsgDescription(),msgs1.getTypeDescription(),msgs1.getNewMsg());
                        msgs2.setMsgID(msgs1.getMsgID());
                        msgsViewModal.Insert(msgs2);

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