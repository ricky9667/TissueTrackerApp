package com.example.android.viewModel;

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
import com.example.android.model.Toilet;
import com.example.android.model.ToiletState;
import com.example.android.service.Store;
import com.example.android.utils.BasicAsyncTask;

import java.util.ArrayList;

public class ToiletListAdapter extends RecyclerView.Adapter<ToiletListAdapter.ToiletViewHolder> {
    private final ArrayList<Toilet> _toiletList = new ArrayList<>();
    private final LayoutInflater _inflater;
    private final ArrayList<String> _selectedToiletList = new ArrayList<>();
    private boolean _isEnabled = false;

    class ToiletViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private final Store _store = Store.getInstance();
        private final TextView _toiletIdTextView;
        private final TextView _toiletLocationTextView;
        private final TextView _toiletPercentageTextView;
        private final TextView _toiletStateTextView;
        private final ImageView _toiletStateImageView;
        private final ImageView _toiletListItemDeleteCheckBox;
        private final View _toiletListItemView;
        private ToiletsViewModel _viewModel;
        final ToiletListAdapter _adapter;

        public ToiletViewHolder(View itemView, ToiletListAdapter adapter) {
            super(itemView);
            _toiletStateTextView = itemView.findViewById(R.id.toiletStateTextView);
            _toiletIdTextView = itemView.findViewById(R.id.toiletIdTextView);
            _toiletLocationTextView = itemView.findViewById(R.id.toiletLocationTextView);
            _toiletPercentageTextView = itemView.findViewById(R.id.toiletPercentageTextView);
            _toiletStateImageView = itemView.findViewById(R.id.toiletStateImageView);
            _toiletListItemDeleteCheckBox = itemView.findViewById(R.id.toiletDeleteCheckCircle);
            _toiletListItemView = itemView;

            this._adapter = adapter;
            _viewModel = new ToiletsViewModel(adapter);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            if (_isEnabled) {
                if (_toiletListItemDeleteCheckBox.getVisibility() == View.GONE) {
                    _toiletListItemDeleteCheckBox.setVisibility(View.VISIBLE);
                    _toiletListItemView.setBackgroundColor(Color.LTGRAY);
                    _selectedToiletList.add(String.valueOf(mPosition));
                } else {
                    _toiletListItemDeleteCheckBox.setVisibility(View.GONE);
                    _toiletListItemView.setBackgroundColor(Color.TRANSPARENT);
                    _selectedToiletList.remove(String.valueOf(mPosition));
                }
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
                        _toiletListItemDeleteCheckBox.setVisibility(View.VISIBLE);
                        _toiletListItemView.setBackgroundColor(Color.LTGRAY);
                        _selectedToiletList.add(String.valueOf(mPosition));
                        return true;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                        int id = item.getItemId();
                        int restroomIndex = _store.getShowingRestroomIndex();
                        String restroomId = _store.getRestroom(restroomIndex).getId();
                        if (id == R.id.delete) {
                            for (int i = getItemCount() - 1; i >= 0; i--) {
                                if (_selectedToiletList.contains(String.valueOf(i))) {
                                    int toiletIndex = i;
                                    String toiletId = _toiletList.get(toiletIndex).getId();
                                    _store.deleteToilet(restroomIndex, toiletIndex);
                                    final Runnable backgroundTask = () -> _viewModel.removeToilet(toiletId, restroomId);
                                    new BasicAsyncTask(backgroundTask, _adapter::notifyDataSetChanged).execute();
                                }
                            }
                            mode.finish();
                            final Runnable backgroundTask = () -> _viewModel.loadToiletsData(restroomId);
                            new BasicAsyncTask(backgroundTask, _adapter::notifyDataSetChanged).execute();
                        }
                        notifyDataSetChanged();
                        return true;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {
                        _isEnabled = false;
                        _selectedToiletList.clear();
                        notifyDataSetChanged();
                    }
                };
                ((AppCompatActivity) view.getContext()).startActionMode(callback);
            } else {
                if (_toiletListItemDeleteCheckBox.getVisibility() == View.GONE) {
                    _toiletListItemDeleteCheckBox.setVisibility(View.VISIBLE);
                    _toiletListItemView.setBackgroundColor(Color.LTGRAY);
                    _selectedToiletList.add(String.valueOf(mPosition));
                } else {
                    _toiletListItemDeleteCheckBox.setVisibility(View.GONE);
                    _toiletListItemView.setBackgroundColor(Color.TRANSPARENT);
                    _selectedToiletList.remove(String.valueOf(mPosition));
                }
            }
            return true;
        }
    }

    public ToiletListAdapter(Context context) {
        _inflater = LayoutInflater.from(context);
    }

    @Override
    public ToiletViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = _inflater.inflate(R.layout.toilet_list_item, parent, false);
        return new ToiletViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ToiletViewHolder holder, int position) {
        Toilet toilet = _toiletList.get(position);
        holder._toiletIdTextView.setText(toilet.getId());
        holder._toiletStateTextView.setText(toilet.getState().toString());
        holder._toiletLocationTextView.setText(toilet.getLocation());

        holder._toiletStateImageView.setImageLevel(getImageLevelFromToiletState(toilet.getState()));

        String percentageText = "--";
        if (toilet.getState() == ToiletState.SUFFICIENT || toilet.getState() == ToiletState.INSUFFICIENT) {
            int percentage = (int) Math.round(toilet.getPercentage() * 100);
            percentageText = percentage + "%";
        }
        holder._toiletPercentageTextView.setText(percentageText);

        if (!_isEnabled) {
            holder._toiletListItemDeleteCheckBox.setVisibility(View.GONE);
            holder._toiletListItemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return _toiletList.size();
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

    public ArrayList<Toilet> getToiletList() {
        return _toiletList;
    }
}
