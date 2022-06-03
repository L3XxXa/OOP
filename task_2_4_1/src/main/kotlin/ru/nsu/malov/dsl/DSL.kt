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
        givenTask {
            taskId = "Task 2_4_1"
            deadLine = "15-10-2022"
        }
        lesson{
            lesson = "20-02-2022"
        }
    }
}

fun main() {
    val dsl = DSL()
    println(dsl.student)
}