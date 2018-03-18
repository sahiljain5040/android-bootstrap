package com.castleglobal_clean.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.castleglobal_clean.CastleGlobalApplication;
import com.castleglobal_clean.ui.viewholder.SearchResultViewHolder;
import com.demo.androidbootstrap.R;
import com.demo.domain.model.SearchResult;
import com.squareup.picasso.Picasso;

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

        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = null;
        switch (viewType) {
            case HEADER:
                view = layoutInflater.inflate(R.layout.activity_search_results_header, parent, false);
                break;

            case SEARCH_RESULT:
                view = layoutInflater.inflate(R.layout.activity_search_item, parent, false);
                break;
        }
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        SearchResult searchResult = mSearchResultsList.get(position);
        if (searchResult.isRestaurant()) {
            if (TextUtils.isEmpty(searchResult.getRestaurant().getThumb())) {
                holder.mImage.setImageResource(R.drawable.not_available);
            } else {
                Picasso.with(CastleGlobalApplication.getContext()).load(searchResult.getRestaurant()
                        .getThumb()).into(holder.mImage);
            }
            holder.mName.setText(searchResult.getRestaurant().getName());
            holder.mAvgCost.setText("Avg cost for two: " + Integer.toString(searchResult.getRestaurant().getAverageCostForTwo()));
            holder.mRating.setText(searchResult.getRestaurant().getUserRating().getAggregateRating());
            holder.mRating.setBackgroundColor(Color.parseColor("#" + searchResult.getRestaurant().getUserRating().getRatingColor()));
            holder.mCuisines.setText(searchResult.getRestaurant().getCuisines());
        } else {
            holder.mHeader.setText(searchResult.getName());
        }
    }

    public List<SearchResult> getSearchResultsList() {
        return mSearchResultsList;
    }

    public void setSearchResultsList(List<SearchResult> searchResultsList) {
        this.mSearchResultsList = searchResultsList;
        this.notifyDataSetChanged();
    }
}
