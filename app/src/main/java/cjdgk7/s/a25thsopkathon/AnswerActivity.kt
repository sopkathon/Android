package cjdgk7.s.a25thsopkathon

import android.content.Context
import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_answer.*
import kotlinx.android.synthetic.main.activity_splash.*

class AnswerActivity : AppCompatActivity() {
    // gif를 보여주는 시간
    val IMAGE_TIME_OUT: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        //화면이 바뀌면 gif시간동안 진동이 울린다
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
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
        //액티비티가 켜질떄는 gif
        Glide.with(this)
            .load(R.drawable.pumpkin)
            .into(answer_card)

        //일정시간 지난후엔
        Handler().postDelayed({
            // Image_TIME_OUT초 뒤에 하는 동작
            answer_card.setBackgroundResource(R.drawable.halloween)
            //질문 text 받아오기
            txt_question.text=""
            //답 받아오기
            txt_answer.text=""

        }, IMAGE_TIME_OUT)

        //TODO btn setOnClickListener 구성하기


    }
}
