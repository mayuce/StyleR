package com.labters.stylerdemo

import android.app.Application
import com.labters.stylerdemo.styler.AppStyleR

class StyleRApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppStyleR().initialize(resources)
    }
}