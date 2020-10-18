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