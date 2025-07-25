package com.devtalles.project.controller;

import com.devtalles.project.execeptions.TaskException;
import com.devtalles.project.execeptions.TaskValidationException;
import com.devtalles.project.model.Task;
import com.devtalles.project.model.TaskRepository;

import java.util.List;

public class TaskController {

	/*
	 * ¿Por qué final?
	 * 1. Inmutabilidad: Una vez que asignamos en el contructor, no puede ser reasignado. La referencia
	 * nunca cambia durante la vida util del controlador.
	 * 2. Seguridad: Previene errores accidentales.
	 * 3. Claridad: Indica a otros desarrolaldores que este es una dependencia escencial.
	 */
	private final TaskRepository taskRepository;

	public TaskController(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public void addTask(String id, String title, String description, boolean isCompleted)
		throws TaskValidationException, TaskException {
		validateTaskData(id, title, description, isCompleted);

		Task task = new Task(id, title, description, isCompleted);
		taskRepository.save(task);
	}

	public void removeTask(String id) throws TaskValidationException, TaskException {
		if (id == null || id.trim()
			.isEmpty()) {
			throw new TaskValidationException("Task id cannot be empty.");
		}
		taskRepository.remove(id);
	}

	public void showTasks() throws TaskException {
		List<Task> tasks = taskRepository.getAll();
		tasks.forEach(System.out::println);
	}

	public void showCompletedTasks() throws TaskException {
		List<Task> completedTasks = taskRepository.getCompletedTasks();
		completedTasks.forEach(System.out::println);
	}

	public void showPendingTasks() throws TaskException {
		List<Task> pendingTasks = taskRepository.getPendingTasks();
		pendingTasks.forEach(System.out::println);
	}

	public void updateTask(String id, String title, String description, boolean isCompleted)
		throws TaskValidationException, TaskException {
		validateTaskData(id, title, description, isCompleted);

		Task task = new Task(id, title, description, isCompleted);
		taskRepository.update(task);
	}

	public void updateTaskCompleted(String id, boolean isCompleted)
		throws TaskValidationException, TaskException {
		validateTaskData(id, isCompleted);
		taskRepository.updateTaskCompleted(id, isCompleted);
	}

	private void validateTaskData(String id, String title, String description, Boolean isCompleted) throws TaskValidationException {
		if (id == null || id.trim()
			.isEmpty()) {
			throw new TaskValidationException("Task id cannot be empty.");
		}

		if (title == null || title.trim()
			.isEmpty()) {
			throw new TaskValidationException("Title cannot be empty.");
		}

		if (description == null || description.trim()
			.isEmpty()) {
			throw new TaskValidationException("Description cannot be empty.");
		}

		if (isCompleted == null) {
			throw new TaskValidationException("Complete status cannot be null.");
		}
	}

	private void validateTaskData(String id, Boolean isCompleted) throws TaskValidationException {
		if (id == null || id.trim()
			.isEmpty()) {
			throw new TaskValidationException("Task id cannot be empty.");
		}

		if (isCompleted == null) {
			throw new TaskValidationException("Complete status cannot be null.");
		}
	}
}
