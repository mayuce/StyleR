/*
 * *
 *  * Created by Ali YUCE on 10/18/20 9:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/15/20 11:17 PM
 *  * GitHub: https://github.com/mayuce
 *
 */

package com.labters.styler.viewbinders.view

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.labters.styler.StyleRProvider
import com.labters.styler.extension.px


@Suppress("MemberVisibilityCanBePrivate")
internal class ViewBinder {

    companion object {
        const val ATTR_STYLE = "style"
        const val ATTR_BACKGROUND_COLOR = "backgroundColor"
        const val ATTR_PRESSED_BACKGROUND_COLOR = "pressedBackgroundColor"
        const val ATTR_DISABLED_BACKGROUND_COLOR = "disabledBackgroundColor"
        const val ATTR_BACKGROUND_RADIUS = "backgroundRadius"
        const val ATTR_BACKGROUND_BORDER_SIZE = "backgroundBorderWidth"
        const val ATTR_ELEVATION = "elevation"
        const val ATTR_PADDING = "padding"
        const val ATTR_PADDING_TOP = "paddingTop"
        const val ATTR_PADDING_BOTTOM = "paddingBottom"
        const val ATTR_PADDING_START = "paddingStart"
        const val ATTR_PADDING_END = "paddingEnd"
        const val ATTR_MARGIN = "margin"
        const val ATTR_MARGIN_TOP = "marginTop"
        const val ATTR_MARGIN_BOTTOM = "marginBottom"
        const val ATTR_MARGIN_START = "marginStart"
        const val ATTR_MARGIN_END = "marginEnd"

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
            setViewPadding(
                this,
                map[ATTR_PADDING]?.toFloatOrNull()?.px?.toInt(),
                map[ATTR_PADDING_TOP]?.toFloatOrNull()?.px?.toInt(),
                map[ATTR_PADDING_BOTTOM]?.toFloatOrNull()?.px?.toInt(),
                map[ATTR_PADDING_START]?.toFloatOrNull()?.px?.toInt(),
                map[ATTR_PADDING_END]?.toFloatOrNull()?.px?.toInt()
            )
            setViewMargin(
                this,
                map[ATTR_MARGIN]?.toFloatOrNull()?.px?.toInt(),
                map[ATTR_MARGIN_TOP]?.toFloatOrNull()?.px?.toInt(),
                map[ATTR_MARGIN_BOTTOM]?.toFloatOrNull()?.px?.toInt(),
                map[ATTR_MARGIN_START]?.toFloatOrNull()?.px?.toInt(),
                map[ATTR_MARGIN_END]?.toFloatOrNull()?.px?.toInt()
            )
            map[ATTR_ELEVATION]?.toFloatOrNull()?.let {
                ViewCompat.setElevation(this, it)
            }
        }

        @SuppressLint("NewApi")
        private fun setViewMargin(
            view: View,
            margin: Int?,
            top: Int?,
            bottom: Int?,
            start: Int?,
            end: Int?
        ) {
            (view.layoutParams as? ViewGroup.MarginLayoutParams)
                ?.run {
                    val isValidVersion =
                        android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1
                    setMargins(
                        start ?: margin ?: if (isValidVersion) marginStart else leftMargin,
                        top ?: margin ?: topMargin,
                        end ?: margin ?: if (isValidVersion) marginEnd else rightMargin,
                        bottom ?: margin ?: bottomMargin
                    )
                }
        }

        private fun setViewPadding(
            view: View,
            padding: Int?,
            top: Int?,
            bottom: Int?,
            start: Int?,
            end: Int?
        ) {
            view.setPadding(
                start ?: padding ?: view.paddingLeft,
                top ?: padding ?: view.paddingTop,
                end ?: padding ?: view.paddingRight,
                bottom ?: padding ?: view.paddingBottom
            )
        }

        private fun generateBackground(
            view: View,
            color: String,
            pressedColor: String?,
            disabledColor: String?,
            borderWidth: Int?,
            radius: Float?
        ) {
            view.background = null
            ViewCompat.setBackgroundTintList(view, null)
            val colorInt = Color.parseColor(StyleRProvider.getColor(color))
            val disabled = Color.parseColor(StyleRProvider.getColor(disabledColor ?: color))
            val pressed = Color.parseColor(StyleRProvider.getColor(pressedColor ?: color))
            val r = radius?.px ?: 0f
            val shape = GradientDrawable()
            shape.shape = GradientDrawable.RECTANGLE
            shape.cornerRadius = r
            val states = arrayOf(
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(-android.R.attr.state_pressed),
                intArrayOf(android.R.attr.state_pressed)
            )
            val colors = intArrayOf(
                disabled,
                colorInt,
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