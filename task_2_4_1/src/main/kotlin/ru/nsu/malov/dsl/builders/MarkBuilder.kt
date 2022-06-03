package ru.nsu.malov.dsl.builders

import ru.nsu.malov.dsl.constructors.Mark
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MarkBuilder {
    var name = 0
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    private var markDate: LocalDate = LocalDate.parse("01-01-0001", dateTimeFormatter)
    var date: String = ""
        set(value) {
            markDate = LocalDate.parse(value, dateTimeFormatter)
        }


    fun build() : Mark{
        return Mark(name, markDate)
    }
}
