package com.ucb.framework.mappers

import com.ucb.domain.Book
import com.ucb.framework.dto.BookDto
import com.ucb.framework.persistence.BookEntity

fun BookDto.toModel(): Book {
    return Book(
        title = this.title,
        authors = this.authors,
        publishYear = this.publishYear
    )
}

fun Book.toEntity(): BookEntity = BookEntity(title, authors.joinToString(","), publishYear)
fun BookEntity.toModel(): Book = Book(title, authors.split(","), publishYear)
