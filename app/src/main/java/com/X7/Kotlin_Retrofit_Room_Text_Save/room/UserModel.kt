package com.X7.Kotlin_Retrofit_Room_Text_Save.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.X7.Kotlin_Retrofit_Room_Text_Save.room.Constants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class UserModel constructor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int,
    @ColumnInfo(name = "userId")
    val userId:Int,
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name = "body")
    val body:String

)