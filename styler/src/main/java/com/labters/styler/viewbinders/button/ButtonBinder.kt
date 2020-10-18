package com.labters.styler.viewbinders.button

import android.widget.Button
import com.labters.styler.StyleRProvider

@Suppress("MemberVisibilityCanBePrivate")
class ButtonBinder {

    companion object {
        const val ATTR_STYLE = "style"

        fun Button.setStyleR(map: HashMap<String, String>) {
            map[ATTR_STYLE]?.let {
                StyleRProvider.getStyle(it)?.let { style -> setStyleR(style) }
            }
        }
    }
}