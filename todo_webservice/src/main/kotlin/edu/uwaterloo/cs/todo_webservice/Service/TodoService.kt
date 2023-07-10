package edu.uwaterloo.cs.todo_webservice.Service

import edu.uwaterloo.cs.todo_webservice.Data.Todo
import org.springframework.stereotype.Service

@Service
class TodoService {

    fun findTodo() : List<Todo> = listOf(
        Todo("Play Game"),
        Todo("Write Code"),
        Todo("Homework"),
    )

}