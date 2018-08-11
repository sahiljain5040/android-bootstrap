package com.demo.search.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.demo.androidbootstrap.databinding.SearchActivityBinding;
import com.demo.base.model.Resource;
import com.demo.base.util.DataBindingUtil;
import com.demo.domain.search.model.SearchResult;
import com.demo.search.adapter.SearchResultsAdapter;
import com.demo.search.fragment.TestFragment;
import com.demo.search.viewmodel.SearchViewModel;
import com.demo.search.viewmodel.factory.SearchViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class SearchActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @BindView(R.id.search_results)
    RecyclerView mSearchResultsRecyclerView;
    @BindView(R.id.seach_edit_text)
    EditText mSearchEditText;
    @BindView(R.id.search_btn)
    Button mSearchBtn;

    @Inject
    DispatchingAndroidInjector<Fragment> mDispatchingAndroidInjector;
    @Inject
    SearchViewModelFactory mSearchViewModelFactory;
    @Inject
    SearchResultsAdapter mSearchResultsAdapter;

    SearchViewModel mSearchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        SearchActivityBinding activityBinding = android.databinding.DataBindingUtil.setContentView(this, R.layout.search_activity);
        ButterKnife.bind(this);
        init();

        mSearchViewModel = ViewModelProviders.of(this, mSearchViewModelFactory)
                .get(SearchViewModel.class);

        mSearchViewModel.getSearchResults().observe(this, (resource) -> {

            activityBinding.setResource(resource);
            activityBinding.setLoadedResults(mSearchResultsAdapter == null ? null : mSearchResultsAdapter.getSearchResultsList());
            if(resource.status == Resource.Status.SUCCESS){
                mSearchResultsAdapter.setSearchResultsList(resource.data);
            }else if(resource.status == Resource.Status.ERROR){
                Toast.makeText(this, "Sorry, Unable to get results", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {

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

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new TestFragment())
                .commit();
    }

    @OnClick(R.id.search_btn)
    public void searchResults() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mSearchBtn.getWindowToken(), 0);
        if (TextUtils.isEmpty(mSearchEditText.getText().toString())) {
            Toast.makeText(this, "Please enter some text", Toast.LENGTH_SHORT).show();
        } else {
            try {
                mSearchViewModel.load(mSearchEditText.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        mSearchResultsAdapter = null;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mDispatchingAndroidInjector;
    }
}