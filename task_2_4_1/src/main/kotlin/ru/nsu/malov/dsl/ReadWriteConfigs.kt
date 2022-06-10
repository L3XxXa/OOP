package ru.nsu.malov.dsl

import ru.nsu.malov.dsl.constructors.GivenTask
import java.io.File

class ReadWriteConfigs {
    fun setUpStudent(name: String, nickname: String, repoUrl: String, group: Int){
        val file = File("./configs/$name.kts")
        if (!file.exists()){
            file.createNewFile()
            file.writeText("import ru.nsu.malov.dsl.builders.StudentBuilder\n" +
                    "import ru.nsu.malov.dsl.constructors.Student\n" +
                    "import ru.nsu.malov.dsl.constructors.TaskList")
        }
        if (file.length() == 0L){
            file.writeText("import ru.nsu.malov.dsl.builders.StudentBuilder\n" +
                    "import ru.nsu.malov.dsl.constructors.Student\n" +
                    "import ru.nsu.malov.dsl.constructors.TaskList")
        }
        val tasks = mutableListOf<GivenTask>()
//        val
    }
}