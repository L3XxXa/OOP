package ru.nsu.malov.attendance

import org.eclipse.jgit.api.Git
import ru.nsu.malov.config.MakeConfig
import ru.nsu.malov.dsl.constructors.Group
import ru.nsu.malov.dsl.constructors.Lesson
import ru.nsu.malov.dsl.constructors.Student
import ru.nsu.malov.git.WorkWithGit
import java.io.File
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.ZoneId
import java.time.temporal.TemporalAdjuster
import java.util.*
import javax.script.ScriptEngineManager
import javax.swing.text.DateFormatter
import kotlin.collections.ArrayList

class Attendance {
    fun addLesson(date: String, groupName: String, task: String){
        val configFiles = ArrayList<File>()
        File("./configs").walk().forEach {
            configFiles.add(it)
        }
        val allStudents = ArrayList<Student>()
        configFiles.forEach {
            if (it.name != "configs") {
                val textConfig = File("./configs/${it.name}").readText()
                var scriptResult: Student
                with(ScriptEngineManager().getEngineByExtension("kts")) {
                    scriptResult = eval(textConfig) as Student
                    allStudents.add(scriptResult)
                }

            }
        }
        val studentsInGroup = ArrayList<Student>()
        val group = Group(groupName.toInt())
        allStudents.forEach {
            if (it.group == group){
                studentsInGroup.add(it)
            }
        }
        if (studentsInGroup.isEmpty()){
            println("You don't have students of this group")
            return
        }
        val workWithGit = WorkWithGit()
        studentsInGroup.forEach {
            workWithGit.pullRepo(it.name, task)
        }
        val dateOfLesson = SimpleDateFormat("dd-MM-yyyy").parse(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        val monday = Date.from(dateOfLesson.with(DayOfWeek.MONDAY).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
        val sunday = Date.from(dateOfLesson.with(DayOfWeek.SUNDAY).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
        studentsInGroup.forEach {
            if (workWithGit.logStudentsAttendance(monday, sunday, it.name)){
                println("\u001B[32mStudent ${it.name} was on this lesson. You can add lesson $date to config\u001B[0m")
            }
            else{
                println("\u001B[31mStudent ${it.name} was absent. You can add lesson $date to config\u001B[0m")
            }
        }
    }


}