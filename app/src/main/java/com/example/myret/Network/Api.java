package com.example.myret.Network;


import com.example.myret.Modal.MsgTypesResponse;
import com.example.myret.Modal.Msgs;
import com.example.myret.Modal.MsgsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("select.php")
    Call<MsgTypesResponse> getAllMsgTypes();

    @GET("select.php/{TypeDesc}")
    Call<MsgsResponse> getAllMsgs(
            @Query("TypeDesc") int TypeDescription
    );
}
