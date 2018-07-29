package com.demo.base.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DataBindingUtil {

    @BindingAdapter({"image_url", "place_holder"})
    public static void setImageUrl(ImageView imageView, String url, Drawable placeHolder){
        if(TextUtils.isEmpty(url)){
            imageView.setImageDrawable(placeHolder);
        }else{
            Picasso.with(imageView.getContext())
                    .load(url).into(imageView);
        }
    }
}
