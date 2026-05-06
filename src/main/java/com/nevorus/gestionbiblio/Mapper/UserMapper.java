package com.nevorus.gestionbiblio.Mapper;

import com.nevorus.gestionbiblio.DTO.User.UserCreateDto;
import com.nevorus.gestionbiblio.DTO.User.UserResponseDto;
import com.nevorus.gestionbiblio.Entity.UserEntity;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserMapper {

    public UserResponseDto toUserResponseDto(UserEntity user){

        return UserResponseDto.builder()
                .email(user.getEmail())
                .nom(user.getNom())
                .id(user.getId())
                .role(user.getRole())
                .status(user.getStatut())
                .build();
    }

    public List<UserResponseDto> toUserResponseDto(List<UserEntity> users) {
        return users.stream()
                .map(this::toUserResponseDto)
                .toList();
    }


    public void applyDtoToEntity(UserCreateDto dto, UserEntity entity) {
        entity.setNom(dto.getNom());
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());
        entity.setStatut(dto.getStatus());
    }
}
