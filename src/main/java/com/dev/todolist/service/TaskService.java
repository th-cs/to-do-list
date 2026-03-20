package com.dev.todolist.service;

import com.dev.todolist.entity.Task;
import com.dev.todolist.repository.TaskRepository;
import com.dev.todolist.mapper.TaskMapper;
import com.dev.todolist.dto.TaskDTO;
import com.dev.todolist.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	private final TaskRepository taskRepository;
	private final TaskMapper taskMapper;
	private final UserRepository userRepository;

	public TaskService(TaskRepository taskRepository, TaskMapper taskMapper,
		UserRepository userRepository) {
		this.taskRepository = taskRepository;
		this.taskMapper = taskMapper;
		this.userRepository = userRepository;
	}

	public List<TaskDTO> listAllTasks() {
		return taskRepository.findAll()
			.stream()
			.map(task -> taskMapper.toDTO(task))
			.collect(Collectors.toList());
	}

	public TaskDTO listTaskById(Long taskId) {
		return taskMapper.toDTO(taskRepository.getReferenceById(taskId));
	}

	public List<TaskDTO> listTasksByUser(Long userId) {
		return userRepository.getReferenceById(userId).getTasks()
			.stream()
			.map(task -> taskMapper.toDTO(task))
			.collect(Collectors.toList());
	}

	public TaskDTO createTask(Long userId, TaskDTO taskDTO) {
		Task task = new Task();

		task.setTitle(taskDTO.title());
		task.setDescription(taskDTO.description());
		task.setDone(taskDTO.isDone());
		task.setUser(userRepository.getReferenceById(userId));
		taskRepository.save(task);

		return taskMapper.toDTO(task);
	}

	public TaskDTO updateTask(Long taskId, TaskDTO taskDTO) {
		Task task = taskRepository.getReferenceById(taskId);

		task.setTitle(taskDTO.title());
		task.setDescription(taskDTO.description());
		task.setDone(taskDTO.isDone());
		taskRepository.save(task);

		return taskMapper.toDTO(task);
	}

	public boolean deleteTask(Long taskId) {
		if (taskRepository.existsById(taskId)) {
			taskRepository.deleteById(taskId);
			return true;
		}
		return false;
	}
}
