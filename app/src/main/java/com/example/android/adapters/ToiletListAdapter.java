package com.example.android.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.classes.Toilet;
import com.example.android.classes.ToiletState;
import com.example.android.store.Store;

import java.util.ArrayList;

public class ToiletListAdapter extends RecyclerView.Adapter<ToiletListAdapter.ToiletViewHolder> {
    private final ArrayList<Toilet> mToiletList;
    private final LayoutInflater mInflater;
    private final ArrayList<String> selectedToiletList = new ArrayList<>();
    private boolean isEnabled = false;

    class ToiletViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private final Store store = Store.getInstance();
        private final TextView toiletIdTextView;
        private final TextView toiletLocationTextView;
        private final TextView toiletPercentageTextView;
        private final TextView toiletStateTextView;
        private final ImageView toiletStateImageView;
        private final ImageView toiletListItemDeleteCheckBox;
        private final View toiletListItemView;
        final ToiletListAdapter mAdapter;

        public ToiletViewHolder(View itemView, ToiletListAdapter adapter) {
            super(itemView);
            toiletStateTextView = itemView.findViewById(R.id.toiletStateTextView);
            toiletIdTextView = itemView.findViewById(R.id.toiletIdTextView);
            toiletLocationTextView = itemView.findViewById(R.id.toiletLocationTextView);
            toiletPercentageTextView = itemView.findViewById(R.id.toiletPercentageTextView);
            toiletStateImageView = itemView.findViewById(R.id.toiletStateImageView);
            toiletListItemDeleteCheckBox = itemView.findViewById(R.id.toiletDeleteCheckCircle);
            toiletListItemView = itemView;

            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            if (isEnabled) {
                if (toiletListItemDeleteCheckBox.getVisibility() == View.GONE) {
                    toiletListItemDeleteCheckBox.setVisibility(View.VISIBLE);
                    toiletListItemView.setBackgroundColor(Color.LTGRAY);
                    selectedToiletList.add(String.valueOf(mPosition));
                } else {
                    toiletListItemDeleteCheckBox.setVisibility(View.GONE);
                    toiletListItemView.setBackgroundColor(Color.TRANSPARENT);
                    selectedToiletList.remove(String.valueOf(mPosition));
                }
            }
        }

        @Override
        public boolean onLongClick(View view) {
            int mPosition = getLayoutPosition();

            if (!isEnabled) {
                ActionMode.Callback callback = new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                        MenuInflater menuInflater = mode.getMenuInflater();
                        menuInflater.inflate(R.menu.menu_delete, menu);
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                        isEnabled = true;
                        toiletListItemDeleteCheckBox.setVisibility(View.VISIBLE);
                        toiletListItemView.setBackgroundColor(Color.LTGRAY);
                        selectedToiletList.add(String.valueOf(mPosition));
                        return true;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                        int id = item.getItemId();
                        int restroomIndex = store.getShowingRestroomIndex();
                        if (id == R.id.delete) {
                            for (int i = getItemCount() - 1; i >= 0; i--) {
                                if (selectedToiletList.contains(String.valueOf(i))) {
                                    store.deleteToilet(restroomIndex, i);
                                }
                            }
                            mode.finish();
                        }
                        return true;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {
                        isEnabled = false;
                        selectedToiletList.clear();
                        notifyDataSetChanged();
                    }
                };
                ((AppCompatActivity) view.getContext()).startActionMode(callback);
            } else {
                if (toiletListItemDeleteCheckBox.getVisibility() == View.GONE) {
                    toiletListItemDeleteCheckBox.setVisibility(View.VISIBLE);
                    toiletListItemView.setBackgroundColor(Color.LTGRAY);
                    selectedToiletList.add(String.valueOf(mPosition));
                } else {
                    toiletListItemDeleteCheckBox.setVisibility(View.GONE);
                    toiletListItemView.setBackgroundColor(Color.TRANSPARENT);
                    selectedToiletList.remove(String.valueOf(mPosition));
                }
            }
            return true;
        }
    }

    public ToiletListAdapter(Context context, ArrayList<Toilet> toiletList) {
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

        holder.toiletStateImageView.setImageLevel(getImageLevelFromToiletState(toilet.getState()));

        String percentageText = "--";
        if (toilet.getState() == ToiletState.SUFFICIENT || toilet.getState() == ToiletState.INSUFFICIENT) {
            int percentage = (int) Math.round(toilet.getPercentage() * 100);
            percentageText = percentage + "%";
        }
        holder.toiletPercentageTextView.setText(percentageText);

        if (!isEnabled) {
            holder.toiletListItemDeleteCheckBox.setVisibility(View.GONE);
            holder.toiletListItemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return mToiletList.size();
    }

    public int getImageLevelFromToiletState(ToiletState state) {
        switch (state) {
            case SUFFICIENT:
                return 0;
            case INSUFFICIENT:
                return 1;
            case DISCONNECTED:
                return 2;
            case CLEANING:
            case MAINTAINING:
                return 3;
            default:
                return -1;
        }
    }
}
