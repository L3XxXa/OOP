package ru.nsu.malov.dsl.builders

import ru.nsu.malov.dsl.constructors.Group

class GroupBuilder {
    var name = 0

    fun build(): Group {
        if (name == 0){
            throw IllegalArgumentException("Number of group is required")
        }
        return Group(name)
    }

}
