package ru.nsu.malov.dsl.builders

import ru.nsu.malov.dsl.constructors.*
import ru.nsu.malov.dsl.sentinels.GivenTasks
import ru.nsu.malov.dsl.sentinels.Lessons
import ru.nsu.malov.dsl.sentinels.Marks

class StudentBuilder {
    var name = ""
    var nickName = ""
    var repoUrl = ""

    lateinit var group: Group
    fun group(block: GroupBuilder.() -> Unit) {
        group = GroupBuilder().apply(block).build()
    }

    var givenTasks = mutableListOf<GivenTask>()

    fun tasks(block: GivenTasks.() -> Unit){
        givenTasks.addAll(GivenTasks().apply(block))
    }
    var lessons = mutableListOf<Lesson>()
    fun lessons(block: Lessons.() -> Unit){
        lessons.addAll(Lessons().apply(block))
    }

    var marks = mutableListOf<Mark>()
    fun marks(block: Marks.() -> Unit){
        marks.addAll(Marks().apply(block))
    }
    fun build(): Student {
        if (nickName == ""){
            throw IllegalArgumentException("Nickname is required")
        }
        else if (name == "") {
            throw IllegalArgumentException("Name is required")
        }
        else if (repoUrl == ""){
            throw IllegalArgumentException("URL of repository is required")
        }

        return Student(nickName, name, repoUrl, group, givenTasks, lessons, marks)
    }
}