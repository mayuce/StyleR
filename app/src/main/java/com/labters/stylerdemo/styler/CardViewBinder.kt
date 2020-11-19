/*
 * *
 *  * Created by Ali YUCE on 10/18/20 9:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/15/20 10:30 PM
 *  * GitHub: https://github.com/mayuce
 *
 */

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
                setCardBackgroundColor(it)
            }
            map[ATTR_CARD_ELEVATION]?.toFloatOrNull()?.let {
                cardElevation = it
            }
        }
    }
}