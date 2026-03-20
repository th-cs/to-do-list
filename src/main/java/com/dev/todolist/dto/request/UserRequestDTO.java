package com.dev.todolist.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
	Long id,
	@NotBlank(message = "Você deve informar um nome!")
	String name,
	@NotBlank(message = "Você deve informar um email!")
	String email) {

}
