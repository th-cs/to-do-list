package com.dev.todolist.mapper;

import com.dev.todolist.entity.User;
import com.dev.todolist.dto.request.UserRequestDTO;
import com.dev.todolist.dto.response.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	public UserResponseDTO toDTO(User user) {
		return new UserResponseDTO(
			user.getId(),
			user.getName(),
			user.getEmail(),
			user.getTasks());
	}

	public User toEntity(UserRequestDTO userRequestDTO) {
		return User.builder()
			.id(userRequestDTO.id())
			.name(userRequestDTO.name())
			.email(userRequestDTO.email())
			.build();
	}
}
