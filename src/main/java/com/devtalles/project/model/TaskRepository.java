package com.devtalles.project.model;

import com.devtalles.project.execeptions.TaskException;
import com.devtalles.project.persistence.TaskPersistence;
import java.util.List;

public class TaskRepository {
    public List<Task> tasks;

    public TaskRepository() {
        this.tasks = TaskPersistence.loadTasks();
    }

    public void save(Task task) throws TaskException {
        if (tasks.contains(task)) {
            throw new TaskException("Task already exists.");
        }

        tasks.add(task);

        // Commit changes
        TaskPersistence.saveTasks(tasks);
    }

    public Task getById(String id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Task> getCompletedTasks() throws TaskException {
        List<Task> completedTasks = tasks.stream().filter(Task::isCompleted).toList();

        if (completedTasks.isEmpty()) {
            throw new TaskException("There is not completed tasks");
        }

        return completedTasks;
    }

    public List<Task> getPendingTasks() throws TaskException {
        List<Task> pendingTasks = tasks.stream().filter(task -> !task.isCompleted()).toList();

        if (pendingTasks.isEmpty()) {
            throw new TaskException("There is not pending tasks");
        }

        return pendingTasks;
    }

    public void remove(String id) throws TaskException {
        Task task = getById(id);

        if (task == null) {
            throw new TaskException("Task cannot be null.");
        }

        // Importante implementar el equals y hashCode en Task
        if(!tasks.contains(task)) {
            throw new TaskException("Task does not exist.");
        }

        tasks.remove(task);

        // Commit changes
        TaskPersistence.saveTasks(tasks);
    }

    public void remove(Task task) throws TaskException {
        if (task == null) {
            throw new TaskException("Task does not exist.");
        }

        tasks.remove(task);

        // Commit changes
        TaskPersistence.saveTasks(tasks);
    }

    public List<Task> getAll() throws TaskException {
        if(tasks.isEmpty()) {
            throw new TaskException("Tasks list is empty.");
        }

        return tasks;
    }

    public int getIndexById(String id) {
        Task task = getById(id);
        return tasks.indexOf(task);
    }

    public void update(Task updatedTask) throws TaskException {
        if (updatedTask == null) {
            throw new TaskException("Task cannot be null.");
        }

        int taskIdx = getIndexById(updatedTask.getId());

        if (taskIdx < 0) {
            throw new TaskException("Task does not exist.");
        }

       tasks.set(taskIdx, updatedTask);

        // Commit changes
        TaskPersistence.saveTasks(tasks);
    }

    public void updateTaskCompleted(String id, boolean completed) throws TaskException {
        int taskIdx = getIndexById(id);
        if (taskIdx < 0) {
            throw new TaskException("Task does not exist.");
        }

        tasks.get(taskIdx).setCompleted(completed);

        // Commit changes
        TaskPersistence.saveTasks(tasks);
    }
}
