/*
 * *
 *  * Created by Ali YUCE on 10/18/20 9:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/16/20 12:00 AM
 *  * GitHub: https://github.com/mayuce
 *
 */

package com.labters.stylerdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.labters.styler.core.StyleR
import com.labters.stylerdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        StyleR.applyStyle(binding.root, "AppTheme${AppStatics.appColor.key}")

        binding.btnLight.binding.btnSimple.setOnClickListener {
            changeAppTheme(AppStatics.AppTheme.LIGHT)
        }
        binding.btnDark.binding.btnSimple.setOnClickListener {
            changeAppTheme(AppStatics.AppTheme.DARK)
        }
        binding.btnBlackout.binding.btnSimple.setOnClickListener {
            changeAppTheme(AppStatics.AppTheme.BLACKOUT)
        }
    }

    private fun changeAppTheme(theme: AppStatics.AppTheme) {
        AppStatics.appColor = theme
        StyleR.applyStyle(binding.root, "AppTheme${AppStatics.appColor.key}")
        binding.btnBlackout.resetStyle()
        binding.btnLight.resetStyle()
        binding.btnDark.resetStyle()
        binding.sample.resetStyle()
    }
}
