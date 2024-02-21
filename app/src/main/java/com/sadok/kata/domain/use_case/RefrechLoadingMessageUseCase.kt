package com.sadok.kata.domain.use_case

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RefreshLoadingMessageUseCase @Inject constructor() {
    operator fun invoke(
        listMessage: List<String>,
        maxTime: Long,
        refreshInterval: Long
    ): Flow<String> = flow {
        var currentTime = 0L
        while (maxTime >= currentTime) {
            listMessage.forEach {
                emit(it)
                currentTime += refreshInterval
                delay(refreshInterval)
            }
        }

    }
}