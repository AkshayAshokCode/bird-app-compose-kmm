package com.akshayashokcode.bird_app_compose_kmm

import com.akshayashokcode.bird_app_compose_kmm.model.BirdImage
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BirdUiState(
    val birds: List<BirdImage> = emptyList(),
    val selectedCategory: String? = null
){
    val categories = birds.map { it.category }.toSet()
    val selectedImages = birds.filter { it.category == selectedCategory }
}

class BirdsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(BirdUiState())
    val uiState = _uiState.asStateFlow()

    private val httpClient = HttpClient {
        install(ContentNegotiation){
            json()
        }
    }

    init {
        updateImages()
        selectCategory("PIGEON")
    }

    override fun onCleared() {
        httpClient.close()
    }

    fun selectCategory(category: String?) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    // Not making this private because it can be used by composable on refresh functionality
    fun updateImages() {
        viewModelScope.launch {
            val images = getImages()
            _uiState.update { it.copy(birds = images) }
        }
    }

    private suspend fun getImages(): List<BirdImage> {
        val images = httpClient.get("https://sebi.io/demo-image-api/pictures.json")
            .body<List<BirdImage>>()
        return images
    }
}