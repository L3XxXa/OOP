package ru.nsu.malov.dsl

class GroupBuilder {
    var name = 0

    fun build() : Group = Group(name)

}
