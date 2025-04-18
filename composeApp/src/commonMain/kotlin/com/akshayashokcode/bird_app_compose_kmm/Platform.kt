package com.akshayashokcode.bird_app_compose_kmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform