package edu.uwaterloo.cs.todo_webservice.Data

data class AccessTokenRequest (
    val client_id : String,
    val client_secret : String,
    val code: String,
    val grant_type : String = "authorization_code",
    val redirect_uri : String
    )
