package com.dev.todolist.service;

import com.dev.todolist.entity.User;
import com.dev.todolist.repository.UserRepository;
import com.dev.todolist.mapper.UserMapper;
import com.dev.todolist.dto.request.UserRequestDTO;
import com.dev.todolist.dto.response.UserResponseDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public List<UserResponseDTO> listALlUsers() {
		return userRepository.findAll()
			.stream()
			.map(user -> userMapper.toDTO(user))
			.collect(Collectors.toList());
	}

	public UserResponseDTO listUserById(Long userId) {
		return userMapper.toDTO(userRepository.getReferenceById(userId));
	}

	public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
		return userMapper.toDTO(
			userRepository.save(userMapper.toEntity(userRequestDTO)));
	}

	public UserResponseDTO updateUser(Long userId,
		UserRequestDTO userRequestDTO) {
		User user = userRepository.getReferenceById(userId);
			user.setName(userRequestDTO.name());
			user.setEmail(userRequestDTO.email());
		userRepository.save(user);

		return userMapper.toDTO(user);
	}

	public boolean deleteUser(Long userId) {
		if (userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
			return true;
		}
		return false;
	}
}
