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