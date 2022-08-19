package com.example.android.viewModel;

import android.content.Context;
import android.content.Intent;
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
import com.example.android.view.ToiletActivity;
import com.example.android.model.Restroom;
import com.example.android.store.Store;

import java.util.ArrayList;

public class RestroomListAdapter extends RecyclerView.Adapter<RestroomListAdapter.RestroomViewHolder> {
    private final ArrayList<Restroom> mRestroomList;
    private final LayoutInflater mInflater;
    private final ArrayList<String> selectedRestroomList = new ArrayList<>();
    private boolean isEnabled = false;

    class RestroomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private final Store store = Store.getInstance();
        private final TextView restroomListItemTitleView;
        private final TextView restroomListItemContentView;
        private final ImageView restroomListItemDeleteCheckBox;
        private final View restroomListItemView;
        final RestroomListAdapter mAdapter;


        public RestroomViewHolder(View itemView, RestroomListAdapter adapter) {
            super(itemView);
            restroomListItemTitleView = itemView.findViewById(R.id.restroomListItemTitle);
            restroomListItemContentView = itemView.findViewById(R.id.restroomListItemContent);
            restroomListItemDeleteCheckBox = itemView.findViewById(R.id.restroomDeleteCheckCircle);
            restroomListItemView = itemView;
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            if (isEnabled) {
                if (restroomListItemDeleteCheckBox.getVisibility() == View.GONE) {
                    restroomListItemDeleteCheckBox.setVisibility(View.VISIBLE);
                    restroomListItemView.setBackgroundColor(Color.LTGRAY);
                    selectedRestroomList.add(String.valueOf(mPosition));
                } else {
                    restroomListItemDeleteCheckBox.setVisibility(View.GONE);
                    restroomListItemView.setBackgroundColor(Color.TRANSPARENT);
                    selectedRestroomList.remove(String.valueOf(mPosition));
                }
            } else {
                Intent intent = new Intent(view.getContext(), ToiletActivity.class);
                store.setShowingRestroomIndex(mPosition);
                view.getContext().startActivity(intent);
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
                        restroomListItemDeleteCheckBox.setVisibility(View.VISIBLE);
                        restroomListItemView.setBackgroundColor(Color.LTGRAY);
                        selectedRestroomList.add(String.valueOf(mPosition));
                        return true;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.delete) {
                            for (int i = getItemCount() - 1; i >= 0; i--) {
                                if (selectedRestroomList.contains(String.valueOf(i))) {
                                    store.deleteRestroom(i);
                                }
                            }
                            mode.finish();
                        }
                        return true;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {
                        isEnabled = false;
                        selectedRestroomList.clear();
                        notifyDataSetChanged();
                    }
                };
                ((AppCompatActivity) view.getContext()).startActionMode(callback);
            } else {
                if (restroomListItemDeleteCheckBox.getVisibility() == View.GONE) {
                    restroomListItemDeleteCheckBox.setVisibility(View.VISIBLE);
                    restroomListItemView.setBackgroundColor(Color.LTGRAY);
                    selectedRestroomList.add(String.valueOf(mPosition));
                } else {
                    restroomListItemDeleteCheckBox.setVisibility(View.GONE);
                    restroomListItemView.setBackgroundColor(Color.TRANSPARENT);
                    selectedRestroomList.remove(String.valueOf(mPosition));
                }
            }
            return true;
        }
    }

    public RestroomListAdapter(Context context, ArrayList<Restroom> restroomList) {
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

        if (!isEnabled) {
            holder.restroomListItemDeleteCheckBox.setVisibility(View.GONE);
            holder.restroomListItemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return mRestroomList.size();
    }
}