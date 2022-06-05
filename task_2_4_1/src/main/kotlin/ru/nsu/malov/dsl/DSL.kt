package ru.nsu.malov.dsl

import ru.nsu.malov.dsl.builders.StudentBuilder
import ru.nsu.malov.dsl.constructors.Student
import ru.nsu.malov.dsl.constructors.TaskList

class DSL {
    fun student(block: StudentBuilder.() -> Unit) : Student = StudentBuilder().apply(block).build()

    fun taskList(block: TaskBuilder.() -> Unit) : TaskList = TaskBuilder().apply(block).build()

    val student = student {
        name = "Malov Alexey"
        nickName = "L3XxXa"
        repoUrl = "onlyfans.com"
        group {
            name = 20214
        }
        tasks {
            givenTask {
                taskId = "task 2.4.1"
                deadLine = "20-12-2022"
            }
            givenTask {
                taskId = "task 2.3.1"
                deadLine = "12-12-2022"
            }
        }
        lessons {
            lesson {
                attendance = true
                date = "12-12-2022"
            }
        }
        marks{
            mark {
                name = 5
                date = "12-12-2022"
            }
        }
    }
    val tasks = taskList {
        tasks {
            task {
                taskId = "Task 2.4.1"
                taskName = "DSL"
                score = 2
            }
            task {
                taskId = "Task 2.3.1"
                taskName = "SNAKE"
                score = 2
            }
        }
    }

}

fun main() {
    val dsl = DSL()
    println(dsl.student)
    println(dsl.tasks)
}