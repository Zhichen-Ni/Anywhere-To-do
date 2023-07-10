package edu.uwaterloo.cs.todo_webservice.WebController

import edu.uwaterloo.cs.todo_webservice.Constant.constant
import edu.uwaterloo.cs.todo_webservice.Data.AccessTokenRequest
import edu.uwaterloo.cs.todo_webservice.Data.UserInfo
import edu.uwaterloo.cs.todo_webservice.provider.AuthProvider
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@CrossOrigin(origins = ["http://localhost:3000"])
@Controller
class AuthWebController {
    val client_id = "248432300243-aldppbvkn85sbcurnfgl7ardbfon0ueu.apps.googleusercontent.com"
    val client_secret = "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"
    val grant_type : String = "authorization_code"
    val redirect_uri = "http://localhost:3000"

    @GetMapping("/oauth2Callback")
    fun callback(@RequestParam(name="code") code : String,
                 @RequestParam(name="error") error : String?) : String{
        if (error != null) {
            println("Error $error when trying to log in using Google account")
        }
        println(code)

        val accessTokenRequest : AccessTokenRequest = AccessTokenRequest(client_id, client_secret,code,grant_type,redirect_uri)

        val token = AuthProvider().getAccessToken(accessTokenRequest)
        if (token != null) {
            constant.mUserInfo.name = AuthProvider().getUserInfo(token)
            constant.token = token
            println("constant.mUserInfo.name ${constant.mUserInfo}")
        }

        return "redirect:http://localhost:3000"
    }

}