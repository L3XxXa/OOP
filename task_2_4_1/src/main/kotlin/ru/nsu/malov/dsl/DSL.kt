package ru.nsu.malov.dsl

class DSL {
    fun student(block: StudentBuilder.() -> Unit) : Student = StudentBuilder().apply(block).build()

    val student = student {
        name = "Malov Alexey"
        nickName = "L3XxXa"
        repoUrl = "onlyfans.com"
        group {
            name = 20214
        }
        givenTasks {
            givenTasks {
                taskId = "Task 2_4_1"
                name = "DSL"
                deadLine = "18-05-2022"
            }
        }
    }
}

fun main() {
    val dsl = DSL()
    println(dsl.student)
}