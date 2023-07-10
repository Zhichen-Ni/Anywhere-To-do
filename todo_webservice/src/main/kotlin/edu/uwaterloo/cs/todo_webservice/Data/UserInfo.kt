package edu.uwaterloo.cs.todo_webservice.Data


data class UserInfo (
    val sub : String? = null,
    var name : String? = null,
    val given_name : String? = null,
    val family_name : String? = null,
    val picture : String? = null,
    val locale : String? = null
)
