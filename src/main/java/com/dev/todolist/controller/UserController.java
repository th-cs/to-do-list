package com.dev.todolist.controller;

import com.dev.todolist.service.UserService;
import com.dev.todolist.dto.request.UserRequestDTO;
import com.dev.todolist.dto.response.UserResponseDTO;
import com.dev.todolist.service.TaskService;
import com.dev.todolist.dto.TaskDTO;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.dao.DataIntegrityViolationException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;
	private final TaskService taskService;

	public UserController(UserService userService, TaskService taskService) {
		this.userService = userService;
		this.taskService = taskService;
	}

	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> listAllUsers() {
		return ResponseEntity.ok(userService.listALlUsers());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> listUserById(@PathVariable Long userId) {
		try {
			return ResponseEntity.ok(userService.listUserById(userId));
		} catch (EntityNotFoundException exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{userId}/tasks")
	public ResponseEntity<List<TaskDTO>> listTasksByUser(@PathVariable Long userId) {
		try {
			return ResponseEntity.ok(taskService.listTasksByUser(userId));
		} catch (EntityNotFoundException exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> createUser(
		@RequestBody @Valid UserRequestDTO userRequestDTO) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(userService.createUser(userRequestDTO));
	}

	@PostMapping("/{userId}/tasks")
	public ResponseEntity<?> createTask(@PathVariable Long userId,
		@RequestBody @Valid TaskDTO taskDTO) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
				.body(taskService.createTask(userId, taskDTO));
		} catch (DataIntegrityViolationException exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable Long userId,
		@RequestBody @Valid UserRequestDTO userRequestDTO) {
		try {
			return ResponseEntity.ok()
				.body(userService.updateUser(userId, userRequestDTO));
		} catch (EntityNotFoundException exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		if (userService.deleteUser(userId)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.internalServerError().build();
	}
}
