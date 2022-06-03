package ru.nsu.malov.dsl

class DSL {
    fun Student.group(block: Group.() -> Unit) : Group = Group().apply(block)

    fun student(block: Student.() -> Unit) : Student = Student().apply(block)

    fun task(block: Task.() -> Unit) : Task = Task().apply(block)

    fun Lesson(block: Lesson.() -> Unit) : Lesson = Lesson().apply(block)

    fun mark(block: Mark.() -> Unit) : Mark = Mark().apply(block)

    fun givenTask(block: GivenTask.() -> Unit) : GivenTask = GivenTask().apply(block)
}