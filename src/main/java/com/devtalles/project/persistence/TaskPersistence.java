package com.devtalles.project.persistence;

import com.devtalles.project.model.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskPersistence {
	private static final String FILE_PATH = "tasks.json";
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static final Type taskListType = new TypeToken<List<Task>>() {}.getType();

	public static void saveTasks(List<Task> tasks) {
		try (Writer writer = new FileWriter(FILE_PATH)) {
			gson.toJson(tasks, writer);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static List<Task> loadTasks() {
		File file = new File(FILE_PATH);
		if(!file.exists()) {
			return new ArrayList<>();
		}

		try (Reader reader = new FileReader(FILE_PATH)) {
			return gson.fromJson(reader, taskListType);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return new ArrayList<>();
		}
	}
}
