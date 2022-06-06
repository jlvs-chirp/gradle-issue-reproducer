import org.gradle.execution.plan.ExecutionPlan

abstract class GreetingTask : DefaultTask() {
    @TaskAction
    fun greet() {
        println("hello from GreetingTask")
    }
}

val taskProvider = tasks.register<GreetingTask>("hello")

gradle.taskGraph.whenReady {
    withGroovyBuilder {
            val executionPlan: ExecutionPlan = getProperty("executionPlan") as ExecutionPlan
            executionPlan.addEntryTasks(listOf(taskProvider.get()))
            executionPlan.determineExecutionPlan()
    }
}
