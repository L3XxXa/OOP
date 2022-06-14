package ru.nsu.malov.dsl.builders

import ru.nsu.malov.dsl.constructors.Task
import ru.nsu.malov.dsl.sentinels.Tasks
import ru.nsu.malov.dsl.constructors.TaskList

class TaskBuilder {

    var tasks = mutableListOf<Task>()
    fun tasks(block: Tasks.() -> Unit){
        tasks.addAll(Tasks().apply(block))
    }

    var taskId = ""
    var taskName = ""
    var score = -1
    fun buildTask(): Task {
        if (taskId == ""){
            throw IllegalArgumentException("TaskID is required")
        }
        if (taskName == ""){
            throw IllegalArgumentException("Task name is required")
        }
        if (score == -1) {
            throw IllegalArgumentException("Score is required")
        }
        return Task(taskId, taskName, score)
    }

    fun build(): TaskList {
        return TaskList(tasks)
    }

}
