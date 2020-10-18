package com.labters.stylerdemo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.labters.styler.core.StyleR

abstract class BaseView<dataBinding : ViewDataBinding> @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    lateinit var binding: dataBinding

    init {
        initComponent(context, attrs)
    }

    private fun initComponent(context: Context?, attrs: AttributeSet?) {
        if (this.isInEditMode) {
            View.inflate(context, layoutId(), this)
        } else {
            this.binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                this.layoutId(),
                this,
                true
            )
            initView()
            resetStyle()
        }
        resolveAttributes(attrs)
    }

    fun resetStyle() {
        StyleR.applyStyle(binding.root, "${javaClass.simpleName}${AppStatics.appColor.key}")
    }

    abstract fun initView()

    abstract fun layoutId(): Int

    abstract fun resolveAttributes(attrs: AttributeSet?)
}