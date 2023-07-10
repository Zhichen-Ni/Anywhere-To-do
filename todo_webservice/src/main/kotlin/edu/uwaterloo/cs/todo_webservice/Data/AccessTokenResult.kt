package edu.uwaterloo.cs.todo_webservice.Data

data class AccessTokenResult (
    val access_token : String,
    val expires_in : Int,
    val refresh_token: String,
    val scope : String,
    val token_type : String
    )
