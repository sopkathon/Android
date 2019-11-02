package cjdgk7.s.a25thsopkathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // 진동
        // 1. 진동 권한을 획득해야한다. AndroidManifest.xml
        // 2. Vibrator 객체를 얻어서 진동시킨다
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        button1.setText("1초진동")
        button1.setOnClickListener() {
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        1000,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                vibrator.vibrate(1000)
            }
            /*button2.setText("지정한 패턴으로 진동")
        button1.setOnClickListener(){
            vibrator.vibrate(1000)
        }*/
            button2.setText("지정한 패턴으로 진동")
            button2.setOnClickListener() {

                val pattern = longArrayOf(100, 300, 100, 700, 300, 2000) // miliSecond
                //           대기,진동,대기,진동,....
                // 짝수 인덱스 : 대기시간
                // 홀수 인덱스 : 진동시간
                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createWaveform(pattern, -1))
                } else {
                    vibrator.vibrate(
                        pattern, // 진동 패턴을 배열로
                        -1
                    )     // 반복 인덱스
                    // 0 : 무한반복, -1: 반복없음,
                    // 양의정수 : 진동패턴배열의 해당 인덱스부터 진동 무한반복
                }

            }

            /*button3.setText("무한반복으로 진동")
        button3.setOnClickListener() {
                vibrator.vibrate(
                    longArrayOf(100, 1000, 100, 500, 100, 500, 100, 1000), 0
                )
        }*/

            /* button4.setText("진동 취소")
        button4.setOnClickListener() {

                vibrator.cancel() // 진동취소

        }
*/

        }
    }
}