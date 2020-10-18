/*
 * *
 *  * Created by Ali YUCE on 10/18/20 9:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/15/20 11:50 PM
 *  * GitHub: https://github.com/mayuce
 *
 */

package com.labters.stylerdemo

class AppStatics {
    companion object {
        var appColor = AppTheme.LIGHT
    }

    enum class AppTheme(val key: String) {
        LIGHT(""),
        DARK(".Dark"),
        BLACKOUT(".Blackout"),
    }

}