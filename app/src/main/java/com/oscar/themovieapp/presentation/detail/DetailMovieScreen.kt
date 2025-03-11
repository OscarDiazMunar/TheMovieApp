package com.oscar.themovieapp.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.oscar.themovieapp.R
import com.oscar.themovieapp.commons.Constants
import com.oscar.themovieapp.domain.entity.detail.MovieDetailEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMovieScreen(
    id: String?,
    viewModel: DetailMovieViewModel,
    navController: NavController
) {
    LaunchedEffect(id) { id?.let {
        viewModel.fetchDetailMovie(it)
    }}
    val moviesDetail: MovieDetailEntity = viewModel.getDetailFlow.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CardDetailMovie(moviesDetail)
        }
    }
}

@Composable
fun CardDetailMovie(detailMovieEntity: MovieDetailEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(10.dp, RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        LazyColumn(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Header(detailMovieEntity) }
            item { InfoSection(title = stringResource(R.string.release_date), content = detailMovieEntity.releaseDate)}
            item { InfoSection(title = stringResource(R.string.overview), content = detailMovieEntity.overview) }
            item { InfoSection(title = stringResource(R.string.genre), content = detailMovieEntity.genres?.joinToString { it.name }
                ?: "") }
        }
    }
}

@Composable
fun Header(detailMovie: MovieDetailEntity) {
    val imageUrl = Constants.urlImage + detailMovie.posterPath
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = detailMovie.title ?: "Unknown",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(12.dp)
                .shadow(8.dp, RoundedCornerShape(16.dp))
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(5.dp)
                    .size(450.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
    }
}

@Composable
fun InfoSection(title: String, content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(top = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Text(
                text = content,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}