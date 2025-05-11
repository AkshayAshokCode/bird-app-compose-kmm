package com.akshayashokcode.bird_app_compose_kmm.model

import kotlinx.serialization.Serializable

@Serializable
data class BirdImage(
    val author: String,
    val category: String,
    val path: String
)