package ru.lavafrai.exler.mai.types

import kotlinx.serialization.Serializable

@Serializable
data class TeacherReview(
    val author: String?,
    val source: String?,
    val publishTime: String?,
    val hypertext: String?,
)