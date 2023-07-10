package edu.uwaterloo.cs.todo_webservice.Controller

import edu.uwaterloo.cs.todo_webservice.Data.Todo
import edu.uwaterloo.cs.todo_webservice.Service.TodoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TodoResource(val service: TodoService) {
    @GetMapping
    fun index(): List<Todo> = service.findTodo()
}