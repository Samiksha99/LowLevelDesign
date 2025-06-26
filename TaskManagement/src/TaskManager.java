import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TaskManager {

    private static TaskManager instance;
    private final Map<Integer, Task> tasks;

    private TaskManager() {
        tasks = new ConcurrentHashMap<>(); // ConcurrentHashMap allows multiple threads to read and write to the map simultaneously
    }

    public static synchronized TaskManager getInstance() { // synchronised so that not more than 1 instance is created
        if(instance == null){
            instance = new TaskManager();
        }
        return instance;
    }

    public void createTask(Task task){
        tasks.put(task.getId(), task);
    }

    public Task getTaskById(int id){
        if(!tasks.containsKey(id)){
            throw new RuntimeException("Task not found, " + id);
        }
        return tasks.get(id);
    }

    public void updateTaskStatus(int id, TaskStatus status){
        getTaskById(id).setTaskStatus(status);
    }
    public void updateTaskPriority(int id, TaskPriority priority){
        getTaskById(id).setPriority(priority);
    }
    public void updateTaskTitle(int id, String title){
        getTaskById(id).setTitle(title);
    }
    public void updateTaskDescription(int id, String title){
        getTaskById(id).setTitle(title);
    }
    public void updateTaskAssignedTo(int id, User user){
        getTaskById(id).setAssignedTo(user);
    }
    public void updateTaskDueDate(int id, Date dueDate){
        getTaskById(id).setDueDate(dueDate);
    }

    public List<Task> listTaskByUser(User user){
        return tasks.values().stream().
                filter(task -> user.equals(task.getAssignedTo()))
                .collect(Collectors.toList());

    }

    public List<Task> listTaskByStatus(TaskStatus status){
        return tasks.values().stream()
                .filter(task -> status.equals(task.getTaskStatus()))
                .collect(Collectors.toList());
    }
    public void remove(int id){
        tasks.remove(id);
    }

}
