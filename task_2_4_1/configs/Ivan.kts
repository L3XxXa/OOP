import ru.nsu.malov.dsl.DSL

DSL().student {
       nickName = "ivan"
       name = "Ivan"
       repoUrl = "aaa"
       group {
            name = 2222
       }
       tasks {

      /*
      write given tasks for this student here
      like
   givenTask {
    taskId = "task 2.4.1"
    deadLine = "dd-MM-yyyy"
   }
      */
     }
    lessons {
      /*
      write lessons with attendance of this student here
      like
   lesson {
    attendance = true
    date = "dd-MM-yyyy"
   }
      */
    }
    marks {
      /*
      write marks of this student here
      like
   mark {
    name = 5
    date = "dd-MM-yyyy"
   }
 */
    }
}