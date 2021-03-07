package com.example.myret.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myret.Modal.MsgTypes;
import com.example.myret.Modal.Msgs;
import com.example.myret.R;

import java.util.ArrayList;
import java.util.List;

public class MsgsAdapter extends ListAdapter<Msgs,MsgsAdapter.ViewHolder> {
    Context con;


    public MsgsAdapter(Context context) {
        super(diffCallback);

        this.con = context;
    }

    public static DiffUtil.ItemCallback<Msgs> diffCallback  = new DiffUtil.ItemCallback<Msgs>() {
        @Override
        public boolean areItemsTheSame(@NonNull Msgs oldItem, @NonNull Msgs newItem) {
            return oldItem.getMsgID() == newItem.getMsgID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Msgs oldItem, @NonNull Msgs newItem) {
            return oldItem.getMsgDescription().contentEquals(newItem.getMsgDescription()) &&
                    oldItem.getTypeDescription().contentEquals(newItem.getTypeDescription()) &&
                    oldItem.getNewMsg().contentEquals(newItem.getNewMsg());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_msgs,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Msgs msgs = getItem(position);

        holder.txt_msg.setText(msgs.getMsgDescription());
//        holder.txt_new.setText(msgs.getNewMsg());

    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_msg ,txt_new;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_msg=itemView.findViewById(R.id.txt_msg);
            txt_new=itemView.findViewById(R.id.newMsg);
        }
    }

    public void updateList (List<Msgs> newmsgsList){

    }
}
