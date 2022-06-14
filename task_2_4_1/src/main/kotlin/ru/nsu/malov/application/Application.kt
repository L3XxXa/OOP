package ru.nsu.malov.application

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.errors.JGitInternalException
import org.eclipse.jgit.transport.URIish
import ru.nsu.malov.attendance.Attendance
import ru.nsu.malov.config.MakeConfig
import ru.nsu.malov.dsl.constructors.Student
import ru.nsu.malov.builder.Builder
import ru.nsu.malov.git.WorkWithGit
import ru.nsu.malov.report.Report
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.script.ScriptEngineManager


fun main(args: Array<String>) {
    val application = Application()
    if (args.isEmpty() || args[0] == "-help") {
        showHelpMessage()
        return
    }
    if (args[0] == "-makeConfig") {
        application.makeConfig()
        return
    } else if (args[0] == "-printConfig") {
        application.printConfig(args)
        return
    } else if (args[0] == "-clone") {
        application.cloneRepo(args)
        return
    } else if (args[0] == "-pull") {
        application.pullRepo(args)
        return
    } else if (args[0] == "-test") {
        application.buildLab(args)
        return
    } else if (args[0] == "-documentation") {
        application.makeDocumentation(args)
    } else if (args[0] == "-attendance") {
        application.addLesson(args)
    } else if (args[0] == "-codestyle") {
        application.checkCodeStyle(args)
    } else if (args[0] == "-codeCoverage"){
        application.checkCodeCoverage(args)
    } else if (args[0] == "-report"){
        application.makeReport(args)
    } else {
        showHelpMessage()
        return
    }
}

private fun showHelpMessage() {
    println(
        """
            -makeConfig - make configuration file
            -printConfig [name] - print configuration [student's name] file 
            -clone [name] - clone [student's] repository
            -pull [name] [branch] - pull [branch] from [student's name] repository 
            -test [name] [laboratory work] - build [student's name] [laboratory work]. Creates report in ./reports/[name] directory
            -documentation [name] [laboratory work] - make documentation about [student's name] [laboratory work]
            -attendance [date] [group name] [laboratory work] - check attendance of all students from [group name] at lesson [date] with given task [laboratory work]
            -codestyle [name] [laboratory work] - check code style for [student's name] [laboratory work]
            -codeCoverage [name] [laboratory work] - check code coverage of the unit test for [student's name] [laboratory work]
        """.trimIndent()
    )
}

class Application {
    fun printConfig(args: Array<String>) {
        val name: String
        try {
            name = args[1]
        } catch (e: java.lang.IndexOutOfBoundsException) {
            System.err.println("You didn't specify a name")
            return
        }
        val path = "./configs/$name.kts"
        if (!File(path).exists()) {
            println("No such config. Try again")
        }
        val student = configureStudent(name)
        println(student)
    }

    fun cloneRepo(args: Array<String>) {
        val name: String
        try {
            name = args[1]
        } catch (e: IndexOutOfBoundsException) {
            System.err.println("You didn't specify a name")
            return
        }
        val workWithGit = WorkWithGit()
        workWithGit.cloneRepo(name)
        println("Successfully cloned repo")
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
        try {
            makeConfig.setUpStudent("$name", "$nickname", "$repoUrl", group)
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }
        println("Successfully made a config")
    }

    fun pullRepo(args: Array<String>) {
        val name: String
        try {
            name = args[1]
        } catch (e: IndexOutOfBoundsException) {
            System.err.println("You didn't specify name")
            return
        }

        if (!Files.isDirectory(Paths.get("./repos/$name"))) {
            println("No such directory. Did you clone this repo?")
        }
        val branchName: String
        try {
            branchName = args[2]
        } catch (e: IndexOutOfBoundsException) {
            System.err.println("You didn't specify a branch")
            return
        }
        val workWithGit = WorkWithGit()
        workWithGit.pullRepo(name, branchName)
        println("Successfully pulled repo")
    }

    fun buildLab(args: Array<String>) {
        val builder = Builder()
        val name: String
        val lab: String
        try {
            name = args[1]
        } catch (e: IndexOutOfBoundsException) {
            System.err.println("You didn't specify a student's name")
            return
        }
        try {
            lab = args[2]
        } catch (e: IndexOutOfBoundsException) {
            System.err.println("You didn't specify a laboratory work")
            return
        }
        builder.buildLab(name, lab)
    }

    fun makeDocumentation(args: Array<String>) {
        val builder = Builder()
        val name: String
        val lab: String
        try {
            name = args[1]
        } catch (e: IndexOutOfBoundsException) {
            System.err.println("You didn't specify a student's name")
            return
        }
        try {
            lab = args[2]
        } catch (e: IndexOutOfBoundsException) {
            System.err.println("You didn't specify a laboratory work")
            return
        }
        builder.documentation(name, lab)
    }

    fun addLesson(args: Array<String>) {
        val attendance = Attendance()
        val name: String
        val groupName: String
        val task: String
        try {
            name = args[1]
        } catch (e: IndexOutOfBoundsException) {
            System.err.println("You didn't specify a date")
            return
        }
        try {
            groupName = args[2]
        } catch (e: IndexOutOfBoundsException) {
            System.err.println("You didn't specify a student's name")
            return
        }
        try {
            task = args[3]
        } catch (e: IndexOutOfBoundsException) {
            System.err.println("You didn't specify a student's name")
            return
        }
        attendance.addLesson(name, groupName, task)

    }

    fun checkCodeStyle(args: Array<String>) {
        val builder = Builder()
        val name: String
        val lab: String
        try {
            name = args[1]
        } catch (e: IndexOutOfBoundsException) {
            System.err.println("You didn't specify a student's name")
            return
        }
        try {
            lab = args[2]
        } catch (e: IndexOutOfBoundsException) {
            System.err.println("You didn't specify a laboratory work")
            return
        }
        builder.checkCodeStyle(name, lab)
    }

    fun checkCodeCoverage(args: Array<String>) {
        val builder = Builder()
        val name: String
        val lab: String
        try {
            name = args[1]
        } catch (e: IndexOutOfBoundsException) {
            System.err.println("You didn't specify a student's name")
            return
        }
        try {
            lab = args[2]
        } catch (e: IndexOutOfBoundsException) {
            System.err.println("You didn't specify a laboratory work")
            return
        }
        builder.checkTestCoverage(name, lab)
    }

    private fun configureStudent(name: String): Student {
        val textConfig = File("./configs/$name.kts").readText()
        var scriptResult: Student
        with(ScriptEngineManager().getEngineByExtension("kts")) {
            scriptResult = eval(textConfig) as Student
        }
        return scriptResult
    }

    fun makeReport(args: Array<String>) {
        val name: String
        try {
            name = args[1]
        } catch (e: IndexOutOfBoundsException){
            System.err.println("You didn't specify a name")
            return
        }
        val report = Report()
        report.makeReport(name)

    }
}