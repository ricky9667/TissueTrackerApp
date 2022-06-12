package com.example.android.recyclerviews;

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
import com.example.android.Store;
import com.example.android.activites.ToiletActivity;
import com.example.android.models.Restroom;

import java.util.ArrayList;

public class RestroomListAdapter extends RecyclerView.Adapter<RestroomListAdapter.RestroomViewHolder>  {
    private final ArrayList<Restroom> mRestroomList;
    private LayoutInflater mInflater;
    boolean isEnable = false;
    ArrayList<String> selectList=new ArrayList<String>();

    class RestroomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public final TextView restroomListItemTitleView;
        public final TextView restroomListItemContentView;
        public ImageView restroomListItemDeleteCheckBox;
        public View restroomListItemView;
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
            if(isEnable)
            {
                if (restroomListItemDeleteCheckBox.getVisibility() == View.GONE) {
                    restroomListItemDeleteCheckBox.setVisibility(View.VISIBLE);
                    restroomListItemView.setBackgroundColor(Color.LTGRAY);
                    selectList.add(String.valueOf(mPosition));
                }
                else {
                    restroomListItemDeleteCheckBox.setVisibility(View.GONE);
                    restroomListItemView.setBackgroundColor(Color.TRANSPARENT);
                    selectList.remove(String.valueOf(mPosition));
                }
            }
            else {
                Intent intent = new Intent(view.getContext(), ToiletActivity.class);
                Store.getInstance().setShowingRestroomIndex(mPosition);
                view.getContext().startActivity(intent);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            int mPosition = getLayoutPosition();

            if (!isEnable)
            {
                ActionMode.Callback callback = new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                        MenuInflater menuInflater= mode.getMenuInflater();
                        menuInflater.inflate(R.menu.menu_delete, menu);
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                        isEnable=true;
                        restroomListItemDeleteCheckBox.setVisibility(View.VISIBLE);
                        restroomListItemView.setBackgroundColor(Color.LTGRAY);
                        selectList.add(String.valueOf(mPosition));
                        return true;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.delete) {
                            for (int i = getItemCount() - 1; i >=0; i --) {
                                if (selectList.contains(String.valueOf(i))) {
                                    Store.getInstance().deleteRestroom(i);
                                }
                            }
                            mode.finish();
                        }
                        return true;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {
                        isEnable=false;
                        selectList.clear();
                        notifyDataSetChanged();
                    }
                };
                ((AppCompatActivity) view.getContext()).startActionMode(callback);
            }
            else
            {
                if (restroomListItemDeleteCheckBox.getVisibility() == View.GONE) {
                    restroomListItemDeleteCheckBox.setVisibility(View.VISIBLE);
                    restroomListItemView.setBackgroundColor(Color.LTGRAY);
                    selectList.add(String.valueOf(mPosition));
                }
                else {
                    restroomListItemDeleteCheckBox.setVisibility(View.GONE);
                    restroomListItemView.setBackgroundColor(Color.TRANSPARENT);
                    selectList.remove(String.valueOf(mPosition));
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

        if(!isEnable) {
            holder.restroomListItemDeleteCheckBox.setVisibility(View.GONE);
            holder.restroomListItemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return mRestroomList.size();
    }
}