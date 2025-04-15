package com.ucb.framework.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookResponseDto(
    @SerialName("docs") val docs: List<BookDto>
)

@Serializable
data class BookDto(
    @SerialName("title") val title: String,
    @SerialName("author_name") val authors: List<String> = emptyList(),
    @SerialName("first_publish_year") val publishYear: Int = 0
)