package com.labters.stylerdemo.styler

import android.graphics.Color
import androidx.cardview.widget.CardView
import com.labters.styler.StyleRProvider

class CardViewBinder {
    companion object {
        const val ATTR_STYLE = "style"
        const val ATTR_CARD_BACKGROUND_COLOR = "cardBackgroundColor"
        const val ATTR_CARD_ELEVATION = "cardElevation"

        fun CardView.setStyleR(map: HashMap<String, String>) {
            StyleRProvider.getColor(map[ATTR_CARD_BACKGROUND_COLOR])?.let {
                setCardBackgroundColor(Color.parseColor(it))
            }
            map[ATTR_CARD_ELEVATION]?.toFloatOrNull()?.let {
                cardElevation = it
            }
        }
    }
}