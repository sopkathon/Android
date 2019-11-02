package cjdgk7.s.a25thsopkathon

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cjdgk7.s.a25thsopkathon.data.PostQuestionRequest
import cjdgk7.s.a25thsopkathon.data.PostQuestionResponse
import cjdgk7.s.a25thsopkathon.network.ApplicationController
import cjdgk7.s.a25thsopkathon.network.NetworkService
import kotlinx.android.synthetic.main.activity_question.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QusetionActivity : AppCompatActivity() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        setOnBtnClickListener()
    }

    private fun setOnBtnClickListener() {
        btn_ok.setOnClickListener {
            val question = txt_user_question.text.toString()

            if (isValid(question)) {
                postQuestionResponse(question)
            }
        }
    }

    private fun postQuestionResponse(input: String) {
        Log.e("123", "postQuestion 통신")
        val postQuestionResponse: Call<PostQuestionResponse> =
            networkService.postQuestionResponse("application/json", PostQuestionRequest(input))
        postQuestionResponse.enqueue(object : Callback<PostQuestionResponse> {

            override fun onFailure(call: Call<PostQuestionResponse>, t: Throwable) {
                Log.e("Q", "Question failed")
            }

            override fun onResponse(
                call: Call<PostQuestionResponse>,
                response: Response<PostQuestionResponse>
            ) {
                // 성공
                Log.e("1", "1")
                response?.takeIf { it.isSuccessful }
                    ?.body()?.takeIf { it.status == 200 }
                    ?.let {
                        Log.e("1", "2")
                        val intent = Intent(this@QusetionActivity, AnswerActivity::class.java)
                        var question = txt_user_question.text.toString()
                        var answer = response.body()!!.message

                        intent.putExtra("question", question.toString())
                        intent.putExtra("answer", answer)
                        startActivityForResult(intent, 0)
                        finish()
                        Log.e("보내기성공?", question)
                        Log.e("보내기성공?", answer)
                    }

                // 실패
                response?.takeIf { it.isSuccessful }
                    ?.body()?.takeIf { it.status == 500 }
                    ?.let {
                        Log.e("1", "111111")
                    }
            }
        })
    }

    private fun isValid(question: String): Boolean {
        if (question == "") {
            Log.e("fail", "fail")
            return false
        } else {
            return true
            Log.e("success", "success")
        }
    }
}
