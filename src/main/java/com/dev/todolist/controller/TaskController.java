package com.dev.todolist.controller;

import com.dev.todolist.service.TaskService;
import com.dev.todolist.dto.TaskDTO;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping
	public ResponseEntity<List<TaskDTO>> listAllTasks() {
		return ResponseEntity.ok(taskService.listAllTasks());
	}

	@GetMapping("/{taskId}")
	public ResponseEntity<?> listTaskById(@PathVariable Long taskId) {
		try {
			return ResponseEntity.ok(taskService.listTaskById(taskId));
		} catch (EntityNotFoundException exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{taskId}")
	public ResponseEntity<?> updateTask(@PathVariable Long taskId,
		@RequestBody @Valid TaskDTO taskDTO) {
		try {
			return ResponseEntity.ok()
				.body(taskService.updateTask(taskId, taskDTO));
		} catch (EntityNotFoundException exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
		if (taskService.deleteTask(taskId)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.internalServerError().build();
	}

}
