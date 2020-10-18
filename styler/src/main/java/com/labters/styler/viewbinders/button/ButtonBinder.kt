/*
 * *
 *  * Created by Ali YUCE on 10/18/20 9:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/15/20 10:30 PM
 *  * GitHub: https://github.com/mayuce
 *
 */

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