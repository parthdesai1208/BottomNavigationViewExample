package com.p1208.bottomnavigation_animation_volley;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.view.Gravity;
import android.view.View;



public class BottomNavigationBehavior extends CoordinatorLayout.Behavior<BottomNavigationView>{
    private int height;

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, BottomNavigationView child, View dependency) {
       if(dependency instanceof Snackbar.SnackbarLayout){
            updateSnackbar(child,(Snackbar.SnackbarLayout) dependency);
       }
        return super.layoutDependsOn(parent,child,dependency);
    }
    private void updateSnackbar(View child, Snackbar.SnackbarLayout snackbarLayout){
        if(snackbarLayout.getLayoutParams() instanceof CoordinatorLayout.LayoutParams){
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)snackbarLayout.getLayoutParams();
                params.setAnchorId(child.getId());
                params.anchorGravity = Gravity.TOP;
                params.gravity = Gravity.TOP;
                snackbarLayout.setLayoutParams(params);
        }
    }
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, BottomNavigationView child, int layoutDirection) {
        height = child.getHeight();
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        if (dyConsumed < 0) {
            showBottomNavigationView(child);
        } else if (dyConsumed > 0) {
            hideBottomNavigationView(child);
        }
    }

    private void hideBottomNavigationView(BottomNavigationView view) {
        view.clearAnimation();
        view.animate().translationY(height).setDuration(200);
      //  view.animate().translationY(view.getHeight());
    }

    private void showBottomNavigationView(BottomNavigationView view) {
        view.clearAnimation();
        view.animate().translationY(0).setDuration(200);
       // view.animate().translationY(0);
    }
}
