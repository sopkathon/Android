package cjdgk7.s.a25thsopkathon

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val SPLASH_TIME_OUT: Long = 3000

        button.setOnClickListener() {
            val pattern = longArrayOf(100, 300, 100, 700, 300, 2000)

            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(
                    VibrationEffect.createWaveform(pattern, -1)
                )
            } else {
                vibrator.vibrate(
                    pattern, -1
                )
            }
            Glide.with(this)
                .load(R.drawable.halloween)
                .into(img)

            Handler().postDelayed({
                startActivity(Intent(this, QusetionActivity::class.java))
                finish()
            }, SPLASH_TIME_OUT)
        }

    }

}
