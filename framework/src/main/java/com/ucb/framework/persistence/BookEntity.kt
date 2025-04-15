package com.ucb.framework.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_books")
data class BookEntity(
    val title: String,
    val authors: String,
    val publishYear: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
