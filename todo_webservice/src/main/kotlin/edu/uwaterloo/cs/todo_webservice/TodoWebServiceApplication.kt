package edu.uwaterloo.cs.todo_webservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoWebServiceApplication

fun main(args: Array<String>) {
    runApplication<TodoWebServiceApplication>(*args)
}
