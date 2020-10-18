/*
 * *
 *  * Created by Ali YUCE on 10/18/20 9:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/15/20 11:17 PM
 *  * GitHub: https://github.com/mayuce
 *
 */

package com.labters.styler.viewbinders.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.view.ViewCompat
import com.labters.styler.StyleRProvider
import com.labters.styler.extension.px


@Suppress("MemberVisibilityCanBePrivate")
class ViewBinder {

    companion object {
        const val ATTR_STYLE = "style"
        const val ATTR_BACKGROUND_COLOR = "backgroundColor"
        const val ATTR_PRESSED_BACKGROUND_COLOR = "pressedBackgroundColor"
        const val ATTR_DISABLED_BACKGROUND_COLOR = "disabledBackgroundColor"
        const val ATTR_BACKGROUND_RADIUS = "backgroundRadius"
        const val ATTR_BACKGROUND_BORDER_SIZE = "backgroundBorderWidth"
        const val ATTR_ELEVATION = "elevation"

        fun View.setStyleR(map: HashMap<String, String>) {
            map[ATTR_STYLE]?.let {
                StyleRProvider.getStyle(it)?.let { style -> setStyleR(style) }
            }
            map[ATTR_BACKGROUND_COLOR]?.let { bgColor ->
                generateBackground(
                    this,
                    bgColor,
                    map[ATTR_PRESSED_BACKGROUND_COLOR],
                    map[ATTR_DISABLED_BACKGROUND_COLOR],
                    map[ATTR_BACKGROUND_BORDER_SIZE]?.toIntOrNull(),
                    map[ATTR_BACKGROUND_RADIUS]?.toFloatOrNull()
                )
            }
            map[ATTR_ELEVATION]?.toFloatOrNull()?.let {
                ViewCompat.setElevation(this, it)
            }
        }

        private fun generateBackground(
            view: View,
            color: String,
            pressedColor: String?,
            disabledColor: String?,
            borderWidth: Int?,
            radius: Float?
        ) {
            val colorInt = Color.parseColor(StyleRProvider.getColor(color))
            val disabled = Color.parseColor(StyleRProvider.getColor(disabledColor ?: color))
            val pressed = Color.parseColor(StyleRProvider.getColor(pressedColor ?: color))
            val r = radius?.px ?: 0f
            val shape = GradientDrawable()
            shape.shape = GradientDrawable.RECTANGLE
            shape.cornerRadius = r
            val states = arrayOf(
                intArrayOf(-android.R.attr.state_pressed),
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(android.R.attr.state_pressed)
            )
            val colors = intArrayOf(
                colorInt,
                disabled,
                pressed
            )
            borderWidth?.let {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    shape.setStroke(it.px, ColorStateList(states, colors))
                } else {
                    shape.setStroke(it.px, colorInt)
                }
            } ?: kotlin.run {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    shape.color = ColorStateList(states, colors)
                } else {
                    shape.setColor(colorInt)
                }
                ViewCompat.setBackgroundTintList(view, ColorStateList(states, colors))
            }
            view.background = shape
        }
    }
}