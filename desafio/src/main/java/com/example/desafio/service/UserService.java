package com.example.desafio.service;

import com.example.desafio.entities.UserEntity;
import com.example.desafio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    public Optional<UserEntity> findById(Long id) {
        return repository.findById(id);
    }

    public UserEntity updateUserByEntity(UserEntity obj) {
        return repository.save(obj);
    }

    public UserEntity deleteById(Long id) {
        Optional<UserEntity> obj = repository.findById(id);
        try {
            obj = repository.findById(id);
            obj.ifPresent(repository::delete);
        } catch (Exception e) {
            e.getMessage();
        }

        return obj.get();
    }

    public UserEntity save(UserEntity obj) {
        return repository.save(obj);
    }

    public UserEntity validationForCriation(UserEntity obj) {
        try {
            List<UserEntity> objUser = repository.findAll();
            if (!objUser.contains(obj))
                return repository.save(obj);
        } catch (Exception e) {
            e.getMessage();
        }

        return obj;
    }

}