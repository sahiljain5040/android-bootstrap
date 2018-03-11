package com.project.castleglobal.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.demo.androidbootstrap.R;
import com.project.castleglobal.CastleGlobalApplication;
import com.project.castleglobal.contracts.SearchMVP;
import com.project.castleglobal.entities.SearchResult;
import com.project.castleglobal.view.adapter.SearchResultsAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements SearchMVP.View{

    @BindView(R.id.search_results)
    RecyclerView mSearchResultsRecyclerView;
    @BindView(R.id.seach_edit_text)
    EditText mSearchEditText;
    @BindView(R.id.search_btn)
    Button mSearchBtn;
    @BindView(R.id.loading_container)
    LinearLayout mLoadingContainer;
    @BindView(R.id.no_results_container)
    LinearLayout mNoResultsContainer;

    @Inject
    SearchMVP.Presenter mSearchActivityPresenter;
    @Inject
    SearchResultsAdapter mSearchResultsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        CastleGlobalApplication.getAppComponent(this).inject(this);
        init();

        mSearchActivityPresenter.setView(this);
        mSearchActivityPresenter.load("");
    }

    private void init(){

        mSearchEditText.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchResults();
                return true;
            }
            return false;
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mSearchResultsRecyclerView.setLayoutManager(layoutManager);
        mSearchResultsRecyclerView.setAdapter(mSearchResultsAdapter);
    }

    @OnClick(R.id.search_btn)
    public void searchResults(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mSearchBtn.getWindowToken(), 0);
        if(TextUtils.isEmpty(mSearchEditText.getText().toString())){
            Toast.makeText(this, "Please enter some text", Toast.LENGTH_SHORT).show();
        }else{
            mSearchActivityPresenter.load(mSearchEditText.getText().toString());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onSearchResultsLoading() {
        mLoadingContainer.setVisibility(View.VISIBLE);
        mNoResultsContainer.setVisibility(View.GONE);
    }

    @Override
    public void onSearchResultsLoaded(ArrayList<SearchResult> searchResults) {
        mLoadingContainer.setVisibility(View.GONE);
        if(searchResults == null || searchResults.isEmpty()){
            mNoResultsContainer.setVisibility(View.VISIBLE);
        }else{
            mNoResultsContainer.setVisibility(View.GONE);
        }
        mSearchResultsAdapter.setSearchResultsList(searchResults);
    }

    @Override
    public void onSearchResultsFailed() {
        mLoadingContainer.setVisibility(View.GONE);
        if(mSearchResultsAdapter.getSearchResultsList() == null || mSearchResultsAdapter.getSearchResultsList().isEmpty()){
            mNoResultsContainer.setVisibility(View.VISIBLE);
        }else{
            mNoResultsContainer.setVisibility(View.GONE);
        }
        Toast.makeText(this, "Sorry, Unable to get results", Toast.LENGTH_SHORT).show();
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
        mSearchActivityPresenter = null;
        mSearchResultsAdapter = null;
    }
}
