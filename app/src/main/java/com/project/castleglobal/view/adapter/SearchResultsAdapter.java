package com.project.castleglobal.view.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.androidbootstrap.R;
import com.project.castleglobal.CastleGlobalApplication;
import com.project.castleglobal.model.SearchResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sahil on 10/18/17.
 */

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder> {

    private static final int HEADER = 1;
    private static final int SEARCH_RESULT = 2;

    private ArrayList<SearchResult> mSearchResultsList;

    @Inject
    public SearchResultsAdapter() {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @BindView(R.id.header_text)
        TextView mHeader;

        @Nullable
        @BindView(R.id.image)
        ImageView mImage;

        @Nullable
        @BindView(R.id.name)
        TextView mName;

        @Nullable
        @BindView(R.id.rating)
        TextView mRating;

        @Nullable
        @BindView(R.id.avg_cost)
        TextView mAvgCost;

        @Nullable
        @BindView(R.id.cuisines)
        TextView mCuisines;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SearchResult searchResult = mSearchResultsList.get(position);
        if (searchResult.isRestaurant()) {
            if (TextUtils.isEmpty(searchResult.getRestaurant().getThumb())) {
                holder.mImage.setImageDrawable(null);
            } else {
                Picasso.with(CastleGlobalApplication.getContext()).load(searchResult.getRestaurant()
                        .getThumb()).into(holder.mImage);
            }
            holder.mName.setText(searchResult.getRestaurant().getName());
            holder.mAvgCost.setText("Avg cost for two: " + Integer.toString(searchResult.getRestaurant().getAverageCostForTwo()));
            holder.mRating.setText(searchResult.getRestaurant().getUserRating().getAggregateRating());
            holder.mCuisines.setText(searchResult.getRestaurant().getCuisines());
        } else {
            holder.mHeader.setText(searchResult.getName());
        }
    }

    public ArrayList<SearchResult> getSearchResultsList() {
        return mSearchResultsList;
    }

    public void setSearchResultsList(ArrayList<SearchResult> searchResultsList) {
        this.mSearchResultsList = searchResultsList;
        this.notifyDataSetChanged();
    }
}
