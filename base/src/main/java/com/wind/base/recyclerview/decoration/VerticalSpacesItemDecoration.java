package com.wind.base.recyclerview.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class VerticalSpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private boolean mSpaceInBottom;
    public VerticalSpacesItemDecoration(int space) {
        this.space = space;
    }
    public VerticalSpacesItemDecoration(int space,boolean mSpaceInBottom) {
       this(space);
       this.mSpaceInBottom=mSpaceInBottom;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {


        int position = parent.getChildAdapterPosition(view);


        outRect.top = space;
        outRect.left = space;

    }
}