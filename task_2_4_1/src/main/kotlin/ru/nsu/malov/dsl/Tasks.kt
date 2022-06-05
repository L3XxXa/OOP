package ru.nsu.malov.dsl

import ru.nsu.malov.dsl.builders.GivenTaskBuilder

class Tasks: ArrayList<Task>() {
    fun task(block: TaskBuilder.() -> Unit){
        add(TaskBuilder().apply(block).buildTask())
    }

}
