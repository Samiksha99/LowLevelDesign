import java.util.Date;

public class Task {
    private int id;
    private TaskPriority priority;
    private String title;

    private String description;
    private Date dueDate;
    private User assignedTo;
    private User createdBy;
    private TaskStatus taskStatus;

    public Task(int id, TaskPriority priority, String title, String description, Date dueDate, User assignedTo, User createdBy) {
        this.id = id;
        this.priority = priority;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.assignedTo = assignedTo;
        this.createdBy = createdBy;
        this.taskStatus = TaskStatus.UNASSIGNED;
    }

    public int getId() {
        return id;
    }

    public synchronized TaskPriority getPriority() {
        return priority;
    }

    public synchronized void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getTitle() {
        return title;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public synchronized void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }



}
