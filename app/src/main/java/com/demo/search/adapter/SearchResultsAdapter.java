package com.demo.search.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.demo.androidbootstrap.BR;
import com.demo.androidbootstrap.databinding.SearchItemBinding;
import com.demo.androidbootstrap.databinding.SearchResultsHeaderBinding;
import com.demo.domain.search.model.SearchResult;
import com.demo.search.viewholder.SearchResultViewHolder;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by sahil on 10/18/17.
 */

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultViewHolder> {

    private static final int HEADER = 1;
    private static final int SEARCH_RESULT = 2;

    private List<SearchResult> mSearchResultsList;

    @Inject
    public SearchResultsAdapter() {

    }

    @Override
    public int getItemCount() {
        if (mSearchResultsList == null) {
            return 0;
        }
        return mSearchResultsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        SearchResult searchResult = mSearchResultsList.get(position);
        if (searchResult.isRestaurant()) {
            return SEARCH_RESULT;
        }
        return HEADER;
    }

    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewDataBinding binding = null;

        LayoutInflater inflater= (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (viewType) {
            case HEADER:
                binding = SearchResultsHeaderBinding.inflate(inflater, parent, false);
                break;

            case SEARCH_RESULT:
                binding = SearchItemBinding.inflate(inflater, parent, false);
                break;
        }
        return new SearchResultViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        SearchResult searchResult = mSearchResultsList.get(position);
        if (searchResult.isRestaurant()) {
            holder.getBinding().setVariable(BR.searchResult, searchResult);
        } else {
            holder.getBinding().setVariable(BR.header, searchResult);
        }
        holder.getBinding().executePendingBindings();
    }

    public List<SearchResult> getSearchResultsList() {
        return mSearchResultsList;
    }

    public void setSearchResultsList(List<SearchResult> searchResultsList) {
        this.mSearchResultsList = searchResultsList;
        this.notifyDataSetChanged();
    }
}
