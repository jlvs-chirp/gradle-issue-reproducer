import org.gradle.internal.DefaultTaskExecutionRequest

abstract class GreetingTask : DefaultTask() {
    @TaskAction
    fun greet() {
        println("hello from GreetingTask")
    }
}

val taskProvider = tasks.register<GreetingTask>("hello") {

}


fun addTaskToTaskGraph(taskProvider: TaskProvider<*>) {
    gradle.startParameter.setTaskRequests(
        gradle.startParameter.taskRequests + DefaultTaskExecutionRequest(listOf("$path:${taskProvider.name}"))
    )
}

//gradle.startParameter.setTaskRequests(gradle.startParameter.taskRequests + DefaultTaskExecutionRequest(listOf("$path:${taskProvider.name}")))
addTaskToTaskGraph(taskProvider)

