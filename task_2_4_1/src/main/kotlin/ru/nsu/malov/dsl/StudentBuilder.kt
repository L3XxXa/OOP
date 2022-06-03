package ru.nsu.malov.dsl

class StudentBuilder {
    var name = ""
    var nickName = ""
    var repoUrl = ""

    lateinit var group: Group
    fun group(block: GroupBuilder.() -> Unit) {
        group = GroupBuilder().apply(block).build()
    }

    var givenTask = mutableListOf<GivenTask>()

    fun givenTask(block: GivenTaskBuilder.() -> Unit){
        givenTask.add(GivenTaskBuilder().apply(block).build())
    }
    var lessons = mutableListOf<Lesson>()
    fun lesson(block: LessonsBuilder.() -> Unit){
        lessons.add(LessonsBuilder().apply(block).build())
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

        return Student(nickName, name, repoUrl, group, givenTask, lessons)
    }
}