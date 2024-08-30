package com.example.session9_todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.session9_todo.database.dao.TodoDao
import com.example.session9_todo.database.model.DateConverter
import com.example.session9_todo.database.model.Todo

@Database(entities = [Todo::class] , version = 1)
@TypeConverters(DateConverter::class)
abstract class MyDatabase : RoomDatabase(){


    abstract fun todoDau() : TodoDao


    //fun in companion object cause we need it static
    //why static?
    //to access fun with class name without taking class object
    companion object{
        private var myDatabase : MyDatabase?=null
        private const val DATABASE_NAME = "Todo"

        // singleton pattern
        //create object of MyDatabase class
        fun getInstance(context : Context):MyDatabase{
            // if database has no objects
            if (myDatabase == null){
                //create object
                myDatabase = Room.databaseBuilder(context,MyDatabase::class.java, DATABASE_NAME)
                    // delete old data and create the updated data with added data
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    // to create object
                    .build()

            }
            // if not null return MyDatabase
            return myDatabase!!
        }


    }

}