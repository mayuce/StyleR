/*
 * *
 *  * Created by Ali YUCE on 10/18/20 9:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/15/20 10:30 PM
 *  * GitHub: https://github.com/mayuce
 *
 */

package com.labters.stylerdemo.styler

import android.content.Context
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.labters.styler.StyleRProvider
import com.labters.styler.extension.getRawTextFile
import com.labters.stylerdemo.R
import com.labters.stylerdemo.styler.CardViewBinder.Companion.setStyleR

class AppStyleR {

    fun initialize(context: Context) {
        val type = object : TypeToken<HashMap<String, List<HashMap<String, String>>>>() {}.type
        val typeStyle = object : TypeToken<HashMap<String, HashMap<String, String>>>() {}.type
        val colorPicker: (colorKey: String?) -> Int? = { colorKey ->
            val colors = R.color::class.java
            colors.fields.find {
                it.name == colorKey
            }?.let { field ->
                (field.get(null) as? Int)?.let {
                    ContextCompat.getColor(context, it)
                }
            }
        }
        StyleRProvider.initialize(
            Gson().fromJson(context.resources.getRawTextFile(R.raw.styler), type),
            colorPicker,
            Gson().fromJson(context.resources.getRawTextFile(R.raw.styles), typeStyle)
        ) { view: View, hashMap: HashMap<String, String> ->
            (view as? CardView)?.setStyleR(hashMap)
        }
    }
}