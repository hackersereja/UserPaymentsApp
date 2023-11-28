package ru.sergean.userpaymentsapp.presentation.payments.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.sergean.userpaymentsapp.utils.dpToPx

class PaymentItemDecoration(
    private val innerMargin: Int,
    private val horizontalMargin: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val context = view.context

        val itemPosition: Int = parent.getChildAdapterPosition(view)

        if (itemPosition == 0) {
            outRect.top = context.dpToPx(dp = innerMargin)
        }

        outRect.bottom = context.dpToPx(dp = innerMargin)
        outRect.left = context.dpToPx(dp = horizontalMargin)
        outRect.right = context.dpToPx(dp = horizontalMargin)
    }
}
