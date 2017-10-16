package com.project.castleglobal.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.androidbootstrap.R;
import com.project.castleglobal.CastleGlobalApplication;
import com.project.castleglobal.presenter.SearchActivityPresenter;
import com.project.castleglobal.view.delegate.SearchResultsDelegate;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity implements SearchResultsDelegate{

    @Inject
    SearchActivityPresenter mSearchActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        CastleGlobalApplication.getAppComponent(this).inject(this);

        mSearchActivityPresenter.setView(this);
        mSearchActivityPresenter.load();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchActivityPresenter.onDestroy();
    }
}
