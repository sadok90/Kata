package com.sadok.kata.common

import com.sadok.kata.domain.model.Weather

sealed class Resource<T>(val data: T? = null, val message: String? = null, val iteration: Int = 0) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    class Loading<T>(iteration: Int) : Resource<T>(iteration = iteration)
}