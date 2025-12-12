package com.example.day2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.day2.dto.CreateUserDto;
import com.example.day2.dto.UserDto;
import com.example.day2.exception.UserNotFoundException;
import com.example.day2.repo.UserRepo;

import entity.User;


@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDto createUser(CreateUserDto userDto) {
        User user = new User(userDto.name(), userDto.email());
        userRepo.save(user);
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    public UserDto getUser(Long id){
        User user = userRepo.findById(id)
                    .orElseThrow(() -> new UserNotFoundException(id));

        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
    
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                    .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                    .toList();
    }

    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepo.deleteById(id);
    }

    public UserDto updateUser(Long id, CreateUserDto userDto) {
        User user = userRepo.findById(id)
                    .orElseThrow(() -> new UserNotFoundException(id));

        user.setName(userDto.name());
        user.setEmail(userDto.email());
        userRepo.save(user);

        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
}
