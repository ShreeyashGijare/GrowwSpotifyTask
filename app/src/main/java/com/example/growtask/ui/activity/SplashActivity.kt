package com.example.growtask.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.growtask.R
import com.example.growtask.auth.AuthManager
import com.example.growtask.databinding.ActivitySplashBinding
import com.spotify.sdk.android.auth.AuthorizationClient

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        mBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val zoomAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.zoom_anim)
        mBinding.splashIcon.startAnimation(zoomAnimation)

        AuthManager.init(applicationContext)
        if (AuthManager.isLoggedIn()) {
            Handler().postDelayed({
                navigateToMainActivity()
            }, 2000)
        } else {
            AuthManager.login(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AuthManager.AUTH_REQUEST_CODE) {
            val response = AuthorizationClient.getResponse(resultCode, data)
            AuthManager.handleAuthResponse(response)
            navigateToMainActivity()
        }
    }


    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}