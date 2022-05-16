package com.example.android;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ToiletListAdapter extends RecyclerView.Adapter<ToiletListAdapter.ToiletViewHolder>  {
    private final LinkedList<String> mToiletList;
    private final LinkedList<String> mToiletStateList;
    private final LinkedList<String> mToiletIdList;
    private final LinkedList<String> mToiletLocationList;
    private LayoutInflater mInflater;

    class ToiletViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView toiletListItemTitleTextView;
        public final TextView toiletStateTextView;
        public final TextView toiletIdTextView;
        public final TextView toiletLocationTextView;
        final ToiletListAdapter mAdapter;

        public ToiletViewHolder(View itemView, ToiletListAdapter adapter) {
            super(itemView);
            toiletListItemTitleTextView = itemView.findViewById(R.id.toiletListItemTitle);
            toiletStateTextView = itemView.findViewById(R.id.toiletStateTextView);
            toiletIdTextView = itemView.findViewById(R.id.toiletIdTextView);
            toiletLocationTextView = itemView.findViewById(R.id.toiletLocationTextView);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            // Get the position of the item that was clicked.
//            int mPosition = getLayoutPosition();
//
//            Intent intent = new Intent(view.getContext(), ToiletActivity.class);
//            String message = String.valueOf(mPosition);
//            intent.putExtra("com.example.android.extra.MESSAGE", message);
//            view.getContext().startActivity(intent);
        }
    }

    public ToiletListAdapter(Context context, LinkedList<String> toiletList, LinkedList<String> toiletStateList, LinkedList<String> toiletIdList, LinkedList<String> toiletLocationList) {
        mInflater = LayoutInflater.from(context);
        mToiletList = toiletList;
        mToiletStateList = toiletStateList;
        mToiletIdList = toiletIdList;
        mToiletLocationList = toiletLocationList;
    }

    @Override
    public ToiletViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.toilet_list_item, parent, false);
        return new ToiletViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ToiletViewHolder holder, int position) {
        holder.toiletListItemTitleTextView.setText(mToiletList.get(position));
        holder.toiletStateTextView.setText(mToiletStateList.get(position));
        holder.toiletIdTextView.setText(mToiletIdList.get(position));
        holder.toiletLocationTextView.setText(mToiletLocationList.get(position));
    }

    @Override
    public int getItemCount() {
        return mToiletList.size();
    }
}