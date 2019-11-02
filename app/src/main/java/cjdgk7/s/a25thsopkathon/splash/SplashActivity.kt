package cjdgk7.s.a25thsopkathon.splash

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import cjdgk7.s.a25thsopkathon.MainActivity
import cjdgk7.s.a25thsopkathon.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {


    // 스플래쉬를 보여주는 시간
    val SPLASH_TIME_OUT: Long = 8000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen)
        setContentView(R.layout.activity_splash)

        Glide.with(this)
            .load(R.drawable)
            .into(splash_animation)

        // 스플래쉬를 유지하는 핸들러
        Handler().postDelayed({



            // SPLASH_TIME_OUT초 뒤에 하는 동작
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}
