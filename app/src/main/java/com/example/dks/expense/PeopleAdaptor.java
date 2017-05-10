package com.example.dks.expense;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dks on 10/5/17.
 */

public class PeopleAdaptor extends RecyclerView.Adapter<PeopleAdaptor.PeopleViewHolder> {

    private List<PeopleModel> peopleList;

    public class PeopleViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName;
        public TextView tvTotal;
        public PeopleViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvTotal = (TextView) itemView.findViewById(R.id.amount);
        }
    }
    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout,parent,false);
        return new PeopleViewHolder(v);
    }

    public PeopleAdaptor(List<PeopleModel> peopleList) {
        this.peopleList = peopleList;
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        PeopleModel peopleModel = peopleList.get(position);
        holder.tvName.setText(peopleModel.name);
        holder.tvTotal.setText(String.valueOf(peopleModel.total));
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

}
