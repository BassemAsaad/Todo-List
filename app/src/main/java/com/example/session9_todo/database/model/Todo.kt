package com.example.session9_todo.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

//change name of table (tableName = "List")
@Entity
data class Todo   (
    //class represent entity
    //attribute represent column
    //object represent raw

    // generate primary key
    @PrimaryKey(autoGenerate = true)
    val id :Int?=null,
    @ColumnInfo
    val name: String?=null,
    @ColumnInfo
    val details : String?=null,
    @ColumnInfo
    val date :Date?=null,
    @ColumnInfo
    val asDone :Boolean?=false,
    )