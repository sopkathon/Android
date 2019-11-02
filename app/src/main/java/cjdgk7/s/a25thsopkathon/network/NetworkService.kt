package cjdgk7.s.a25thsopkathon.network

import cjdgk7.s.a25thsopkathon.data.PostQuestionRequest
import cjdgk7.s.a25thsopkathon.data.PostQuestionResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkService {
    @POST("input")
    fun postQuestionResponse(
        @Header("Content-Type") content_type: String,
        @Body body: PostQuestionRequest
    ): Call<PostQuestionResponse>
}