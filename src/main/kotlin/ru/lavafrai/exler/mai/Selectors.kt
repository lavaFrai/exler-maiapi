package ru.lavafrai.exler.mai

class Selectors {
    companion object {
        const val FACULTIES_NAME_SELECTOR = "body > center > table > tbody > tr > td:nth-child(1) > table:nth-child(2) > tbody > tr > td > div:nth-child(3) > table > tbody > tr > td > table > tbody > tr > td:nth-child(3) > div > b"
        const val TEACHER_NAME_SELECTOR = "body > center > table > tbody > tr > td:nth-child(1) > table:nth-child(2) > tbody > tr > td > ol > li > a"
        const val TEACHER_IMAGE_SELECTOR = "body > center > table > tbody > tr > td:nth-child(1) > table:nth-child(2) > tbody > tr > td > a:nth-child(4) > img"
        const val TEACHER_FACULTY_SELECTOR = "body > center > table > tbody > tr > td:nth-child(1) > table:nth-child(2) > tbody > tr > td > small:nth-child(3) > font"
    }
}
