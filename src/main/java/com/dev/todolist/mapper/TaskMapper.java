package com.dev.todolist.mapper;

import com.dev.todolist.entity.Task;
import com.dev.todolist.dto.TaskDTO;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

	public TaskDTO toDTO(Task task) {
		return new TaskDTO(
			task.getId(),
			task.getTitle(),
			task.getDescription(),
			task.isDone());
	}

	public Task toEntity(TaskDTO taskDTO) {
		return Task.builder()
			.id(taskDTO.id())
			.title(taskDTO.title())
			.description(taskDTO.description())
			.isDone(taskDTO.isDone())
			.build();
	}
}
