package com.oscar.themovieapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.themovieapp.domain.entity.detail.MovieDetailEntity
import com.oscar.themovieapp.domain.usecase.DetailMovieUseCase
import com.oscar.themovieapp.domain.usecase.LocalGetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val detailMovieUseCase: DetailMovieUseCase,
    private val localGetMovieUseCase: LocalGetMovieUseCase
): ViewModel() {
    private val _getDetailFlow: MutableStateFlow<MovieDetailEntity> = MutableStateFlow(value = MovieDetailEntity())
    val getDetailFlow: MutableStateFlow<MovieDetailEntity> get() = _getDetailFlow
    fun fetchDetailMovie(id: String){
        if (id.equals("local")){
            loadLocalDetailMovie(id)
        }else {
            loadDetailMovie(id)
        }
    }

    fun loadDetailMovie(id: String){
        viewModelScope.launch {
            detailMovieUseCase.execute(id)
                .collect{
                    _getDetailFlow.value = it
                }
        }
    }

    fun loadLocalDetailMovie(id: String){
        viewModelScope.launch {
            localGetMovieUseCase.execute(id)
                .collect{
                    _getDetailFlow.value = it
                }
        }
    }
}