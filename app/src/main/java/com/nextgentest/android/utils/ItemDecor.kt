package com.nextgentest.android.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nextgentest.android.R

class ItemDecor(val context: Context) : RecyclerView.ItemDecoration(){

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = context.resources.getDimensionPixelSize(R.dimen.dp16)
        outRect.left = context.resources.getDimensionPixelSize(R.dimen.dp16)
        outRect.right = context.resources.getDimensionPixelSize(R.dimen.dp16)
    }

}