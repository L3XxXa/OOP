package ru.nsu.malov.dsl.constructors

data class Student(
    var nickName: String,
    var name: String,
    var repoUrl: String,
    var group: Group,
    var tasks: List<GivenTask>,
    val lessons: List<Lesson>,
    val marks: List<Mark>
)
