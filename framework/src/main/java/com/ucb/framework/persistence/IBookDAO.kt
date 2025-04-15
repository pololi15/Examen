package com.ucb.framework.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IBookDAO {
    @Insert
    suspend fun insert(book: BookEntity)

    @Query("SELECT * FROM favorite_books")
    suspend fun getAll(): List<BookEntity>
}
