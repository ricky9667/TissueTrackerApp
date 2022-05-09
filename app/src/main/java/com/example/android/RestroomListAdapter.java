package com.example.android;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class RestroomListAdapter extends RecyclerView.Adapter<RestroomListAdapter.RestroomViewHolder>  {
    private final LinkedList<String> mRestroomList;
    private final LinkedList<String> mDescriptionList;
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

            Intent intent = new Intent(view.getContext(), DescriptionActivity.class);
            String message = String.valueOf(mPosition);
            intent.putExtra("com.example.android.extra.MESSAGE", message);
            view.getContext().startActivity(intent);
        }
    }

    public RestroomListAdapter(Context context, LinkedList<String> recipeList, LinkedList<String> descriptionList) {
        mInflater = LayoutInflater.from(context);
        mRestroomList = recipeList;
        mDescriptionList = descriptionList;
    }

    @Override
    public RestroomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.restroom_list_item, parent, false);
        return new RestroomViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(RestroomViewHolder holder, int position) {
        holder.restroomListItemTitleView.setText(mRestroomList.get(position));
        holder.restroomListItemContentView.setText(mDescriptionList.get(position));
    }

    @Override
    public int getItemCount() {
        return mRestroomList.size();
    }
}