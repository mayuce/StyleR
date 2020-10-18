/*
 * *
 *  * Created by Ali YUCE on 10/18/20 9:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/15/20 10:05 PM
 *  * GitHub: https://github.com/mayuce
 *
 */

package com.labters.stylerdemo.components.simplebutton

import android.content.Context
import android.util.AttributeSet
import androidx.databinding.BindingAdapter
import com.labters.stylerdemo.BaseView
import com.labters.stylerdemo.R
import com.labters.stylerdemo.databinding.ViewSimpleButtonBinding

@BindingAdapter("app:buttonText")
fun SimpleButtonView.setButtonText(text: String?) {
    binding.btnSimple.text = text
}

class SimpleButtonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseView<ViewSimpleButtonBinding>(context, attrs, defStyleAttr) {
    override fun initView() {
        binding.run {
            btnSimple.setOnClickListener {

            }
        }
    }

    override fun layoutId() = R.layout.view_simple_button

    override fun resolveAttributes(attrs: AttributeSet?) = Unit
}