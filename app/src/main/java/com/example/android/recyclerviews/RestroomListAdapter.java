package com.example.android.recyclerviews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.activites.ToiletActivity;
import com.example.android.models.Restroom;

import java.util.LinkedList;

public class RestroomListAdapter extends RecyclerView.Adapter<RestroomListAdapter.RestroomViewHolder>  {
    private final LinkedList<Restroom> mRestroomList;
    private LayoutInflater mInflater;

    class RestroomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView restroomListItemTitleView;
        public final TextView restroomListItemContentView;
        final RestroomListAdapter mAdapter;

        public RestroomViewHolder(View itemView, RestroomListAdapter adapter) {
            super(itemView);
            restroomListItemTitleView = itemView.findViewById(R.id.restroomListItemTitle);
            restroomListItemContentView = itemView.findViewById(R.id.restroomListItemContent);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();

            Intent intent = new Intent(view.getContext(), ToiletActivity.class);
            String message = String.valueOf(mPosition);
            intent.putExtra("com.example.android.extra.MESSAGE", message);
            view.getContext().startActivity(intent);
        }
    }

    public RestroomListAdapter(Context context, LinkedList<Restroom> restroomList) {
        mInflater = LayoutInflater.from(context);
        mRestroomList = restroomList;
    }

    @Override
    public RestroomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.restroom_list_item, parent, false);
        return new RestroomViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(RestroomViewHolder holder, int position) {
        Restroom restroom = mRestroomList.get(position);
        holder.restroomListItemTitleView.setText(restroom.getLocation());
        holder.restroomListItemContentView.setText(restroom.getId());
    }

    @Override
    public int getItemCount() {
        return mRestroomList.size();
    }
}