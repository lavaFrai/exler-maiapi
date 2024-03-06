package ru.lavafrai.exler.mai.types

data class Teacher(
    val name: String,
    val path: String,

    val nameHash: Int = teacherNameHash(name),
)


fun teacherNameHash(name: String): Int {
    return name.trim().split(" ").sorted().joinToString(" ").lowercase().replace("ё", "е").hashCode()
}