package ru.nsu.malov.git

import org.eclipse.jgit.*
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.errors.JGitInternalException
import org.eclipse.jgit.api.errors.RefNotFoundException
import org.eclipse.jgit.revwalk.filter.CommitTimeRevFilter
import org.eclipse.jgit.transport.URIish
import ru.nsu.malov.dsl.constructors.Student
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.*
import javax.script.ScriptEngineManager
import kotlin.collections.List

class WorkWithGit {
    fun pullRepo(name: String, branch: String) {
        val git = Git.open(File("./repos/$name"))
        try {
            git.checkout().setName(branch).call()
        } catch (e: RefNotFoundException) {
            git.checkout().setCreateBranch(true).setName(branch).call()
            git.checkout().setName(branch).call()
        }
        try {
            git.pull().remote = name
            git.pull().call()
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }
    }

    fun cloneRepo(name: String){
        Git.init().setDirectory(File("./repos")).call()
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
    }

    fun logStudentsAttendance(monday: Date, sunday: Date, name: String): Boolean{
        val git = Git.open(File("./repos/$name"))
        val between = CommitTimeRevFilter.between(monday, sunday)
        val revCommit = git.log().setRevFilter(between).call()
        if (revCommit.count() > 0){
            return true
        }
        return false
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