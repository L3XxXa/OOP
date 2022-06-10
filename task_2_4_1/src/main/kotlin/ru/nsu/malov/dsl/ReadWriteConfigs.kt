package ru.nsu.malov.dsl

import java.io.File

class ReadWriteConfigs {
    fun setUpStudent(name: String, nickname: String, repoUrl: String, group: Int){
        val file = File("./configs/$name.kts")
        if (!file.exists()){
            file.createNewFile()
        }

    }
}