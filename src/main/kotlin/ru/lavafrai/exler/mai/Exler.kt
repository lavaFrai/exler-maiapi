package ru.lavafrai.exler.mai

import org.jsoup.Jsoup
import ru.lavafrai.exler.mai.types.*
import kotlin.math.max
import kotlin.math.min

object Exler {
    private var teachers = null as List<Teacher>?


    fun parseFaculties(): List<Faculty> {
        val page = ExlerNetworkHelper.getPage("/education/prepods/")
        return page.select(Selectors.FACULTIES_NAME_SELECTOR).map {
            Faculty(
                it.text().removePrefix("«").removeSuffix("»"),
                it.select("a").attr("href")
            )
        }
    }


    @Synchronized
    fun parseTeachers(): List<Teacher> {
        synchronized(this) {
            if (this.teachers != null) return this.teachers!!

            val faculties = parseFaculties()
            val teachers = mutableListOf<Teacher>()
            faculties.forEach { faculty ->
                teachers.addAll(
                    ExlerNetworkHelper.getPage(faculty.path).select(Selectors.TEACHER_NAME_SELECTOR).map {
                        Teacher(
                            it.text(),
                            it.attr("href")
                        )
                    }
                )
            }
            this.teachers = teachers
            return teachers
        }

    }

    fun parseTeacherInfo(teacher: Teacher): TeacherInfo {
        val page = ExlerNetworkHelper.getPage(teacher.path)
        page.select("br").append("\\n");

        val reviewsFinds =
            "<!--subscribeBegin-->([\\s\\S]+?)<!--subscribeEnd-->".toRegex().findAll(page.html()).toList()
        val reviewsHtml = reviewsFinds.safeSubList(1, reviewsFinds.size).map { Jsoup.parse(it.groups[1]!!.value) }

        val reviews = reviewsHtml.map {
            val small = it.select("small")

            TeacherReview(
                author = "<b>Автор:</b>(.+?)<br>".toRegex().find(small.html())?.groups?.get(1)?.value?.trim()
                    ?.replace("&nbsp;", " ") ?: "<b>Авторы:</b>(.+?)<br>".toRegex()
                    .find(small.html())?.groups?.get(1)?.value?.trim()?.replace("&nbsp;", " "),
                source = "<b>Источник:</b>(.+?)<br>".toRegex().find(small.html())?.groups?.get(1)?.value?.trim()
                    ?.replace("&nbsp;", " "),
                publishTime = "<b>Опубликовано:</b>(.+?)<br>".toRegex()
                    .find(small.html())?.groups?.get(1)?.value?.trim()?.replace("&nbsp;", " "),
                hypertext = "</small>(.+)".toRegex().find(it.html())?.groups?.get(1)?.value?.trim()
                    ?.replace("&nbsp;", " ")
            )
        }

        return TeacherInfo(
            teacher.name,
            page.select(Selectors.TEACHER_FACULTY_SELECTOR).text().split("\\n").first().trim()
                .removePrefix("Факультет:").trim().replace("&nbsp;", " "),
            page.select(Selectors.TEACHER_FACULTY_SELECTOR).text().split("\\n").getOrNull(1)?.trim()
                ?.removePrefix("Кафедра: ")?.trim()?.replace("&nbsp;", " "),
            teacher.path + "/" + page.select(Selectors.TEACHER_IMAGE_SELECTOR).attr("src"),
            reviews,
        )
    }

    @Synchronized
    fun findTeacher(name: String): Teacher? {
        synchronized(this) {
            val teachers = parseTeachers()
            return teachers.find { it.fullNameEquals(name) }
        }
    }
}

fun <T> List<T>.safeSubList(fromIndex: Int, toIndex: Int): List<T> {
    if (fromIndex > toIndex) return emptyList()
    return subList(max(min(fromIndex.coerceAtLeast(0), size), 0), max(min(toIndex.coerceAtMost(size), size), 0))
}

fun Teacher.fullNameEquals(another: String): Boolean {
    return teacherNameHash(another) == this.nameHash
}
