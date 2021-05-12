package com.jdm.garam.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.jdm.garam.R
import com.jdm.garam.databinding.BaseAppbarBinding

class BaseAppBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    lateinit var binding: BaseAppbarBinding
    var titleText = ""
        set(value) {
            binding.appbarTitle.text = value
            field = value
        }

    init {
        initView()
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.BaseAppBar,
            defStyleAttr, defStyleRes
        ).apply {
            titleText = getString(R.styleable.BaseAppBar_titleText) ?: ""
            recycle()
        }
    }

    private fun initView() {
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.base_appbar, this, true)
    }

    private fun setTitle(s: String) {
        binding.appbarTitle.text = s
    }
}