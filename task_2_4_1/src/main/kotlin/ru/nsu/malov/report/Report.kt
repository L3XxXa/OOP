package ru.nsu.malov.report

import java.io.File
import kotlinx.html.*
import kotlinx.html.dom.*
import kotlinx.html.stream.appendHTML
import ru.nsu.malov.dsl.constructors.GivenTask
import ru.nsu.malov.dsl.constructors.Student
import ru.nsu.malov.builder.Builder
import java.awt.Color
import java.io.FileWriter
import java.nio.file.Path
import java.time.LocalDate
import javax.script.ScriptEngineManager


class Report {
    fun makeReport(name: String) {
        val file = File("./reports/$name/report.html")
        if (!file.exists()) {
            file.createNewFile()
        }
        val textConfig = File("./configs/$name.kts").readText()
        var scriptResult: Student
        with(ScriptEngineManager().getEngineByExtension("kts")) {
            scriptResult = eval(textConfig) as Student
        }
        val givenTasks = scriptResult.tasks
        val tasksMap = runTasks(givenTasks, name)
        val string = StringBuilder()
        string.appendHTML().html {
            head {
            }
            body {
                style {
                    unsafe {
                        raw(
                            ".green {" +
                                    "color: green;" +
                                    "}" +
                                    ".red {" +
                                    "color: red;" +
                                    "}" +
                                    ".orange {" +
                                    "color: orange;" +
                                    "}" +
                                    ".blue {" +
                                    "color: blue;" +
                                    "}"
                        )
                    }
                }
                h1 { +"Report by ${LocalDate.now()}" }
                h2 {
                    +"Student ${scriptResult.name}. Group: ${scriptResult.group.number}"
                }
                h3{
                    +"Nickname: ${scriptResult.nickName}"
                }
                h3{
                    +"Repository: ${scriptResult.repoUrl}"
                }
                h3 {
                    +"Attendance"
                }
                scriptResult.lessons.forEach {
                    div {
                        +"*${it.date} "
                        span(
                            if (it.attendance) {
                                "green"
                            } else {
                                "red"
                            }
                        ) {
                            +if (it.attendance) "ATTENDED" else "ABSENT"
                        }
                    }
                }
                h3 {
                    +"Laboratory works"
                }
                tasksMap.forEach {
                    div {
                        it.keys.forEach {
                            +"$it result "
                        }
                        span {
                            it.values.forEach {
                                span(
                                    if (it) {
                                        "green"
                                    } else {
                                        "red"
                                    }
                                ) {
                                    +if (it) "SUCCESSFUL BUILDING" else "SOMETHING WENT WRONG"
                                }

                            }
                        }
                    }
                }
                h3{
                    +"Marks"
                }
                scriptResult.marks.forEach{
                    div{
                        +"*${it.date} mark "
                        span (
                            when (it.name) {
                                5 -> {
                                    "green"
                                }
                                4 -> {
                                    "blue"
                                }
                                3 -> {
                                    "orange"
                                }
                                else -> {
                                    "red"
                                }
                            }
                        ){
                            + "${it.name}"
                        }
                    }
                }
            }
        }
        file.writeText(string.toString())

    }

    private fun runTasks(givenTasks: List<GivenTask>, name: String): ArrayList<Map<String, Boolean>> {
        val tasks = ArrayList<Map<String, Boolean>>()
        val builder = Builder()
        givenTasks.forEach {
            tasks.add(mapOf(it.taskId to builder.buildLab(name, it.taskId)))
        }
        return tasks
    }
}