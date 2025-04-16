package com.ucb.examen.book

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucb.domain.Book

@Composable
fun FavoritesUI(viewModel: BookViewModel = hiltViewModel(), onBack: () -> Unit) {
    val favorites by viewModel.favorites.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadFavorites()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Volver")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(favorites) { book ->
                FavoriteBookItem(book)
            }
        }
    }
}

@Composable
fun FavoriteBookItem(book: Book) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Título: ${book.title}")
            Text("Autor(es): ${book.authors.joinToString()}")
            Text("Año: ${book.publishYear}")
        }
    }
}
