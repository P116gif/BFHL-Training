package com.example.booking.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.booking.domain.models.User;
import com.example.booking.infrastructure.persistence.Entities.UserEntity;
import com.example.booking.interfaces.UserMapper;


@Component
public class UserRepositoryImpl implements UserRepository {
    
    private final JpaUserRepository jpaRepository;
    private final UserMapper userMapper;

    public UserRepositoryImpl(JpaUserRepository jpaRepository, UserMapper userMapper) {
        this.jpaRepository = jpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findById(UUID id) {
        
        return jpaRepository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(userMapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        return userMapper.toDomain(jpaRepository.save(userEntity));
    }


}
