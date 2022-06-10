package ru.nsu.malov.dsl.sentinels

import ru.nsu.malov.dsl.builders.TaskBuilder
import ru.nsu.malov.dsl.constructors.Task

class Tasks: ArrayList<Task>() {
    fun task(block: TaskBuilder.() -> Unit){
        add(TaskBuilder().apply(block).buildTask())
    }

}
