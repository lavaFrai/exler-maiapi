package ru.lavafrai.exler.mai.types

import kotlinx.serialization.Serializable

@Serializable
data class TeacherInfo (
    val name: String,
    val faculty: String?,
    val department: String?,
    val photo: List<String>?,
    val reviews: List<TeacherReview>,
)