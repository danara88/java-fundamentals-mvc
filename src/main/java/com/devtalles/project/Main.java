package com.devtalles.project;
import com.devtalles.project.controller.TaskController;
import com.devtalles.project.model.TaskRepository;
import com.devtalles.project.view.TaskView;

public class Main {
    public static void main(String[] args) {
        // Dependency Injection Simulation
        TaskRepository taskRepository = new TaskRepository();
        TaskController taskController = new TaskController(taskRepository);
        TaskView view = new TaskView(taskController);

        // Execute program engine
        view.showMenu();
    }
}