package com.demo.chat.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.androidbootstrap.R;

/**
 * Created by sahil on 8/22/17.
 */

public class InterventionFragment extends Fragment{

    public static final String TAG = InterventionFragment.class.getSimpleName();

    public static InterventionFragment newInstance() {
        InterventionFragment fragment = new InterventionFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intervention, container, false);
        return view;
    }
}
