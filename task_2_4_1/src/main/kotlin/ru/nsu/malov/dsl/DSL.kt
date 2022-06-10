package ru.nsu.malov.dsl

import ru.nsu.malov.dsl.builders.StudentBuilder
import ru.nsu.malov.dsl.constructors.Student
import ru.nsu.malov.dsl.constructors.TaskList

class DSL {
    fun student(block: StudentBuilder.() -> Unit) : Student = StudentBuilder().apply(block).build()

    fun taskList(block: TaskBuilder.() -> Unit) : TaskList = TaskBuilder().apply(block).build()
}



fun main(args: Array<String>) {
    if(args.isEmpty()) {
        println("-makeConfig - create config file")
    }
    if (args[0] == "-makeConfig"){
        val makeConfig = MakeConfig()
        makeConfig.setUpStudent("Semen Persunov", "AnonSDvacha", "2ch.hk", 420)
    }
}