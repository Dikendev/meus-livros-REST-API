package com.project.meuslivros.user.service;

import com.project.meuslivros.exception.NotFoundException;
import com.project.meuslivros.user.entity.UserEntity;
import com.project.meuslivros.user.data.UserDto;
import com.project.meuslivros.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    private byte[] createSalt() {
        var random = new SecureRandom();
        var salt = new byte[128];
        random.nextBytes(salt);

        return salt;
    }

    private byte[] createPasswordHash(String password, byte[] salt)
            throws NoSuchAlgorithmException {

        var md = MessageDigest.getInstance("SHA-512");
        md.update(salt);

        return md.digest(password.getBytes(StandardCharsets.UTF_8)
        );
    }

    public UserDto createUser(UserDto userDto, String password)
            throws NoSuchAlgorithmException {

        var user = convertToEntity(userDto);

        if (password.isBlank()) throw new IllegalArgumentException(
                "Password is required."
        );

        var existEmail = userRepository.selectExistsEmail(user.getEmail());
        if (existEmail) throw new BadCredentialsException(
                "Email " + user.getEmail() + "taken"
        );

        byte [] salt = createSalt();
        byte [] hashedPassword = createPasswordHash(password, salt);

        user.setStoredSalt(salt);
        user.setStoredHash(hashedPassword);

        userRepository.save(user);

        return convertToDto(user);
    }

    public void updateUser(UUID id, UserDto userDto, String password)
            throws NoSuchAlgorithmException {

        var user = findOrThrow(id);
        var userParam = convertToEntity(userDto);

        user.setEmail(userParam.getEmail());
        user.setMobileNumber(userParam.getMobileNumber());

        if (!password.isBlank()) {
            byte[] salt = createSalt();
            byte[] hashedPassword = createPasswordHash(password, salt);

            user.setStoredSalt(salt);
            user.setStoredHash(hashedPassword);
        }

        userRepository.save(user);
    }

    public void removeUserById(UUID id) {
        findOrThrow(id);
        userRepository.deleteById(id);
    }

    public List<UserDto> findAllUsers() {
        var userEntityList =
                new ArrayList<>(userRepository.findAll());

        return userEntityList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDto findUserById(final UUID id) {
        var user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "User by id " + id + " was not found")
                );

        return convertToDto(user);
    }

    public UserEntity searchByEmail(String email) {
        return userRepository.findByEmail(email);
    }

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
