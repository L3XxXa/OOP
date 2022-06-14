import ru.nsu.malov.dsl.DSL

DSL().student {
    nickName = "L3XxXa"
    name = "AlexeyMalov"
    repoUrl = "https://github.com/L3XxXa/OOP.git"
    group {
        name = 20214
    }
    tasks {
        givenTask {
            taskId = "task 2.4.1"
            deadLine = "20-12-2022"
        }
        givenTask {
            taskId = "task 2.3.1"
            deadLine = "12-12-2022"
        }
    }
    lessons {
        lesson{
            attendance = true
            date = "16-06-2022"
        }
    }
    marks{
        mark {
            name = 5
            date = "12-12-2022"
        }
    }
}