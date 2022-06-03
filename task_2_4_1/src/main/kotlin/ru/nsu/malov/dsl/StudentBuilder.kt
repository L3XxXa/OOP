package ru.nsu.malov.dsl

class StudentBuilder {
    var name = ""
    var nickName = ""
    var repoUrl = ""

    lateinit var group: Group
    fun group(block: GroupBuilder.() -> Unit) {
        group = GroupBuilder().apply(block).build()
    }

    var givenTasks = mutableListOf<GivenTask>()

    fun givenTasks(block: GivenTaskBuilder.() -> Unit){
        givenTasks.add(GivenTaskBuilder().apply(block).build())
    }

    fun build(): Student = Student(nickName, name, repoUrl, group, givenTasks)
}