package com.oscar.themovieapp.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.oscar.themovieapp.R
import com.oscar.themovieapp.commons.Constants
import com.oscar.themovieapp.domain.entity.MoviesEntity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListMoviesScreen(
    viewModel: ListMoviesViewModel,
    navController: NavHostController,
) {
    val moviesList: LazyPagingItems<MoviesEntity> = viewModel.getListFlow.collectAsLazyPagingItems()
    var searchQuery by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue),
                    actions = {
                        IconButton(onClick = {
                            coroutineScope.launch{
                                navController.popBackStack()
                            }
                        }) {

                        }
                    }
                )
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        viewModel.setSearchQuery(searchQuery)
                                    },
                    placeholder = { Text(stringResource(R.string.search_movie)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    singleLine = true
                )
            }
        },
                floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                containerColor = Color.Yellow
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Add Movie")
            }
        }
    ) { innerPadding ->
        AllMovies(listMoviesModel = moviesList, innerPadding) { itemId ->

        }
    }
}

@Composable
fun AllMovies(
    listMoviesModel: LazyPagingItems<MoviesEntity>,
    paddingA: PaddingValues,
    onRowClick: (String) -> Unit
) {
    GridMovie(movies = listMoviesModel, paddingA = paddingA, onRowClick)
}

@Composable
fun GridMovie(
    movies: LazyPagingItems<MoviesEntity>,
    paddingA: PaddingValues,
    onRowClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        modifier = Modifier.padding(paddingA))
    {
        items(movies.itemCount) { index ->
            val movie = movies[index] ?: return@items
            MovieImage(modifier = Modifier, movie){
                onRowClick(it)
            }
        }
    }
}

@Composable
fun MovieImage(
    modifier: Modifier,
    movie: MoviesEntity,
    onRowClick: (String) -> Unit
) {
    val imageurl = Constants.urlImage + movie.posterPath
    Column(
        modifier = Modifier
            .padding(10.dp)
            .clickable { onRowClick(movie.id) }
    ) {
        Card {
            AsyncImage(
                model = imageurl,
                contentDescription = "",
                modifier = Modifier.padding(5.dp)
            )
        }
        Box {
            Text(text = movie.title)
        }
    }
}