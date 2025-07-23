package com.devtalles.project.model;

import com.devtalles.project.execeptions.TaskException;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    List<Task> tasks = new ArrayList<>();

    public void save(Task task) throws TaskException {
        if (task == null) {
            throw new TaskException("Task cannot be null.");
        }

        tasks.add(task);
    }

    public Task getById(String id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
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
    }

    public void remove(Task task) throws TaskException {
        if (task == null) {
            throw new TaskException("Task does not exist.");
        }

        tasks.remove(task);
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
    }
}
