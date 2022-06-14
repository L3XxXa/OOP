package ru.nsu.malov.dsl.sentinels

import ru.nsu.malov.dsl.constructors.Lesson
import ru.nsu.malov.dsl.builders.LessonsBuilder

class Lessons : ArrayList<Lesson>(){
    fun lesson(block: LessonsBuilder.() -> Unit){
        add(LessonsBuilder().apply(block).build())
    }
}
