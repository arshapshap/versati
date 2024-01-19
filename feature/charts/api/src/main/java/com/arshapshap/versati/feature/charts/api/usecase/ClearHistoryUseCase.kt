package com.arshapshap.versati.feature.charts.api.usecase

import com.arshapshap.versati.feature.charts.api.repository.ChartsRepository

class ClearHistoryUseCase(
    private val repository: ChartsRepository
) {

    suspend operator fun invoke() {
        return repository.clearHistory()
    }
}