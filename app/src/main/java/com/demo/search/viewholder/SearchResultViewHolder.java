package com.demo.search.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.demo.androidbootstrap.BR;
import com.demo.domain.search.model.SearchResult;

import butterknife.ButterKnife;

/**
 * Created by sahil on 10/19/17.
 */

public class SearchResultViewHolder extends RecyclerView.ViewHolder{

    private ViewDataBinding mBinding;

    public SearchResultViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        ButterKnife.bind(this, itemView);
        mBinding = binding;
        mBinding.setVariable(BR.searchResultHolder, this);
    }

    public SearchResultViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onClick(View view, SearchResult searchResult) {
        Toast.makeText(view.getContext(), searchResult.getRestaurant().get().getName(), Toast.LENGTH_SHORT).show();
    }

    public ViewDataBinding getBinding() {
        return mBinding;
    }
}
