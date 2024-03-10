import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private int id;
    private String description;
    private boolean isCompleted;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.isCompleted = false;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markCompleted() {
        isCompleted = true;
    }
}

class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean markTaskAsCompleted(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.markCompleted();
                return true;
            }
        }
        return false;
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("Tasks:");
        for (Task task : tasks) {
            System.out.println("ID: " + task.getId() + ", Description: " + task.getDescription() + ", Completed: "
                    + task.isCompleted());
        }
    }

    public int getTaskCount() {
        return tasks.size();
    }
}

interface TaskManagerUI {
    void displayMenu();
}

class ConsoleTaskManagerUI implements TaskManagerUI {
    private final TaskManager taskManager;
    private final Scanner scanner;

    public ConsoleTaskManagerUI(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayMenu() {
        while (true) {
            System.out.println("\nTask Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. Display Tasks");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    markTaskAsCompleted();
                    break;
                case 3:
                    displayTasks();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);

            }
        }
    }

    private void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        Task task = new Task(taskManager.getTaskCount() + 1, description);
        taskManager.addTask(task);
        System.out.println("Task added successfully.");
    }

    private void markTaskAsCompleted() {
        System.out.print("Enter task ID to mark as completed: ");
        int taskId = scanner.nextInt();
        if (taskManager.markTaskAsCompleted(taskId)) {
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private void displayTasks() {
        taskManager.displayTasks();
    }
}

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        TaskManagerUI taskManagerUI = new ConsoleTaskManagerUI(taskManager);
        taskManagerUI.displayMenu();
    }
}
