package com.nevorus.gestionbiblio.Controller;

import com.nevorus.gestionbiblio.DTO.User.UserCreateDto;
import com.nevorus.gestionbiblio.DTO.User.UserResponseDto;
import com.nevorus.gestionbiblio.Entity.UserEntity;
import com.nevorus.gestionbiblio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUsers(@RequestBody UserCreateDto dtos) {
        UserResponseDto userCreated = userService.addUser(dtos);
        return ResponseEntity.ok(userCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto>   updateUsers(@PathVariable Long id, @RequestBody UserCreateDto dtos) {
        UserResponseDto userUpdated = userService.updateUser(id, dtos);
        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDto> deleteUsers(@PathVariable Long id) {
        UserResponseDto userDeleted = userService.deleteUser(id);
        return ResponseEntity.ok(userDeleted);
    }

}
