package com.castleglobal_clean.presentation.ui.viewholder;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.androidbootstrap.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sahil on 10/19/17.
 */

public class SearchResultViewHolder extends RecyclerView.ViewHolder{

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

    public SearchResultViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
