package com.example.clockappliction.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clockappliction.DataBase.CRUD;
import com.example.clockappliction.Information.Clock;
import com.example.clockappliction.Information.Student;
import com.example.clockappliction.R;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.InnerHolder> {


    private final List<Clock> mData;

    public ListViewAdapter(List<Clock> data){this.mData = data;}

    //创建View
    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =View.inflate(parent.getContext(),R.layout.item_list_view,null);


        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {

        holder.setData(mData.get(position));

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }


    public class InnerHolder extends RecyclerView.ViewHolder{

        private ImageView mIv_168;
        private TextView mDate,mWord,mSum,mKeep;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            //找到控件
            mIv_168 = itemView.findViewById(R.id.iv_168);

            mDate = itemView.findViewById(R.id.list_tv_date);
            mWord = itemView.findViewById(R.id.list_tv_word);
            mSum  = itemView.findViewById(R.id.list_tv_summary);
            mKeep = itemView.findViewById(R.id.list_tv_keep);
        }

        public void setData(Clock clock) {

            mDate.setText(clock.date);
            mWord.setText(clock.word);
            mSum.setText(clock.summary);
            mKeep.setText(clock.keep);
        }
    }

}
