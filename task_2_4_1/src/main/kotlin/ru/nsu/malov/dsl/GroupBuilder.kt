package ru.nsu.malov.dsl

class GroupBuilder {
    var name = 0

    fun build(): Group {
        if (name == 0){
            throw IllegalArgumentException("Number of group is required")
        }
        return Group(name)
    }

}
