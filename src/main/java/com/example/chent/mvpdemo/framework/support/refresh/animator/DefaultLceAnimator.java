package com.example.chent.mvpdemo.framework.support.refresh.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import com.example.chent.mvpdemo.R;

/**
 * Created by chent on 2017/9/25.
 */

public class DefaultLceAnimator implements LCEAnimator {
    @Override
    public void showLoadingView(View loadingView, View contentView, View errorView) {
        contentView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContentView(final View loadingView, final View contentView, View errorView) {
        if (contentView.getVisibility() == View.VISIBLE){
            errorView.setVisibility(View.INVISIBLE);
            loadingView.setVisibility(View.INVISIBLE);
        }else {
            errorView.setVisibility(View.INVISIBLE);

            final Resources resources = loadingView.getResources();
            final int translateInPixels = resources
                    .getDimensionPixelSize(R.dimen.lce_content_view_animation_translate_y);
            // Not visible yet, so animate the view in
            AnimatorSet set = new AnimatorSet();
            ObjectAnimator contentFadeIn = ObjectAnimator.ofFloat(contentView,
                    "alpha", 0f, 1f);
            ObjectAnimator contentTranslateIn = ObjectAnimator.ofFloat(
                    contentView, "translationY", translateInPixels, 0);

            ObjectAnimator loadingFadeOut = ObjectAnimator.ofFloat(loadingView,
                    "alpha", 1f, 0f);
            ObjectAnimator loadingTranslateOut = ObjectAnimator.ofFloat(
                    loadingView, "translationY", 0, -translateInPixels);

            set.playTogether(contentFadeIn, contentTranslateIn, loadingFadeOut,
                    loadingTranslateOut);
            set.setDuration(resources
                    .getInteger(R.integer.lce_content_view_show_animation_time));

            set.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationStart(Animator animation) {
                    contentView.setTranslationY(0);
                    loadingView.setTranslationY(0);
                    contentView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    loadingView.setVisibility(View.GONE);
                    loadingView.setAlpha(1f); // For future showLoading calls
                    contentView.setTranslationY(0);
                    loadingView.setTranslationY(0);
                }
            });

            set.start();

        }
    }

    @Override
    public void showErrorView(final View loadingView, View contentView, final View errorView, String errorMsg) {
        contentView.setVisibility(View.INVISIBLE);

        final Resources resources = loadingView.getResources();
        // Not visible yet, so animate the view in
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator in = ObjectAnimator.ofFloat(errorView, "alpha", 1f);
        ObjectAnimator loadingOut = ObjectAnimator.ofFloat(loadingView,
                "alpha", 0f);

        set.playTogether(in, loadingOut);
        set.setDuration(resources
                .getInteger(R.integer.lce_error_view_show_animation_time));

        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                errorView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                loadingView.setVisibility(View.GONE);
                loadingView.setAlpha(1f); // For future showLoading calls
            }
        });

        set.start();

        ((TextView) errorView.findViewById(R.id.errorMessage)).setText(errorMsg + "，点击页面重新加载");
    }
}
