package com.example.chent.mvpdemo.demo.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chent.mvpdemo.R;
import com.example.chent.mvpdemo.demo.base.BaseRefreshAdapter;
import com.example.chent.mvpdemo.demo.bean.MoviesBean;

import java.util.List;

/**
 * Created by chent on 2017/9/25.
 */

public class RefreshAdapter extends BaseRefreshAdapter<MoviesBean.SubjectsBean, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param context
     * @param data    A new list is created out of this one to avoid mutable list
     */
    public RefreshAdapter(Context context, List<MoviesBean.SubjectsBean> data) {
        super(context, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, MoviesBean.SubjectsBean subjectsBean) {
        String title = subjectsBean.getTitle() + "/" + subjectsBean.getOriginal_title();
        holder.setText(R.id.tv_movie_title, title);//设置电影名
        String doc = "";
        for (MoviesBean.SubjectsBean.DirectorsBean directorsBean : subjectsBean.getDirectors()) {
            doc += directorsBean.getName() + "  ";
        }
        holder.setText(R.id.tv_movie_doc, "导演:" + doc);
        String casts = "";
        for (MoviesBean.SubjectsBean.CastsBean castsBean : subjectsBean.getCasts()) {
            casts += castsBean.getName() + "  ";
        }

        holder.setText(R.id.tv_movie_art, "主演:" + casts);
        String genres = "";
        for (String genre : subjectsBean.getGenres()) {
            genres += genre + " ";
        }
        holder.setText(R.id.tv_movie_type, subjectsBean.getYear() + " / " + genres);//年份+分级
        holder.setText(R.id.tv_movie_grade, subjectsBean.getRating().getAverage() + "");//评分
        ImageView iv_pic = holder.getView(R.id.iv_movie_pic);
        Glide.with(getContext())
                .load(subjectsBean.getImages().getSmall())
                .diskCacheStrategy(DiskCacheStrategy.ALL)//加快显示速度---缓存在本地磁盘
                .into(iv_pic);//图片
    }
}
