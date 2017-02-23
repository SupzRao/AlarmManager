package com.alarmmanager.Alarm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alarmmanager.R;
import com.alarmmanager.model.entity.Alarm;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Suprada on 22-Feb-17.
 */

public class NonRepeatAdapter extends RecyclerView.Adapter<NonRepeatAdapter.NonRepeatListViewHolder> {


    private final List<Alarm> list_alarm;

    public NonRepeatAdapter(List<Alarm> list_alarm) {
        this.list_alarm = list_alarm;
    }

    @Override
    public void onBindViewHolder(NonRepeatAdapter.NonRepeatListViewHolder holder, int position) {
        holder.tv_display_time.setText(list_alarm.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list_alarm.size();
    }

    @Override

    public NonRepeatListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.non_repeat_recycle_view_item, viewGroup, false);
        return new NonRepeatListViewHolder(itemView);
    }

    public static class NonRepeatListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_display_time)
        TextView tv_display_time;
        Context context;

        public NonRepeatListViewHolder(View v) {
            super(v);
            context = itemView.getContext();
            ButterKnife.bind(this, v);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


        }
    }

}
