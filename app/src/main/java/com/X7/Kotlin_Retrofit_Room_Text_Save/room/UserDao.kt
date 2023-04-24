package com.X7.Kotlin_Retrofit_Room_Text_Save.room

import androidx.room.*
import com.X7.Kotlin_Retrofit_Room_Text_Save.room.Constants.TABLE_NAME

@Dao
interface UserDao {
    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllUsers(): List<UserModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserModel)

    @Update
    fun updateUser(user: UserModel)

    @Delete
    fun deleteUser(user: UserModel)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAllUsers()

    @Query("SELECT * FROM $TABLE_NAME WHERE id=:idd ")
    fun loadbyid(idd: Int): UserModel

}