package com.devtalles.project.view;

import com.devtalles.project.controller.TaskController;
import com.devtalles.project.execeptions.TaskException;
import com.devtalles.project.execeptions.TaskValidationException;
import com.devtalles.project.model.Task;
import java.util.Scanner;

public class TaskView {
    private final TaskController taskController;
    private final Scanner scanner;

    public TaskView(TaskController taskController) {
        this.taskController = taskController;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println();
            System.out.println("Welcome to the Task Management System");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. List Tasks");
            System.out.println("4. List Completed Tasks");
            System.out.println("5. List Pending Tasks");
            System.out.println("6. Update Task");
            System.out.println("7. Update Task Status");
            System.out.println("8. Exit");
            System.out.println("Enter your choice : ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addTaskView();
                    break;
                case "2":
                    removeTaskView();
                    break;
                case "3":
                    showTasksView();
                    break;
                case "4":
                    showCompletedTasksView();
                    break;
                case "5":
                    showPendingTasksView();
                    break;
                case "6":
                    updateTaskView();
                    break;
                case "7":
                    updateCompletedTaskView();
                    break;
                case "8":
                    System.out.println("Bye bye.");
                    return;
                    default:
                        System.out.println("Invalid choice.");
            }
        }
    }

    public void addTaskView() {
        try {
            Task task = getTaskInput();
            taskController.addTask(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted());
            System.out.println("Task added successfully.");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ups ! Something went wrong.");
        }
    }

    public void removeTaskView() {
        try {
            System.out.println("Enter task id: ");
            String id = scanner.nextLine();

            taskController.removeTask(id);
            System.out.println("Task removed successfully.");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ups ! Something went wrong.");
        }
    }

    public void showTasksView() {
        try {
            System.out.println();
            System.out.println("List of Tasks");
            taskController.showTasks();
        } catch (TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ups ! Something went wrong.");
        }
    }

    public void updateTaskView() {
        try {
            Task task = getTaskInput();
            taskController.updateTask(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted());
            System.out.println("Task updated successfully.");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ups ! Something went wrong.");
        }
    }

    public void updateCompletedTaskView() {
        try {
            String id;
            do {
                System.out.println("Enter task id: ");
                id = scanner.nextLine();
                if(id.isEmpty()) {
                    System.out.println("Task id cannot be empty.");
                }
            } while (id.isEmpty());

            Boolean isCompleted = null;
            while (isCompleted == null) {
                System.out.println("Is your task completed? (true/false): ");
                String rawInput = scanner.nextLine().trim();
                if (rawInput.equalsIgnoreCase("true")) {
                    isCompleted = true;
                } else if (rawInput.equalsIgnoreCase("false")) {
                    isCompleted = false;
                } else {
                    System.out.println("Invalid input. Please introduce true or false.");
                }
            }

            taskController.updateTaskCompleted(id, isCompleted);
            System.out.println("Task updated successfully.");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ups ! Something went wrong.");
        }
    }

    public void showCompletedTasksView() {
        try {
            System.out.println();
            System.out.println("List of Completed Tasks");
            taskController.showCompletedTasks();
        } catch (TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ups ! Something went wrong.");
        }
    }

    public void showPendingTasksView() {
        try {
            System.out.println();
            System.out.println("List of Pending Tasks");
            taskController.showPendingTasks();
        } catch (TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ups ! Something went wrong.");
        }
    }

    private Task getTaskInput() {
        String id;
        do {
            System.out.println("Enter task id: ");
            id = scanner.nextLine();
            if(id.isEmpty()) {
                System.out.println("Task id cannot be empty.");
            }
        } while (id.isEmpty());

        String title;
        do {
            System.out.println("Enter task title: ");
            title = scanner.nextLine();
            if(title.isEmpty()) {
                System.out.println("Task title cannot be empty.");
            }
        } while (title.isEmpty());

        String description;
        do {
            System.out.println("Enter task description: ");
            description = scanner.nextLine();
            if(description.isEmpty()) {
                System.out.println("Task description cannot be empty.");
            }
        } while (description.isEmpty());

        Boolean isCompleted = null;

        while (isCompleted == null) {
            System.out.println("Is your task completed? (true/false): ");
            String rawInput = scanner.nextLine().trim();
            if (rawInput.equalsIgnoreCase("true")) {
                isCompleted = true;
            } else if (rawInput.equalsIgnoreCase("false")) {
                isCompleted = false;
            } else {
                System.out.println("Invalid input. Please introduce true or false.");
            }
        }

        return new Task(id, title, description, isCompleted);
    }
}
