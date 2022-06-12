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
        createDocumentation(name, fileName)
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

    private fun createReport(name: String, fileName: String){
        if (!Files.isDirectory(Path.of("./reports/$name/$fileName/"))){
            Files.createDirectories(Path.of("./reports/$name/"))
        }
        val file = File("./testReports/$name/$fileName/$fileName'Report.html")
        if(!file.exists()){
            file.createNewFile()
        }
        val reportFile = File("./repos/$name/$fileName/build/reports/tests/test/index.html")
        file.writeText(reportFile.readText())

    }
}