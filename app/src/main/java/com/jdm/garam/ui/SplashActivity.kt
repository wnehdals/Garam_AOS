package com.jdm.garam.ui

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingActivity
import com.jdm.garam.data.response.version.Version
import com.jdm.garam.databinding.ActivitySplashBinding
import com.jdm.garam.state.BaseState
import com.jdm.garam.ui.main.MainActivity
import com.jdm.garam.util.DialogUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : ViewBindingActivity<ActivitySplashBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_splash
    private val viewModel: SplashViewModel by viewModel()
    override fun subscribe() {
        viewModel.versionState.observe(this, {
            when (it) {
                is BaseState.Success<*> -> {
                    var localVersion = getPackageVersion()
                    var remoteVersion = (it.SuccessResp as Version)
                    if (remoteVersion.version == localVersion && remoteVersion.force) {
                        goToMainActivity()
                    } else if (remoteVersion.version == localVersion && !remoteVersion.force) {
                        goToMainActivity()
                    }
                    else if(remoteVersion.version != localVersion && remoteVersion.force){
                        showForceFailDialog()
                    } else {
                        showFailDialog()
                    }
                }
                is BaseState.Fail<*> -> {
                    showForceFailDialog()
                }
            }
        })
    }
    private fun getPackageVersion(): Float {
        var localVersion = -1f
        var info: PackageInfo? = null
        try {
            info = packageManager.getPackageInfo(packageName, 0)
            localVersion = info.versionName.toFloat()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return localVersion
    }
    private fun goToMainActivity() {
        var intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun showForceFailDialog() {
        DialogUtil.makeSimpleDialog(
            context = this@SplashActivity,
            title = getString(R.string.notice_update),
            message = getString(R.string.notice_message),
            positiveButtonText = "확인",
            positiveButtonOnClickListener = object :
                DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog?.dismiss()
                    finish()
                }
            },
            cancelable = false
        )
            .show()
    }
    private fun showFailDialog() {
        DialogUtil.makeSimpleDialog(
            context = this@SplashActivity,
            title = getString(R.string.notice_update),
            message = getString(R.string.notice_message),
            positiveButtonText = "확인",
            positiveButtonOnClickListener = object :
                DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog?.dismiss()
                    goToMainActivity()
                }
            },
            cancelable = false
        )
            .show()
    }

    override fun initView() {
        viewModel.getVersion()
        /*
        lifecycleScope.launch {
            delay(3000)
            var remoteVersion = ""
            var localVersion = ""
            var info: PackageInfo? = null
            try {
                info = packageManager.getPackageInfo(packageName,0)
                localVersion = info.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            var remoteConfig = Firebase.remoteConfig
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 3600 * 6
            }
            remoteConfig.setConfigSettingsAsync(configSettings)
            remoteConfig.setDefaultsAsync(R.xml.remote_config_default)

            remoteConfig.fetchAndActivate()
                .addOnCompleteListener(this@SplashActivity) { task ->
                    if(task.isSuccessful) {

                        remoteVersion = parseJson(remoteConfig.getString("appversion"))

                        if(remoteVersion == localVersion) {
                            var intent = Intent(this@SplashActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            DialogUtil.makeSimpleDialog(context = this@SplashActivity,
                            title = getString(R.string.notice_update),
                                message = getString(R.string.notice_message),
                                positiveButtonText = "확인",
                                positiveButtonOnClickListener = object : DialogInterface.OnClickListener{
                                    override fun onClick(dialog: DialogInterface?, which: Int) {
                                        dialog?.dismiss()
                                        finish()
                                    }
                                },
                                cancelable = false
                            )
                                .show()
                        }
                    }
                }

        }

         */
    }

    private fun parseJson(json: String): String {
        try {
            var jsonObject = JSONObject(json)
            return jsonObject.getString("appversion")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }


}