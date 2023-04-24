package com.X7.Kotlin_Retrofit_Room_Text_Save.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserModel::class], version = 1)
abstract class Appdatabase: RoomDatabase() {
    abstract fun userdao(): UserDao
}