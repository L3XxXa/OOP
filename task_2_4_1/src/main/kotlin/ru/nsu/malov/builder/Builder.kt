package ru.nsu.malov.builder

import org.gradle.tooling.GradleConnector
import java.io.File
import java.io.FileNotFoundException

class Builder {
    fun buildLab(name: String, fileName: String){
        if (!File("./repos/$name/$fileName").exists()){
            throw FileNotFoundException("$name doesn't have such file")
        }
        val connection = GradleConnector.newConnector().forProjectDirectory(File("./repos/$name/$fileName")).connect()
        try{
            connection.newBuild().forTasks("test").run()
        } finally {
            connection.close()
        }
    }
}