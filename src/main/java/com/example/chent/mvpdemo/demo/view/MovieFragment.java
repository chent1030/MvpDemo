package com.example.chent.mvpdemo.demo.view;

import com.example.chent.mvpdemo.demo.bean.MoviesBean;
import com.example.chent.mvpdemo.framework.support.refresh.RefreshView;
import com.example.chent.mvpdemo.framework.support.refresh.fragment.BaseRefreshFragment;

/**
 * Created by chent on 2017/9/25.
 */

public class MovieFragment extends BaseRefreshFragment<MoviesBean, RefreshView<MoviesBean>, MoviePresenter> implements RefreshView<MoviesBean> {
}
