import ru.nsu.malov.dsl.builders.StudentBuilder
import ru.nsu.malov.dsl.constructors.Student
import ru.nsu.malov.dsl.constructors.TaskList


student {
	name = "Semen Persunov"
	nickname = "AnonSDvacha" 
	repoUrl = "2ch.hk" 
 	group {
 		name = 420
	}
	tasks {
		/*
		write given tasks for this student here
		like
			givenTask {
				taskId = "task 2.4.1"
				deadLine = "20-12-2022"
			}
		*/
	}
	lessons {
		/*
		write lessons with attendance of this student here
		like
			lesson {
				attendance = true
				date = "20-12-2022"
			}
		*/
	}
	marks {
		/*
		write marks of this student here
		like
			mark {
				name = 5
				date = "20-12-2022"
			}
		*/
	}
}