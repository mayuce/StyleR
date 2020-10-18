/*
 * *
 *  * Created by Ali YUCE on 10/18/20 9:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/13/20 11:15 PM
 *  * GitHub: https://github.com/mayuce
 *  
 */

package com.labters.styler.extension

import android.content.res.Resources
import androidx.annotation.RawRes


class StyleRExtension

fun Resources.getRawTextFile(@RawRes id: Int) =
    openRawResource(id).bufferedReader().use { it.readText() }

val Int.dp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Float.dp: Float get() = (this / Resources.getSystem().displayMetrics.density)

val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.px: Float get() = (this * Resources.getSystem().displayMetrics.density)