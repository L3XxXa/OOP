package ru.nsu.malov.application

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.errors.JGitInternalException
import org.eclipse.jgit.api.errors.RefNotFoundException
import org.eclipse.jgit.internal.storage.file.FileRepository
import org.eclipse.jgit.transport.URIish
import ru.nsu.malov.config.MakeConfig
import ru.nsu.malov.dsl.constructors.Student
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.script.ScriptEngineManager


fun main(args: Array<String>) {
    val application = Application()
    if (args.isEmpty()) {
        println("-makeConfig - create config file")
        println("-printConfig - print result of reading config file")
    } else if (args[0] == "-makeConfig") {
        application.makeConfig()
    } else if (args[0] == "-printConfig") {
        application.printConfig()
    } else if (args[0] == "-clone") {
        application.cloneRepo()
    } else if (args[0] == "-pull") {
        application.pullRepo()
    }
}

class Application {
    fun printConfig() {
        println("Write student's name")
        val name = readLine()
        val path = "./configs/$name.kts"
        if (!File(path).exists()) {
            println("No such config")
            printConfig()
        }
        val student = configureStudent(name!!)
        println(student)
    }

    fun cloneRepo() {
        Git.init().setDirectory(File("./repos")).call()
        println("Write student's name")
        val name = readLine()
        if (!Files.isDirectory(Paths.get("./repos/$name"))) {
            Files.createDirectories(Paths.get("./repos/$name"))
        }
        val student = configureStudent(name!!)
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
        git.remoteAdd().setName("$name").setUri(URIish(student.repoUrl)).call()
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
        makeConfig.setUpStudent("$name", "$nickname", "$repoUrl", group)
    }

    fun pullRepo() {
        println("Write student's name")
        val name = readLine()
        if (!Files.isDirectory(Paths.get("./repos/$name"))) {
            println("No such directory. Did you clone this repo?")
        }
        println("Branch to pull")
        val branchName = readLine()
        val git = Git.open(File("./repos/$name"))
        try {
            git.checkout().setName(branchName).call()
        } catch (e: RefNotFoundException){
            git.checkout().setCreateBranch(true).setName(branchName).call()
            git.checkout().setName(branchName).call()
        }
        git.pull().remote = name
        git.pull().call()

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