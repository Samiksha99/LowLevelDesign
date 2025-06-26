import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TaskManagementMainDemo {
    public static void main(String[] args){
        TaskManager taskManager = TaskManager.getInstance();

        User user1 = new User(1, "Sam", "sam@gmail.com");
        User user2 = new User(2, "iksha", "sam@gmail.com");
        Task task1 = new Task(1, TaskPriority.HIGH, "Complete LLD", "", new Date(2010, Calendar.FEBRUARY, 3), user1, user1);
        Task task2 = new Task(2, TaskPriority.MEDIUM, "lets see", "", new Date(2010, Calendar.FEBRUARY, 3), user1, user2);
        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.getTaskById(2);
        taskManager.getTaskById(1);

        // update task
        taskManager.updateTaskStatus(1, TaskStatus.IN_PROGRESS);
        taskManager.updateTaskStatus(2, TaskStatus.IN_PROGRESS);
        // get tasks by a user

        System.out.println("Tasks by user, "+ user1);
        List<Task> taskHistory = taskManager.listTaskByUser(user1);
        for(Task task: taskHistory){
            System.out.println(task.getTitle());
        }
        System.out.println("In progress tasks:");
        List<Task> inProgressTasks = taskManager.listTaskByStatus(TaskStatus.IN_PROGRESS);
        for(Task task: inProgressTasks){
            System.out.println(task.getTitle());
        }
        taskManager.updateTaskStatus(2, TaskStatus.COMPLETED);
        System.out.println("Completed tasks:");
        List<Task> completedTasks = taskManager.listTaskByStatus(TaskStatus.COMPLETED);
        for(Task task: completedTasks){
            System.out.println(task.getTitle());
        }
        System.out.println("In progress tasks:");
        List<Task> inProgressTasks2 = taskManager.listTaskByStatus(TaskStatus.IN_PROGRESS);
        for(Task task: inProgressTasks2){
            System.out.println(task.getTitle());
        }
    }

}