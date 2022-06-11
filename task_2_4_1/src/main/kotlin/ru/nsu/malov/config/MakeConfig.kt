package ru.nsu.malov.config

import java.io.File

class MakeConfig {
    fun setUpStudent(name: String, nickname: String, repoUrl: String, group: Int) {
        val file = File("./configs/$name.kts")
        if (!file.exists()) {
            file.createNewFile()
        }
        if (file.length() == 0L) {
            file.writeText(
                "import ru.nsu.malov.dsl.DSL\n\n" +
                        "DSL().student {\n" +
                        "\tname = \"$name\"\n" +
                        "\tnickName = \"$nickname\" \n" +
                        "\trepoUrl = \"$repoUrl\" \n " +
                        "\tgroup {\n \t\tname = $group\n\t}\n" +
                        "\ttasks {\n" +
                        "\t\t/*\n" +
                        "\t\twrite given tasks for this student here" +
                        "\n\t\tlike" +
                        "\n\t\t\tgivenTask {\n" +
                        "\t\t\t\ttaskId = \"task 2.4.1\"\n" +
                        "\t\t\t\tdeadLine = \"dd-MM-yyyy\"\n" +
                        "\t\t\t}" +
                        "\n\t\t*/" +
                        "\n\t}\n" +
                        "\tlessons {\n" +
                        "\t\t/*\n" +
                        "\t\twrite lessons with attendance of this student here" +
                        "\n\t\tlike" +
                        "\n\t\t\tlesson {\n" +
                        "\t\t\t\tattendance = true\n" +
                        "\t\t\t\tdate = \"dd-MM-yyyy\"\n" +
                        "\t\t\t}" +
                        "\n\t\t*/" +
                        "\n\t}\n" +
                        "\tmarks {\n" +
                        "\t\t/*\n" +
                        "\t\twrite marks of this student here" +
                        "\n\t\tlike" +
                        "\n\t\t\tmark {\n" +
                        "\t\t\t\tname = 5\n" +
                        "\t\t\t\tdate = \"dd-MM-yyyy\"\n" +
                        "\t\t\t}" +
                        "\n\t\t*/" +
                        "\n\t}\n" +
                        "}"
            )
            println("Successfully created a config")
        }
        else {
            println("Config already exists")
        }
    }
}