import ru.nsu.malov.dsl.DSL



DSL().student {
	name = "Ivan"
	nickName = "ivan"
	repoUrl = "repo" 
 	group {
 		name = 1
	}
	tasks {
		givenTask {
			taskId = "task 2.4.1"
			deadLine = "10-12-2022"
		}
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
		lesson {
			attendance = true
			date = "10-12-2022"
		}
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
		mark {
			name = 5
			date = "10-12-2022"
		}
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