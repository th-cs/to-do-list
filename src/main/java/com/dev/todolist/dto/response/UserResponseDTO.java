package com.dev.todolist.dto.response;

import com.dev.todolist.entity.Task;
import java.util.List;

public record UserResponseDTO(
	Long id,
	String name,
	String email,
	List<Task> tasks) {

}
