package ru.nsu.malov.builder

import org.gradle.tooling.GradleConnector
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

class Builder {
    fun buildLab(name: String, fileName: String){
        if (!File("./repos/$name/$fileName").exists()){
            throw FileNotFoundException("$name doesn't have such file")
        }
        val connection = GradleConnector.newConnector().forProjectDirectory(File("./repos/$name/$fileName")).connect()
        connection.use { connection ->
            connection.newBuild().forTasks("test").run()
        }
        println("successfully tested this lab")
    }

    fun documentation(name: String, fileName: String){
        if (!File("./repos/$name/$fileName").exists()){
            throw FileNotFoundException("$name doesn't have such file")
        }
        val connection = GradleConnector.newConnector().forProjectDirectory(File("./repos/$name/$fileName")).connect()
        connection.use { connection ->
            connection.newBuild().forTasks("javadoc").run()
        }
        createDocumentation(name, fileName)
    }
    fun checkCodeStyle(name: String, lab: String) {
        if (!File("./repos/$name/$lab").exists()){
            throw FileNotFoundException("$name doesn't have such file")
        }
        val connection = GradleConnector.newConnector().forProjectDirectory(File("./repos/$name/$lab")).connect()
        connection.use { connection ->
            connection.newBuild().forTasks("checkstyleMain").run()
        }
        makeCodeStyleCheckReport(name, lab)
    }

    private fun makeCodeStyleCheckReport(name: String, lab: String) {
        val directory = File("./reports/$name/$lab/codeStyle")
        val dir = Path.of("./reports/$name/$lab/codeStyle")
        val origin = Path.of("./repos/$name/$lab/build/reports/checkstyle")
        if (directory.length() != 0L){
            Files.walk(dir)
                .sorted(Comparator.reverseOrder())
                .map { it.toFile() }
                .forEach { it.delete() }
            Files.createDirectories(dir)
        }
        Files.walk(origin).forEach{
            Files.copy(it, dir.resolve(origin.relativize(it)), StandardCopyOption.REPLACE_EXISTING)
        }
        val documentationFile = File("./reports/$name/$lab/codeStyle/main.html")
        documentationFile.renameTo(File("./reports/$name/$lab/codeStyle/codeStyle.html"))
    }


    private fun createDocumentation(name: String, fileName: String) {
        val directory = File("./reports/$name/$fileName/documentation")
        val dir = Path.of("./reports/$name/$fileName/documentation")
        val origin = Path.of("./repos/$name/$fileName/build/docs/javadoc")
        if (directory.length() != 0L){
            Files.walk(dir)
                .sorted(Comparator.reverseOrder())
                .map { it.toFile() }
                .forEach { it.delete() }
            Files.createDirectories(dir)
        }
        Files.walk(origin).forEach{
            Files.copy(it, dir.resolve(origin.relativize(it)), StandardCopyOption.REPLACE_EXISTING)
        }
        val documentationFile = File("./reports/$name/$fileName/documentation/allclasses-index.html")
        documentationFile.renameTo(File("./reports/$name/$fileName/documentation/Documentation.html"))
    }


}