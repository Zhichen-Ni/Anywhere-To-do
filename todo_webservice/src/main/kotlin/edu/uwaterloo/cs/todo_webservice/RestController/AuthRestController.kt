package edu.uwaterloo.cs.todo_webservice.RestController

import edu.uwaterloo.cs.todo_webservice.Constant.constant
import edu.uwaterloo.cs.todo_webservice.Data.UserInfo
import edu.uwaterloo.cs.todo_webservice.provider.AuthProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/api/v1/auth")
class AuthRestController {
    @Autowired
    private lateinit var autoProvider: AuthProvider

    @GetMapping("/userinfo")
    fun getUserInfo() : UserInfo = constant.mUserInfo

    @GetMapping("/token")
    fun getAccessToken() : String? = constant.token
}