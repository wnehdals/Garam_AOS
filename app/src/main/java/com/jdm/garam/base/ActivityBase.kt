package com.jdm.garam.base

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import com.jdm.garam.IProgressDialog
import com.jdm.garam.ProgressDialog
import com.jdm.garam.R
import com.jdm.garam.util.DialogUtil
import com.jdm.garam.view.BaseAppBar
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.IllegalStateException

open class ActivityBase : AppCompatActivity(), IProgressDialog {
    private val compositeDisposable = CompositeDisposable()
    private var progressDialog: ProgressDialog? = null
    private var baseAppBar: BaseAppBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun setBaseAppBar(title: String = "") {
        if(supportActionBar == null)
            throw IllegalStateException("Can not found supportActionBar")

        baseAppBar = BaseAppBar(this, supportActionBar)
        baseAppBar?.setUpActionBar()
        setAppBarTitle(title)
    }
    fun setAppBarTitle(title: String) {
        baseAppBar?.setUpActionBar()
        if(!title.isNullOrEmpty()) {
            baseAppBar?.setTitle(title)
        }
    }
    fun setAppBarColor(color: String) {
        baseAppBar?.setBackgroundColor(color)
    }
    fun appBarLeftButtonClicked(callback: (View) -> Unit) {
        baseAppBar?.leftButtonClickListener = callback
    }
    fun appBarRightButtonClicked(callback: (View) -> Unit) {
        baseAppBar?.rightButtonClickListener = callback
    }
    fun setBackKey() {
        baseAppBar?.setLeftButtonDrawable(R.drawable.ic_chevron_left)
        appBarLeftButtonClicked {
            onBackPressed()
        }
    }

    fun addDisposable(vararg disposables: Disposable) {
        compositeDisposable.addAll(*disposables)
    }

    fun showFailToastMessage(message: String = getString(R.string.fail_load_data)) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }



    override fun onDestroy() {
        super.onDestroy()
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }

    override fun showProgressDialog() {
        progressDialog?.dismiss()
        progressDialog = ProgressDialog(this, getString(R.string.loading))
        progressDialog?.show()
    }

    override fun hideProgressDialog() {
        progressDialog?.dismiss()
        progressDialog = null
    }
}

@ColorInt
fun Context.getColorFromAttr(
        @AttrRes attrColor: Int,
        typedValue: TypedValue = TypedValue(),
        resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}

fun Context.showSimpleDialog(
        title: String? = null,
        message: String,
        positiveButtonText: String = "OK",
        negativeButtonText: String? = null,
        positiveButtonOnClickListener: DialogInterface.OnClickListener,
        negativeButtonOnClickListener: DialogInterface.OnClickListener? = null,
        cancelable: Boolean = true
) = DialogUtil.makeSimpleDialog(
        this,
        title,
        message,
        positiveButtonText,
        negativeButtonText,
        positiveButtonOnClickListener,
        negativeButtonOnClickListener,
        cancelable
).show()