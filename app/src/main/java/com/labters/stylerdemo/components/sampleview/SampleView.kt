/*
 * *
 *  * Created by Ali YUCE on 10/18/20 9:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/10/20 3:15 PM
 *  * GitHub: https://github.com/mayuce
 *
 */

package com.labters.stylerdemo.components.sampleview

import android.content.Context
import android.util.AttributeSet
import com.labters.stylerdemo.BaseView
import com.labters.stylerdemo.R
import com.labters.stylerdemo.databinding.ViewSampleBinding

class SampleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseView<ViewSampleBinding>(context, attrs, defStyleAttr) {
    override fun initView() {
        binding.run {

        }
    }

    override fun layoutId() = R.layout.view_sample

    override fun resolveAttributes(attrs: AttributeSet?) {

    }
}