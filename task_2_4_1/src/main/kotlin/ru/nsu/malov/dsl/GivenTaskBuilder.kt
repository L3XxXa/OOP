package ru.nsu.malov.dsl

import java.text.SimpleDateFormat
import java.util.Date

class GivenTaskBuilder {
    var taskId = ""
    private var deadLineDate: Date = Date()
    var deadLine: String = ""
    set(value) {
        deadLineDate = SimpleDateFormat("dd-MM-yyyy").parse(value)
    }

    fun build():GivenTask = GivenTask(taskId, deadLineDate)

}
