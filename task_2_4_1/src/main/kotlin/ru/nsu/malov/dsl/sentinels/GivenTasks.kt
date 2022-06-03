package ru.nsu.malov.dsl.sentinels

import ru.nsu.malov.dsl.constructors.GivenTask
import ru.nsu.malov.dsl.builders.GivenTaskBuilder

class GivenTasks : ArrayList<GivenTask>(){
    fun givenTask(block: GivenTaskBuilder.() -> Unit){
        add(GivenTaskBuilder().apply(block).build())
    }

}
