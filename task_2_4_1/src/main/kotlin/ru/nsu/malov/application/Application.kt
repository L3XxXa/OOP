package ru.nsu.malov.application

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.errors.JGitInternalException
import org.eclipse.jgit.api.errors.RefNotFoundException
import org.eclipse.jgit.internal.storage.file.FileRepository
import org.eclipse.jgit.transport.URIish
import ru.nsu.malov.config.MakeConfig
import ru.nsu.malov.dsl.constructors.Student
import ru.nsu.malov.builder.Builder
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.script.ScriptEngineManager


fun main(args: Array<String>) {
    val application = Application()
    if (args.isEmpty() || args[0] == "-help"){
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
    } else if (args[0] == "-test"){
        application.buildLab(args)
        return
    }
    else{
        showHelpMessage()
        return
    }
}

private fun showHelpMessage(){
    println(
        """
            -makeConfig - make configuration file
            -printConfig [name] - print configuration [student's name] file 
            -clone [name] - clone [student's] repository
            -pull [name] [branch] - pull [branch] from [student's name] repository 
            -test [name] [laboratory work] - build [student's name] [laboratory work] 
        """.trimIndent()
    )
}

class Application {
    fun printConfig(args: Array<String>) {
        val name: String
        try {
            name = args[1]
        } catch (e: java.lang.IndexOutOfBoundsException){
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
        Git.init().setDirectory(File("./repos")).call()
        val name: String
        try {
            name = args[1]
        } catch (e: IndexOutOfBoundsException){
            System.err.println("You didn't specify a name")
            return
        }
        if (!Files.isDirectory(Paths.get("./repos/$name"))) {
            Files.createDirectories(Paths.get("./repos/$name"))
        }
        val student = configureStudent(name)
        try {
            Git.cloneRepository()
                .setURI(student.repoUrl)
                .setDirectory(File("./repos/$name"))
                .call()
        } catch (e: JGitInternalException) {
            println("You have already cloned this repo")
            return
        }
        val git = Git.open(File("./repos/$name"))
        git.remoteAdd().setName(name).setUri(URIish(student.repoUrl)).call()
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
        } catch (e: IndexOutOfBoundsException){
            System.err.println("You didn't specify name")
            return
        }

        if (!Files.isDirectory(Paths.get("./repos/$name"))) {
            println("No such directory. Did you clone this repo?")
        }
        val branchName: String
        try {
            branchName = args[2]
        } catch (e: IndexOutOfBoundsException){
            System.err.println("You didn't specify a branch")
            return
        }

        val git = Git.open(File("./repos/$name"))
        try {
            git.checkout().setName(branchName).call()
        } catch (e: RefNotFoundException) {
            git.checkout().setCreateBranch(true).setName(branchName).call()
            git.checkout().setName(branchName).call()
        }
        try {
            git.pull().remote = name
            git.pull().call()
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }
        println("Successfully pulled repo")
    }

    fun buildLab(args: Array<String>){
        val builder = Builder()
        val name: String
        val lab: String
        try {
            name = args[1]
        } catch (e: IndexOutOfBoundsException){
            System.err.println("You didn't specify a student's name")
            return
        }
        try {
            lab = args[2]
        } catch (e: IndexOutOfBoundsException){
            System.err.println("You didn't specify a laboratory work")
            return
        }
        builder.buildLab(name, lab)
    }
    private fun configureStudent(name: String): Student {
        val textConfig = File("./configs/$name.kts").readText()
        var scriptResult: Student
        with(ScriptEngineManager().getEngineByExtension("kts")) {
            scriptResult = eval(textConfig) as Student
        }
        return scriptResult
    }
}