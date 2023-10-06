package com.dial.infocommand.view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.WindowManager
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.dial.infocommand.R
import com.dial.infocommand.api.ApiInterface
import com.dial.infocommand.api.ApiUtilities
import com.dial.infocommand.repository.InfoUrlRepository
import com.dial.infocommand.viewmodel.InfoUrlViewModel
import com.dial.infocommand.viewmodel.InfoUrlViewModelFactory
import com.onesignal.OneSignal
import java.util.Locale


@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    private lateinit var infoUrlViewModel: InfoUrlViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var splashConstrain = findViewById<ConstraintLayout>(R.id.splash_constraint)

        Glide.with(this@SplashActivity)
            .load(resources.getString(R.string.text_url_background))
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: com.bumptech.glide.request.transition.Transition<in Drawable?>?
                ) {
                    splashConstrain.background = resource
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
            })

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler(Looper.getMainLooper()).postDelayed({

            val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)

            val infoIPRepository = InfoUrlRepository(apiInterface)

            infoUrlViewModel = ViewModelProvider(this, InfoUrlViewModelFactory(infoIPRepository,
                getSystemDetail(), Locale.getDefault().language, Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID))).get(InfoUrlViewModel::class.java)

            infoUrlViewModel.info.observe(this) {

                when(it.url) {
                    "no" -> {
                         startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                         finish()
                    }
                    "nopush"->{
                        OneSignal.disablePush(true)
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()


                    } else -> {
                        val intent = Intent(this@SplashActivity, WebViewActivity::class.java)
                        intent.putExtra("LINK", it.url)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }, 3000)
    }

    @SuppressLint("HardwareIds")
    private fun getSystemDetail(): String {
        return Build.BRAND + " " + Build.MODEL
    }

}