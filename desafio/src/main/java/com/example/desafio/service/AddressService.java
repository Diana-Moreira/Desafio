package com.example.desafio.service;

import com.example.desafio.entities.AddressEntity;
import com.example.desafio.entities.UserEntity;
import com.example.desafio.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;

    public Optional<AddressEntity> findById(Long id) {
        return repository.findById(id);
    }

    public AddressEntity updateAddressByEntity(AddressEntity obj) {
        return repository.save(obj);
    }

    public AddressEntity deleteById(Long id) {
        try {
            Optional<AddressEntity> obj = repository.findById(id);
            obj.ifPresent(repository::delete);
            return obj.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}