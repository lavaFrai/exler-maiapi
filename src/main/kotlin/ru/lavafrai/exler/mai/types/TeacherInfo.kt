package ru.lavafrai.exler.mai.types

data class TeacherInfo (
    val name: String,
    val faculty: String?,
    val department: String?,
    val photo: String?,
    val reviews: List<TeacherReview>,
)