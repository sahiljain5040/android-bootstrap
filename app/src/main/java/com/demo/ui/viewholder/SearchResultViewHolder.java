package com.demo.ui.viewholder;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.SampleApplication;
import com.demo.androidbootstrap.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sahil on 10/19/17.
 */

public class SearchResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @Nullable
    @BindView(R.id.header_text)
    public TextView mHeader;

    @Nullable
    @BindView(R.id.image)
    public ImageView mImage;

    @Nullable
    @BindView(R.id.name)
    public TextView mName;

    @Nullable
    @BindView(R.id.rating)
    public TextView mRating;

    @Nullable
    @BindView(R.id.avg_cost)
    public TextView mAvgCost;

    @Nullable
    @BindView(R.id.cuisines)
    public TextView mCuisines;

    private SearchResult mSearchResult;

    public SearchResultViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(SearchResult searchResult){
        if(searchResult.isRestaurant()){
            itemView.setOnClickListener(this);
            itemView.setContentDescription(searchResult.getRestaurant().getName());
        }else{
            itemView.setOnClickListener(null);
            itemView.setContentDescription(searchResult.getName());
        }
        mSearchResult = searchResult;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(SampleApplication.getContext(), mSearchResult.getRestaurant().getName(), Toast.LENGTH_SHORT).show();
    }
}
