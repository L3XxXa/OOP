package ru.nsu.malov.dsl.builders

import ru.nsu.malov.dsl.constructors.Lesson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LessonsBuilder {
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    private var lessonDate: LocalDate = LocalDate.parse("01-01-0001", dateTimeFormatter)
    var date: String = ""
        set(value) {
            lessonDate = LocalDate.parse(value, dateTimeFormatter)
        }

    var attendance = false
    fun build(): Lesson {
        if (lessonDate == LocalDate.parse("01-01-0001", dateTimeFormatter)) {
            throw IllegalArgumentException("Date of the lesson is required")
        }
        return Lesson(lessonDate, attendance)
    }

}
