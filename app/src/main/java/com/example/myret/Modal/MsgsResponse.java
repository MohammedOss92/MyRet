package com.example.myret.Modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MsgsResponse {

    //المشكله كانت هنا
    @SerializedName("Msgs")
    List<Msgs> msgs;

    public MsgsResponse() {
    }

    public MsgsResponse(List<Msgs> msgs) {
        this.msgs = msgs;
    }

    public List<Msgs> getMsgs() {
        return msgs;
    }

    public void setMsgsList(List<Msgs> msgs) {
        this.msgs = msgs;
    }
}
