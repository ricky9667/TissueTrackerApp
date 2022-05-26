package com.example.android.recyclerviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.models.Toilet;
import com.example.android.models.ToiletState;

import java.util.LinkedList;

public class ToiletListAdapter extends RecyclerView.Adapter<ToiletListAdapter.ToiletViewHolder> {
    private final LinkedList<Toilet> mToiletList;
    private LayoutInflater mInflater;

    class ToiletViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView toiletStateTextView;
        public final TextView toiletIdTextView;
        public final TextView toiletLocationTextView;
        public final TextView toiletPercentageTextView;
        final ToiletListAdapter mAdapter;

        public ToiletViewHolder(View itemView, ToiletListAdapter adapter) {
            super(itemView);
            toiletStateTextView = itemView.findViewById(R.id.toiletStateTextView);
            toiletIdTextView = itemView.findViewById(R.id.toiletIdTextView);
            toiletLocationTextView = itemView.findViewById(R.id.toiletLocationTextView2);
            toiletPercentageTextView = itemView.findViewById(R.id.toiletPercentageTextView);
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

    public ToiletListAdapter(Context context, LinkedList<Toilet> toiletList) {
        mInflater = LayoutInflater.from(context);
        mToiletList = toiletList;
    }

    @Override
    public ToiletViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.toilet_list_item, parent, false);
        return new ToiletViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ToiletViewHolder holder, int position) {
        Toilet toilet = mToiletList.get(position);
        holder.toiletIdTextView.setText(toilet.getId());
        holder.toiletStateTextView.setText(toilet.getState().toString());
        holder.toiletLocationTextView.setText(toilet.getLocation());

        String percentageText = "--";
        if (toilet.getState() == ToiletState.SUFFICIENT || toilet.getState() == ToiletState.INSUFFICIENT) {
            int percentage = (int) Math.round(toilet.getPercentage() * 100);
            percentageText = percentage + "%";
        }
        holder.toiletPercentageTextView.setText(percentageText);
    }

    @Override
    public int getItemCount() {
        return mToiletList.size();
    }
}