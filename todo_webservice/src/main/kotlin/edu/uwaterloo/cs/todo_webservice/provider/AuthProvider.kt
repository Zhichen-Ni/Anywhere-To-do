package edu.uwaterloo.cs.todo_webservice.provider

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject

import edu.uwaterloo.cs.todo_webservice.Data.AccessTokenRequest
import edu.uwaterloo.cs.todo_webservice.Data.AccessTokenResult
import edu.uwaterloo.cs.todo_webservice.Data.UserInfo
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class AuthProvider {
    private val READ_TIMEOUT : Long = 100
    private val CONNECT_TIMEOUT : Long= 60
    private val WRITE_TIMEOUT : Long = 60
    private val GOOGLE_TOKEN_URL = "https://www.googleapis.com/oauth2/v4/token"

    private val GOOGLE_USER_INFO_URL = "https://www.googleapis.com/oauth2/v3/userinfo"

    private val clientBuilder : OkHttpClient.Builder = OkHttpClient.Builder()

    private fun setUpClient() : OkHttpClient{
        //Read timeout
        clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        return clientBuilder.build()
    }


    fun getAccessToken(accessTokenRequest: AccessTokenRequest) : String? {
        val mediaType : MediaType? = "application/x-www-form-urlencoded".toMediaTypeOrNull()
        val REQUEST_BODY = "code=${accessTokenRequest.code}&client_id=248432300243-aldppbvkn85sbcurnfgl7ardbfon0ueu.apps.googleusercontent.com&client_secret=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&redirect_uri=http://localhost:8888/oauth2Callback&grant_type=authorization_code"

        val body : RequestBody = RequestBody.Companion.create(mediaType,REQUEST_BODY)

        val request : Request = Request.Builder()
            .url(GOOGLE_TOKEN_URL)
            .method("POST",body)
            .build()

        setUpClient()
        //val clientBuilder : OkHttpClient.Builder = OkHttpClient.Builder()

        val client = setUpClient()

        try {
            val response : Response = client.newCall(request).execute()

            val responseObject = JSON.parseObject(response.body?.string(), AccessTokenResult::class.javaObjectType)

            println("Access Token is ${responseObject.access_token}")

            return responseObject.access_token
        } catch (e : Exception) {
            e.printStackTrace()
        }
        return null
    }


    fun getUserInfo(token : String) : String? {
        val mediaType : MediaType? = "application/x-www-form-urlencoded".toMediaTypeOrNull()
        println(token)

        val request : Request = Request.Builder()
            .url(GOOGLE_USER_INFO_URL)
            .header("Authorization", "Bearer $token")
            .build()

        val client = OkHttpClient()

        try {
            val response : Response = client.newCall(request).execute()

            //println(response.body?.string())
            val responseObject = JSON.parseObject(response.body?.string(), UserInfo::class.javaObjectType)
            return responseObject.name

        } catch (e : Exception) {
            e.printStackTrace()
        }
        return null
    }
}