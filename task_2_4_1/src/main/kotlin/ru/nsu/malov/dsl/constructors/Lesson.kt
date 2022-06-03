package ru.nsu.malov.dsl.constructors

import java.time.LocalDate
import java.util.Date

data class Lesson(var date: LocalDate, var attendance: Boolean)