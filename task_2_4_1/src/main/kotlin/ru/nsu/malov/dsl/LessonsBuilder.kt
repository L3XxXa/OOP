package ru.nsu.malov.dsl

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LessonsBuilder {
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    private var lessonDate: LocalDate = LocalDate.parse("01-01-0001", dateTimeFormatter)
    var lesson: String = ""
        set(value) {
            lessonDate = LocalDate.parse(value, dateTimeFormatter)
        }

    fun build(): Lesson {
        if (lessonDate == LocalDate.parse("01-01-0001", dateTimeFormatter)){
            throw IllegalArgumentException("Date of the lesson is required")
        }
        return Lesson(lessonDate)
    }

}
