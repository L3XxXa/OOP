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
            taskId = "task_2_3_1"
            deadLine = "12-12-2022"
        }
        givenTask{
            taskId = "task_2_1_1"
            deadLine = "16-12-2022"
        }
    }
    lessons {
        lesson{
            attendance = true
            date = "16-06-2022"
        }
        lesson{
            attendance = false
            date = "25-06-2022"
        }
    }
    marks{
        mark {
            name = 5
            date = "12-12-2022"
        }
        mark {
            name = 4
            date = "14-12-2022"
        }
        mark {
            name = 3
            date = "16-12-2022"
        }
        mark {
            name = 2
            date = "16-12-2022"
        }
    }
}