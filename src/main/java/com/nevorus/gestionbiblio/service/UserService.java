package com.nevorus.gestionbiblio.service;

import com.nevorus.gestionbiblio.DTO.User.UserCreateDto;
import com.nevorus.gestionbiblio.DTO.User.UserResponseDto;
import com.nevorus.gestionbiblio.Entity.UserEntity;
import com.nevorus.gestionbiblio.Mapper.UserMapper;
import com.nevorus.gestionbiblio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public List<UserResponseDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return userMapper.toUserResponseDto(users);
    }

    public UserResponseDto getUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return userMapper.toUserResponseDto(user.get());
    }

    public UserResponseDto addUser(UserCreateDto userCreateDto) {
        if (userRepository.findByEmail(userCreateDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé : " + userCreateDto.getEmail());
        }

        UserEntity userEntity = new UserEntity();
        userMapper.applyDtoToEntity(userCreateDto, userEntity);
        return userMapper.toUserResponseDto(userRepository.save(userEntity));
    }

    public UserResponseDto updateUser(long id, UserCreateDto userCreateDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + id));

        userMapper.applyDtoToEntity(userCreateDto, userEntity);
        return userMapper.toUserResponseDto(userRepository.save(userEntity));
    }

    public UserResponseDto deleteUser(long id) {
        userRepository.findById(id).ifPresent(userEntity -> {
            userRepository.delete(userEntity);
        });
        return userMapper.toUserResponseDto(userRepository.findById(id).get());
    }
}
