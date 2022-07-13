package com.example.baseproject.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.baseproject.model.AppInfor;
import com.example.baseproject.viewModel.AppInfoViewModel;
import com.example.baseproject.R;
import com.example.baseproject.databinding.ListAppItemBinding;

import java.util.List;

public class ListAppAdapter extends RecyclerView.Adapter<ListAppAdapter.ListAppViewHolder> {
    List<AppInfor> mListAppInfor;

    public ListAppAdapter(List<AppInfor> mListAppInfor) {

        this.mListAppInfor = mListAppInfor;
    }

    @NonNull
    @Override
    public ListAppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListAppItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_app_item,
                parent, false);
        return new ListAppViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAppViewHolder holder, int position) {
        AppInfor appInfor = mListAppInfor.get(position);
        holder.bind(appInfor);
    }

    @Override
    public int getItemCount() {
        if (mListAppInfor != null) {
            return mListAppInfor.size();
        }
        return 0;
    }


    public class ListAppViewHolder extends RecyclerView.ViewHolder {
        private final ListAppItemBinding mBinding;

        public ListAppViewHolder(ListAppItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(new AppInfoViewModel(mBinding.getRoot().getContext()));
        }

        public void bind(AppInfor appInfor) {
            mBinding.getViewModel().setAppIcon(appInfor);// viewmodel
            mBinding.getViewModel().setAppName(appInfor);
            mBinding.getViewModel().setAppDuration(appInfor);
            mBinding.executePendingBindings();
        }
    }
}

