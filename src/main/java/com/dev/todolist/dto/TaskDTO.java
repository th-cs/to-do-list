package com.dev.todolist.dto;

import jakarta.validation.constraints.NotBlank;

public record TaskDTO(
	Long id,
	@NotBlank(message = "Você deve informar o título da tarefa!")
	String title,
	String description,
	boolean isDone) {

}
