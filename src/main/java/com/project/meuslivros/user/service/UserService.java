package com.project.meuslivros.user.service;

import com.project.meuslivros.exception.NotFoundException;
import com.project.meuslivros.user.entity.UserEntity;
import com.project.meuslivros.user.data.UserDto;
import com.project.meuslivros.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    private UserDto convertToDto(UserEntity entity) {
        return mapper.map(entity, UserDto.class);
    }

    private UserEntity convertToEntity(UserDto dto) {
        return mapper.map(dto, UserEntity.class);
    }

    private UserEntity findOrThrow(final UUID id) {
      return userRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("User by id" + id +
                        " was not found")
                );
    }

}
