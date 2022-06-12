package ru.nsu.malov.builder

import org.gradle.tooling.GradleConnector
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Path

class Builder {
    fun buildLab(name: String, fileName: String){
        if (!File("./repos/$name/$fileName").exists()){
            throw FileNotFoundException("$name doesn't have such file")
        }
        val connection = GradleConnector.newConnector().forProjectDirectory(File("./repos/$name/$fileName")).connect()
        connection.use { connection ->
            connection.newBuild().forTasks("test").run()
        }
        createReport(name, fileName)
    }

    fun documentation(name: String, fileName: String){
        if (!File("./repos/$name/$fileName").exists()){
            throw FileNotFoundException("$name doesn't have such file")
        }
        val connection = GradleConnector.newConnector().forProjectDirectory(File("./repos/$name/$fileName")).connect()
        connection.use { connection ->
            connection.newBuild().forTasks("javadoc").run()
        }
    }

    private fun createReport(name: String, fileName: String){
        if (!Files.isDirectory(Path.of("./reports/$name/"))){
            Files.createDirectories(Path.of("./reports/$name/"))
        }
        val file = File("./testReports/$name/$fileName.html")
        if(!file.exists()){
            file.createNewFile()
        }
        val reportFile = File("./repos/$name/$fileName/build/reports/tests/test/index.html")
        file.writeText(reportFile.readText())

    }
}