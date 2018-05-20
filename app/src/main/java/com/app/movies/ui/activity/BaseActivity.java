package com.app.movies.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.app.movies.R;
import com.app.movies.ui.view.BaseView;

import butterknife.BindView;
import dagger.android.AndroidInjection;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @BindView(R.id.loading)
    ProgressBar loading;

    abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showProgress() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loading.setVisibility(View.GONE);
    }
}
