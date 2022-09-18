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
import com.example.android.utils.BasicAsyncTask;
import com.example.android.view.ToiletActivity;
import com.example.android.model.Restroom;
import com.example.android.service.Store;

import java.util.ArrayList;

public class RestroomListAdapter extends RecyclerView.Adapter<RestroomListAdapter.RestroomViewHolder> {
    private final LayoutInflater _inflater;
    private final ArrayList<Restroom> _restroomList = new ArrayList<>();
    private final ArrayList<String> _selectedRestroomList = new ArrayList<>();
    private boolean _isEnabled = false;

    class RestroomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private final Store _store = Store.getInstance(); // TODO: remove after all branches have been merge
        private final TextView _restroomListItemTitleView;
        private final TextView _restroomListItemContentView;
        private final ImageView _restroomListItemDeleteCheckBox;
        private final View _restroomListItemView;
        private final RestroomsViewModel _viewModel;
        private final RestroomListAdapter _adapter;

        public RestroomViewHolder(View itemView, RestroomListAdapter adapter) {
            super(itemView);
            _restroomListItemTitleView = itemView.findViewById(R.id.restroomListItemTitle);
            _restroomListItemContentView = itemView.findViewById(R.id.restroomListItemContent);
            _restroomListItemDeleteCheckBox = itemView.findViewById(R.id.restroomDeleteCheckCircle);
            _restroomListItemView = itemView;
            this._adapter = adapter;
            _viewModel = new RestroomsViewModel(adapter);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            if (_isEnabled) {
                if (_restroomListItemDeleteCheckBox.getVisibility() == View.GONE) {
                    _restroomListItemDeleteCheckBox.setVisibility(View.VISIBLE);
                    _restroomListItemView.setBackgroundColor(Color.LTGRAY);
                    _selectedRestroomList.add(String.valueOf(position));
                } else {
                    _restroomListItemDeleteCheckBox.setVisibility(View.GONE);
                    _restroomListItemView.setBackgroundColor(Color.TRANSPARENT);
                    _selectedRestroomList.remove(String.valueOf(position));
                }
            } else {
                Intent intent = new Intent(view.getContext(), ToiletActivity.class);
                intent.putExtra("restroomId", _restroomList.get(position).getId());
                intent.putExtra("restroomLocation", _restroomList.get(position).getLocation());
                view.getContext().startActivity(intent);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            int mPosition = getLayoutPosition();
            if (!_isEnabled) {
                ActionMode.Callback callback = new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                        MenuInflater menuInflater = mode.getMenuInflater();
                        menuInflater.inflate(R.menu.menu_delete, menu);
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                        _isEnabled = true;
                        _restroomListItemDeleteCheckBox.setVisibility(View.VISIBLE);
                        _restroomListItemView.setBackgroundColor(Color.LTGRAY);
                        _selectedRestroomList.add(String.valueOf(mPosition));
                        return true;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.delete) {
                            for (int i = getItemCount() - 1; i >= 0; i--) {
                                if (_selectedRestroomList.contains(String.valueOf(i))) {
                                    int index = i;
                                    String restroomId = _restroomList.get(index).getId();
                                    _store.deleteRestroom(index);
                                    final Runnable backgroundTask = () -> _viewModel.deleteRestroom(restroomId);
                                    new BasicAsyncTask(backgroundTask, _adapter::notifyDataSetChanged).execute();
                                }
                            }
                            mode.finish();
                            final Runnable backgroundTask = () -> _viewModel.loadRestroomsData();
                            new BasicAsyncTask(backgroundTask, _adapter::notifyDataSetChanged).execute();
                        }
                        notifyDataSetChanged();
                        return true;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {
                        _isEnabled = false;
                        _selectedRestroomList.clear();
                        notifyDataSetChanged();
                    }
                };
                ((AppCompatActivity) view.getContext()).startActionMode(callback);
            } else {
                if (_restroomListItemDeleteCheckBox.getVisibility() == View.GONE) {
                    _restroomListItemDeleteCheckBox.setVisibility(View.VISIBLE);
                    _restroomListItemView.setBackgroundColor(Color.LTGRAY);
                    _selectedRestroomList.add(String.valueOf(mPosition));
                } else {
                    _restroomListItemDeleteCheckBox.setVisibility(View.GONE);
                    _restroomListItemView.setBackgroundColor(Color.TRANSPARENT);
                    _selectedRestroomList.remove(String.valueOf(mPosition));
                }
            }
            return true;
        }
    }

    public RestroomListAdapter(Context context) {
        _inflater = LayoutInflater.from(context);
    }

    @Override
    public RestroomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = _inflater.inflate(R.layout.restroom_list_item, parent, false);
        return new RestroomViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(RestroomViewHolder holder, int position) {
        Restroom restroom = _restroomList.get(position);
        holder._restroomListItemTitleView.setText(restroom.getLocation());
        holder._restroomListItemContentView.setText(restroom.getId());

        if (!_isEnabled) {
            holder._restroomListItemDeleteCheckBox.setVisibility(View.GONE);
            holder._restroomListItemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return _restroomList.size();
    }

    public ArrayList<Restroom> getRestroomList() {
        return _restroomList;
    }
}