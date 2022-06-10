package ru.nsu.malov.dsl

import config.MakeConfig
import ru.nsu.malov.dsl.builders.StudentBuilder
import ru.nsu.malov.dsl.builders.TaskBuilder
import ru.nsu.malov.dsl.constructors.Student
import ru.nsu.malov.dsl.constructors.TaskList
import java.io.File
import java.lang.module.Configuration
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class DSL {
    fun student(block: StudentBuilder.() -> Unit): Student = StudentBuilder().apply(block).build()

    fun taskList(block: TaskBuilder.() -> Unit): TaskList = TaskBuilder().apply(block).build()
}


fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("-makeConfig - create config file")
        println("-printConfig - print result of reading config file")
    }
    if (args[0] == "-makeConfig") {
        makeConfig()
    }
    if (args[0] == "-printConfig") {
        printConfig()
    }
}

fun printConfig() {
    val dsl: DSL
    println("Write students name")
    val path = "./configs/${readLine()}.kts"
    if (!File(path).exists()){
        println("No such config")
        printConfig()
    }
    val textConfig = File(path).readText()
    println(textConfig)
    with(ScriptEngineManager().getEngineByExtension("kts")){
        val scriptResult = eval(textConfig)
        println(scriptResult)
    }

}

fun makeConfig() {
    val makeConfig = MakeConfig()
    println("Write student's name")
    val name = readLine()
    if (File("./configs/$name.kts").exists()) {
        println("You have such config file")
        return
    }
    println("Write student's nickname")
    val nickname = readLine()
    println("Write link of student's repo")
    val repoUrl = readLine()
    println("Write student's group")
    val group = readLine()!!.toInt()
    makeConfig.setUpStudent("$name", "$nickname", "$repoUrl", group)
}
