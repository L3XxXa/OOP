package ru.nsu.malov.dsl.sentinels

import ru.nsu.malov.dsl.builders.LessonsBuilder
import ru.nsu.malov.dsl.builders.MarkBuilder
import ru.nsu.malov.dsl.constructors.Mark

class Marks : ArrayList<Mark>() {
    fun mark(block: MarkBuilder.() -> Unit){
        add(MarkBuilder().apply(block).build())
    }

}
