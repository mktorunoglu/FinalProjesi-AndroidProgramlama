package com.example.ezberdefteri;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{
    ArrayList<ListItems> mDataList;
    LayoutInflater inflater;

    public Adapter(Context context, ArrayList<ListItems> data){
        inflater = LayoutInflater.from(context);
        this.mDataList = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.list_item, parent,false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListItems clicked = mDataList.get(position);
        holder.setData(clicked, position);
    }

    public void deleteItem(int position){
        mDataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mDataList.size());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        Button mKelime;
        TextView mAnlam;
        ImageButton mDelete;
        int clickedPositionValue=0;

        public MyViewHolder(View itemView) {
            super(itemView);
            mKelime= (Button) itemView.findViewById(R.id.btn_item);
            mAnlam= (TextView) itemView.findViewById(R.id.tv_item);
            mDelete= (ImageButton) itemView.findViewById(R.id.imbtn_item);

            mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(clickedPositionValue);
                    MainActivity.delete(mKelime.getText().toString());
                }
            });

            mKelime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAnlam.setVisibility(View.VISIBLE);
                }
            });
        }

        public void setData(ListItems clicked, int position) {
            this.mKelime.setText(clicked.getKelime());
            this.mAnlam.setText(clicked.getAnlam());
            this.clickedPositionValue = position;
        }
    }
}
