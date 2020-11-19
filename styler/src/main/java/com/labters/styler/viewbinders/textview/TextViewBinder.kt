/*
 * *
 *  * Created by Ali YUCE on 10/18/20 9:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/15/20 11:16 PM
 *  * GitHub: https://github.com/mayuce
 *
 */

package com.labters.styler.viewbinders.textview

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.widget.TextView
import com.labters.styler.StyleRProvider

@Suppress("MemberVisibilityCanBePrivate")
internal class TextViewBinder {

    companion object {
        const val NO_COLOR = "#00000000"

        const val ATTR_STYLE = "style"
        const val ATTR_TEXT_SIZE = "textSize"
        const val ATTR_TEXT_ALL_CAPS = "textAllCaps"
        const val ATTR_FONT_NAME = "fontName"
        const val ATTR_TEXT_COLOR = "textColor"
        const val ATTR_DISABLED_TEXT_COLOR = "disabledTextColor"
        const val ATTR_PRESSED_TEXT_COLOR = "pressedTextColor"
        private const val FONTS_PATH = "fonts/"

        fun TextView.setStyleR(map: HashMap<String, String>) {
            map[ATTR_STYLE]?.let {
                StyleRProvider.getStyle(it)?.let { style -> setStyleR(style) }
            }
            map[ATTR_TEXT_SIZE]?.toFloatOrNull()?.let { textSize = it }
            map[ATTR_TEXT_ALL_CAPS]?.toBoolean()?.let { isAllCaps = it }
            map[ATTR_FONT_NAME]?.let {
                typeface = Typeface.createFromAsset(context.assets, "$FONTS_PATH$it")
            }

            map[ATTR_TEXT_COLOR]?.let {
                setTextColorStyle(
                    this,
                    it,
                    map[ATTR_DISABLED_TEXT_COLOR],
                    map[ATTR_PRESSED_TEXT_COLOR]
                )
            }
        }

        private fun setTextColorStyle(
            view: TextView,
            color: String,
            disabledColor: String?,
            pressedColor: String?
        ) {
            val colorInt = StyleRProvider.getColor(color)
            val disabled =
                StyleRProvider.getColor(disabledColor ?: color)
            val pressed = StyleRProvider.getColor(pressedColor ?: color)
            val states = arrayOf(
                intArrayOf(-android.R.attr.state_pressed),
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(android.R.attr.state_pressed)
            )
            if (colorInt == null || disabled == null || pressed == null) {
                return
            }
            val colors = intArrayOf(
                colorInt,
                disabled,
                pressed
            )
            view.setTextColor(ColorStateList(states, colors))
        }
    }
}