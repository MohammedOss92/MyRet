package com.example.myret.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myret.Modal.MsgTypes;
import com.example.myret.Modal.Msgs;
import com.example.myret.R;

import java.util.ArrayList;
import java.util.List;

public class MsgsAdap extends RecyclerView.Adapter<MsgsAdap.ViewHolder> {
    private Context context;
    private List<Msgs> msgsList=new ArrayList<>();

    public MsgsAdap(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public MsgsAdap.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_msgs,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MsgsAdap.ViewHolder holder, int position) {

        Msgs msgs = msgsList.get(position);
        holder.txt_msg.setText(msgs.getMsgDescription());

    }

    public void setAllMsgs(List<Msgs> msgsList) {
        this.msgsList = msgsList;
    }

    @Override
    public int getItemCount() {
        return msgsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_msg ,txt_new;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_msg=itemView.findViewById(R.id.txt_msg);
            txt_new=itemView.findViewById(R.id.newMsg);
        }
    }
}
