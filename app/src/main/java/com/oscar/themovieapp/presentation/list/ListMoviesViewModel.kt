package com.oscar.themovieapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.oscar.themovieapp.domain.entity.MoviesEntity
import com.oscar.themovieapp.domain.usecase.GetListMoviesUsecCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMoviesViewModel @Inject constructor(
    private val getListMoviesUseCase: GetListMoviesUsecCase
): ViewModel() {
    private val searchQuery = MutableStateFlow("")

    private val _getListFlow: MutableStateFlow<PagingData<MoviesEntity>> = MutableStateFlow(value = PagingData.empty())
    val getListFlow: MutableStateFlow<PagingData<MoviesEntity>> get() = _getListFlow

    init {
        getListMovies()
    }

    private fun getListMovies() {
        viewModelScope.launch {
            searchQuery.
            flatMapLatest { query ->
                getListMoviesUseCase.execute(Unit)
                    .distinctUntilChanged()
                    .map { pagingData ->
                        pagingData.filter { it.title.contains(query, ignoreCase = true) }
                    }
                    .cachedIn(viewModelScope)
            }
            .collect{
                _getListFlow.value = it
            }
        }
    }

    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }
}