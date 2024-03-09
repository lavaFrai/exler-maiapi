package ru.lavafrai.exler.mai.types

import kotlinx.serialization.Serializable

@Serializable
data class Faculty (
    val name: String,
    val path: String,
)