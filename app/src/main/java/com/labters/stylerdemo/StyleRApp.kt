/*
 * *
 *  * Created by Ali YUCE on 10/18/20 9:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/15/20 11:46 PM
 *  * GitHub: https://github.com/mayuce
 *
 */

package com.labters.stylerdemo

import android.app.Application
import com.labters.stylerdemo.styler.AppStyleR

class StyleRApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppStyleR().initialize(resources)
    }
}