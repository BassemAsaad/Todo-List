package com.example.session9_todo.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.session9_todo.database.model.Todo
import java.util.Date

@Dao
interface TodoDao {

    @Insert
    fun addTodo(todo : Todo)

    @Delete
    fun deleteTodo(todo : Todo)

    @Update
    fun updateTodo(todo : Todo)

    @Query("select * from Todo")
    fun getAllTodo():List<Todo>
}