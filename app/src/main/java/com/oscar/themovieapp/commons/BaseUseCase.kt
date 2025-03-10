package com.oscar.themovieapp.commons

interface BaseUseCase<In, Out>{
    suspend fun execute(input: In): Out
}