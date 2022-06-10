package ru.nsu.malov.dsl

import ru.nsu.malov.dsl.builders.StudentBuilder
import ru.nsu.malov.dsl.constructors.Student
import ru.nsu.malov.dsl.constructors.TaskList

class DSL {
    fun student(block: StudentBuilder.() -> Unit) : Student = StudentBuilder().apply(block).build()

    fun taskList(block: TaskBuilder.() -> Unit) : TaskList = TaskBuilder().apply(block).build()
}



fun main() {
    val dsl = DSL()
    val readWriteConfigs = ReadWriteConfigs()
    readWriteConfigs.setUpStudent("Semen Persunov", "AnonSDvacha", "2ch.hk", 420)
}