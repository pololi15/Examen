package com.ucb.usecases

import com.ucb.data.BookRepository

class GetFavoriteBooks(private val repository: BookRepository) {
    suspend fun invoke() = repository.getFavorites()
}